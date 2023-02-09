package io.github.ableron.springboot.autoconfigure;

import io.github.ableron.springboot.filter.UiCompositionFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(classes = { AbleronAutoConfiguration.class })
public class AbleronEnabledTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void shouldInitUiCompositionFilterByDefault() {
    try {
      applicationContext.getBean(UiCompositionFilter.class);
    } catch(NoSuchBeanDefinitionException e) {
      fail("UiCompositionFilter not instantiated");
    }
  }
}
