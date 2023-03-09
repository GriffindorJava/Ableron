package io.github.ableron.springboot.autoconfigure;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "ableron")
public class AbleronProperties {

  /**
   * Whether Ableron UI composition is enabled.
   *
   * Defaults to true.
   */
  private boolean enabled = true;

  /**
   * Maximum duration to wait for a successful and complete response of an include source
   * or fallback URL.
   *
   * Defaults to 5 seconds.
   */
  private long requestTimeoutMillis = 5000;

  /**
   * Duration to cache HTTP responses in case there is no caching information provided
   * along the response, i.e. neither Cache-Control nor Expires header.
   *
   * Defaults to 5 minutes.
   */
  private long defaultResponseCacheDurationMillis = 5000;

  /**
   * Maximum size in bytes the response cache may have.
   *
   * Defaults to 10 MB.
   */
  private long maxCacheSizeInBytes = 1024 * 1024 * 10;

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

  public long getRequestTimeoutMillis() {
    return requestTimeoutMillis;
  }

  public void setRequestTimeoutMillis(long requestTimeoutMillis) {
    this.requestTimeoutMillis = requestTimeoutMillis;
  }

  public long getDefaultResponseCacheDurationMillis() {
    return defaultResponseCacheDurationMillis;
  }

  public void setDefaultResponseCacheDurationMillis(long defaultResponseCacheDurationMillis) {
    this.defaultResponseCacheDurationMillis = defaultResponseCacheDurationMillis;
  }

  public long getMaxCacheSizeInBytes() {
    return maxCacheSizeInBytes;
  }

  public void setMaxCacheSizeInBytes(long maxCacheSizeInBytes) {
    this.maxCacheSizeInBytes = maxCacheSizeInBytes;
  }
}
