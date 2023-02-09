package io.github.ableron.springboot.autoconfigure;

import io.github.ableron.springboot.filter.UiCompositionFilter;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

@SpringBootTest(
  classes = { AbleronAutoConfiguration.class },
  properties = { "ableron.enabled=false" }
)
public class AbleronDisabledTest {

  @Autowired
  private ApplicationContext applicationContext;

  @Test
  public void shouldNotInitUiCompositionFilterIfDisabled() {
    try {
      applicationContext.getBean(UiCompositionFilter.class);
      fail("UiCompositionFilter must not be instantiated");
    } catch(NoSuchBeanDefinitionException e) {
      assertEquals("No qualifying bean of type 'io.github.ableron.springboot.filter.UiCompositionFilter' available", e.getMessage());
    }
  }
}
