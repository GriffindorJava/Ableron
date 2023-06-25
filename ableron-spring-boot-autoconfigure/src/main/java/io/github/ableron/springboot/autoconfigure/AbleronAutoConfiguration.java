package io.github.ableron.springboot.autoconfigure;

import io.github.ableron.Ableron;
import io.github.ableron.AbleronConfig;
import io.github.ableron.springboot.filter.UiCompositionFilter;
import java.time.Duration;

import jakarta.servlet.Filter;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AutoConfiguration
@ConditionalOnClass(Ableron.class)
@ConditionalOnProperty(value = "ableron.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AbleronProperties.class)
public class AbleronAutoConfiguration {

  private final AbleronProperties ableronProperties;

  public AbleronAutoConfiguration(AbleronProperties ableronProperties) {
    this.ableronProperties = ableronProperties;
  }

  @Bean
  @ConditionalOnMissingBean
  public AbleronConfig ableronConfig() {
    return AbleronConfig.builder()
      .enabled(ableronProperties.isEnabled())
      .fragmentRequestTimeout(Duration.ofMillis(ableronProperties.getFragmentRequestTimeoutMillis()))
      .fragmentRequestHeadersToPass(ableronProperties.getFragmentRequestHeadersToPass())
      .primaryFragmentResponseHeadersToPass(ableronProperties.getPrimaryFragmentResponseHeadersToPass())
      .cacheMaxSizeInBytes(ableronProperties.getCacheMaxSizeInBytes())
      .build();
  }

  @Bean
  @ConditionalOnMissingBean
  public Ableron ableron(AbleronConfig ableronConfig) {
    return new Ableron(ableronConfig);
  }

  @Configuration(proxyBeanMethods = false)
  @ConditionalOnClass(Filter.class)
  public static class SpringWebMvcConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public UiCompositionFilter uiCompositionFilter(Ableron ableron) {
      return new UiCompositionFilter(ableron);
    }
  }
}
