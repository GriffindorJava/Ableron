package io.github.ableron.springboot.autoconfigure;

import io.github.ableron.Ableron;
import io.github.ableron.AbleronConfig;
import io.github.ableron.AbleronConfigParams;
import io.github.ableron.springboot.filter.UiCompositionFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
@ConditionalOnClass(Ableron.class)
@ConditionalOnProperty(value = "ableron.enabled", havingValue = "true", matchIfMissing = true)
@EnableConfigurationProperties(AbleronProperties.class)
public class AbleronAutoConfiguration {

  @Autowired
  private AbleronProperties ableronProperties;

  @Bean
  @ConditionalOnMissingBean
  public AbleronConfig ableronConfig() {
    AbleronConfig ableronConfig = new AbleronConfig();
    ableronConfig.put(AbleronConfigParams.ENABLED, ableronProperties.isEnabled());
    return ableronConfig;
  }

  @Bean
  @ConditionalOnMissingBean
  public Ableron ableron(AbleronConfig ableronConfig) {
    return new Ableron(ableronConfig);
  }

  @Bean
  @ConditionalOnMissingBean
  public UiCompositionFilter uiCompositionFilter(Ableron ableron) {
    return new UiCompositionFilter(ableron);
  }
}
