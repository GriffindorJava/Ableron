package io.ableron.springboot.autoconfigure;

import io.ableron.Ableron;
import io.ableron.AbleronConfig;
import io.ableron.AbleronConfigParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnClass(Ableron.class)
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
}
