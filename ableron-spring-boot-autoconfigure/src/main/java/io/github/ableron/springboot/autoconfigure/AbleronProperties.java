package io.github.ableron.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.util.unit.DataSize;

import java.time.Duration;
import java.util.List;

@ConfigurationProperties(prefix = "ableron")
public class AbleronProperties {

  /**
   * Whether Ableron UI composition is enabled.
   */
  private boolean enabled = true;

  /**
   * Timeout for requesting fragments.
   */
  private long fragmentRequestTimeoutMillis = Duration.ofSeconds(3).toMillis();

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
   * Response headers of primary fragments to pass to the page response if present.
   */
  private List<String> primaryFragmentResponseHeadersToPass = List.of(
    "Content-Language",
    "Location",
    "Refresh"
  );

  /**
   * Maximum size in bytes the fragment cache may have.
   */
  private long cacheMaxSizeInBytes = DataSize.ofMegabytes(10).toBytes();

  /**
   * Whether to append UI composition stats as HTML comment to the content.
   */
  private boolean statsAppendToContent = false;

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

  public List<String> getFragmentRequestHeadersToPass() {
    return fragmentRequestHeadersToPass;
  }

  public void setFragmentRequestHeadersToPass(List<String> fragmentRequestHeadersToPass) {
    this.fragmentRequestHeadersToPass = fragmentRequestHeadersToPass;
  }

  public List<String> getPrimaryFragmentResponseHeadersToPass() {
    return primaryFragmentResponseHeadersToPass;
  }

  public void setPrimaryFragmentResponseHeadersToPass(List<String> primaryFragmentResponseHeadersToPass) {
    this.primaryFragmentResponseHeadersToPass = primaryFragmentResponseHeadersToPass;
  }

  public long getCacheMaxSizeInBytes() {
    return cacheMaxSizeInBytes;
  }

  public void setCacheMaxSizeInBytes(long cacheMaxSizeInBytes) {
    this.cacheMaxSizeInBytes = cacheMaxSizeInBytes;
  }

  public boolean isStatsAppendToContent() {
    return statsAppendToContent;
  }

  public void setStatsAppendToContent(boolean statsAppendToContent) {
    this.statsAppendToContent = statsAppendToContent;
  }
}
