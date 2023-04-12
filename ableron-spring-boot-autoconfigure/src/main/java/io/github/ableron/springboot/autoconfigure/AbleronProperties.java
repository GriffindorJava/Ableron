package io.github.ableron.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties(prefix = "ableron")
public class AbleronProperties {

  /**
   * Whether Ableron UI composition is enabled.
   * Defaults to true.
   */
  private boolean enabled = true;

  /**
   * Timeout for requesting fragments.
   * Defaults to 3 seconds.
   */
  private long fragmentRequestTimeoutMillis = Duration.ofSeconds(3).toMillis();

  /**
   * Duration to cache fragments in case no caching information is provided
   * along the response, i.e. neither Cache-Control nor Expires header.
   * Defaults to 5 minutes.
   */
  private long fragmentDefaultCacheDurationMillis = Duration.ofMinutes(5).toMillis();

  /**
   * Request headers that are passed to fragment requests if present.
   */
  private List<String> fragmentRequestHeadersToPass = List.of(
    "Accept-Language",
    "Correlation-ID",
    "Forwarded",
    "Referer",
    "User-Agent",
    "X-Correlation-ID",
    "X-Forwarded-For",
    "X-Forwarded-Proto",
    "X-Forwarded-Host",
    "X-Real-IP",
    "X-Request-ID"
  );

  /**
   * Maximum size in bytes the fragment cache may have.
   * Defaults to 10 MB.
   */
  private long cacheMaxSizeInBytes = DataSize.ofMegabytes(10).toBytes();

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public long getFragmentRequestTimeoutMillis() {
    return fragmentRequestTimeoutMillis;
  }

  public void setFragmentRequestTimeoutMillis(long fragmentRequestTimeoutMillis) {
    this.fragmentRequestTimeoutMillis = fragmentRequestTimeoutMillis;
  }

  public long getFragmentDefaultCacheDurationMillis() {
    return fragmentDefaultCacheDurationMillis;
  }

  public void setFragmentDefaultCacheDurationMillis(long fragmentDefaultCacheDurationMillis) {
    this.fragmentDefaultCacheDurationMillis = fragmentDefaultCacheDurationMillis;
  }

  public List<String> getFragmentRequestHeadersToPass() {
    return fragmentRequestHeadersToPass;
  }

  public void setFragmentRequestHeadersToPass(List<String> fragmentRequestHeadersToPass) {
    this.fragmentRequestHeadersToPass = fragmentRequestHeadersToPass;
  }

  public long getCacheMaxSizeInBytes() {
    return cacheMaxSizeInBytes;
  }

  public void setCacheMaxSizeInBytes(long cacheMaxSizeInBytes) {
    this.cacheMaxSizeInBytes = cacheMaxSizeInBytes;
  }
}
