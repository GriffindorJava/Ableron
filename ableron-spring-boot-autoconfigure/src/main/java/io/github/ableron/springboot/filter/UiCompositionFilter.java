package io.github.ableron.springboot.filter;

import io.github.ableron.Ableron;
import io.github.ableron.HttpUtil;
import io.github.ableron.TransclusionResult;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingResponseWrapper;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class UiCompositionFilter extends OncePerRequestFilter {

  private final Ableron ableron;

  public UiCompositionFilter(Ableron ableron) {
    this.ableron = ableron;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
    HttpServletResponse responseToUse = response;

    if (!isAsyncDispatch(request) && !(response instanceof ContentCachingResponseWrapper)) {
      responseToUse = new ContentCachingResponseWrapper(response);
    }

    filterChain.doFilter(request, responseToUse);

    if (!isAsyncStarted(request)) {
      handleResponse(request, responseToUse);
    }
  }

  private void handleResponse(HttpServletRequest request, HttpServletResponse response) throws IOException {
    ContentCachingResponseWrapper responseWrapper = WebUtils.getNativeResponse(response, ContentCachingResponseWrapper.class);
    Assert.notNull(responseWrapper, "ContentCachingResponseWrapper not found");

    if (shouldApplyTransclusion(responseWrapper)) {
      applyTransclusion(request, responseWrapper);
    }

    responseWrapper.copyBodyToResponse();
  }

  /**
   * Indicates whether transclusion should be applied to the response. This is {@code true}
   * if all the following conditions are met:
   * <ul>
   * <li>Response is not committed.</li>
   * <li>Response status code is not in the {@code 1xx} series.</li>
   * <li>Response status code is not in the {@code 3xx} series.</li>
   * <li>Response content type is {@code text/html}.</li>
   * </ul>
   *
   * @param response The HTTP response
   * @return {@code true} if transclusion should be applied to the given response, {@code false} otherwise
   */
  private boolean shouldApplyTransclusion(HttpServletResponse response) {
    HttpStatus.Series responseStatusCodeSeries = HttpStatus.Series.resolve(response.getStatus());

    return !response.isCommitted()
      && !HttpStatus.Series.INFORMATIONAL.equals(responseStatusCodeSeries)
      && !HttpStatus.Series.REDIRECTION.equals(responseStatusCodeSeries)
      && response.getContentType() != null
      && MediaType.TEXT_HTML.isCompatibleWith(MediaType.valueOf(response.getContentType()));
  }

  /**
   * @param responseWrapper The wrapped HTTP response
   */
  private void applyTransclusion(HttpServletRequest request, ContentCachingResponseWrapper responseWrapper) throws IOException {
    String encoding = getResponseBodyCharacterEncoding(responseWrapper);
    String originalResponseBody = new String(responseWrapper.getContentAsByteArray(), encoding);
    TransclusionResult transclusionResult = ableron.resolveIncludes(originalResponseBody, getRequestHeaders(request));
    String processedResponseBody = transclusionResult.getContent();
    responseWrapper.resetBuffer();

    if (processedResponseBody.isEmpty()) {
      responseWrapper.getResponse().setContentLength(0);
    } else {
      responseWrapper.getOutputStream().write(processedResponseBody.getBytes(encoding));
    }

    if (transclusionResult.hasPrimaryInclude()) {
      transclusionResult.getPrimaryIncludeStatusCode().ifPresent(responseWrapper::setStatus);
      transclusionResult.getPrimaryIncludeResponseHeaders().forEach((name, values) -> {
        for (int i = 0; i < values.size(); i++) {
          if (i == 0) {
            responseWrapper.setHeader(name, values.get(i));
          } else {
            responseWrapper.addHeader(name, values.get(i));
          }
        }
      });
    }

    transclusionResult.getContentExpirationTime().ifPresent(contentExpirationTime -> {
      if (contentExpirationTime.isBefore(Instant.now())) {
        responseWrapper.setHeader(HttpHeaders.CACHE_CONTROL, "no-store");
      } else if (contentExpirationTime.isBefore(getInitialPageExpirationTime((HttpServletResponse) responseWrapper.getResponse()))) {
        responseWrapper.setHeader(HttpHeaders.CACHE_CONTROL, "max-age=" + ChronoUnit.SECONDS.between(Instant.now(), contentExpirationTime));
      }
    });
  }

  private Instant getInitialPageExpirationTime(HttpServletResponse response) {
    return HttpUtil.calculateResponseExpirationTime(getResponseHeaders(response));
  }

  private Map<String, List<String>> getRequestHeaders(HttpServletRequest request) {
    return Collections.list(request.getHeaderNames())
      .stream()
      .collect(Collectors.toMap(
        headerName -> headerName,
        headerName -> Collections.list(request.getHeaders(headerName))
      ));
  }

  private Map<String, List<String>> getResponseHeaders(HttpServletResponse response) {
    return response.getHeaderNames()
      .stream()
      .collect(Collectors.toMap(
        headerName -> headerName,
        headerName -> new ArrayList<>(response.getHeaders(headerName))
      ));
  }

  private String getResponseBodyCharacterEncoding(HttpServletResponse response) {
    return Optional.ofNullable(response.getCharacterEncoding())
      .orElse(WebUtils.DEFAULT_CHARACTER_ENCODING);
  }
}
