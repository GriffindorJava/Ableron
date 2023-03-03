package io.github.ableron.springboot.filter;

import io.github.ableron.Ableron;
import io.github.ableron.AbleronConfig;
import io.github.ableron.springboot.autoconfigure.AbleronAutoConfiguration;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServlet;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockFilterChain;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

@ContextConfiguration(classes = AbleronAutoConfiguration.class)
public class UiCompositionFilterTest {

  @Test
  public void shouldApplyUiComposition() throws ServletException, IOException {
    // given
    var ableron = new Ableron(AbleronConfig.builder().build());
    var uiCompositionFilter = new UiCompositionFilter(ableron);
    var request = new MockHttpServletRequest();
    var response = new MockHttpServletResponse();
    var filterChain = new MockFilterChain(mock(HttpServlet.class), uiCompositionFilter, new OutputGeneratingFilter(
      "<ableron-include src=\"foo\">fallback</ableron-include>"
    ));

    // when
    filterChain.doFilter(request, response);

    // then
    assertEquals("fallback", response.getContentAsString());
    assertEquals(8, response.getHeaderValue(HttpHeaders.CONTENT_LENGTH));
  }

  @Test
  public void shouldNotApplyUiCompositionIfDisabled() throws ServletException, IOException {
    // given
    var ableron = new Ableron(AbleronConfig.builder().enabled(false).build());
    var uiCompositionFilter = new UiCompositionFilter(ableron);
    var request = new MockHttpServletRequest();
    var response = new MockHttpServletResponse();
    var filterChain = new MockFilterChain(mock(HttpServlet.class), uiCompositionFilter, new OutputGeneratingFilter(
      "<ableron-include src=\"foo\">fallback</ableron-include>"
    ));

    // when
    filterChain.doFilter(request, response);

    // then
    assertEquals("<ableron-include src=\"foo\">fallback</ableron-include>", response.getContentAsString());
  }

  @Test
  public void shouldNotApplyUiCompositionIfContentTypeIsNotTextHtml() throws ServletException, IOException {
    // given
    var ableron = new Ableron(AbleronConfig.builder().build());
    var uiCompositionFilter = new UiCompositionFilter(ableron);
    var request = new MockHttpServletRequest();
    var response = new MockHttpServletResponse();
    var filterChain = new MockFilterChain(mock(HttpServlet.class), uiCompositionFilter, new OutputGeneratingFilter(
      "<ableron-include src=\"foo\">fallback</ableron-include>", "application/json"
    ));

    // when
    filterChain.doFilter(request, response);

    // then
    assertEquals("<ableron-include src=\"foo\">fallback</ableron-include>", response.getContentAsString());
  }

  @Test
  public void shouldSetContentLengthHeaderToZeroForEmptyResponse() throws ServletException, IOException {
    // given
    var ableron = new Ableron(AbleronConfig.builder().build());
    var uiCompositionFilter = new UiCompositionFilter(ableron);
    var request = new MockHttpServletRequest();
    var response = new MockHttpServletResponse();
    var filterChain = new MockFilterChain(mock(HttpServlet.class), uiCompositionFilter, new OutputGeneratingFilter(
      "<ableron-include />"
    ));

    // when
    filterChain.doFilter(request, response);

    // then
    assertEquals("", response.getContentAsString());
    assertEquals(0, response.getHeaderValue(HttpHeaders.CONTENT_LENGTH));
  }

  static class OutputGeneratingFilter implements Filter {

    private final String content;
    private final String contentType;

    public OutputGeneratingFilter(String content) {
      this(content, MediaType.TEXT_HTML_VALUE);
    }

    public OutputGeneratingFilter(String content, String contentType) {
      this.content = content;
      this.contentType = contentType;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException {
      servletResponse.setContentType(contentType);
      servletResponse.getOutputStream().print(content);
      servletResponse.setContentLength(content.length());
    }
  }
}
