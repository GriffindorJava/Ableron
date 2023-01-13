package io.github.ableron.springboot.autoconfigure;

import org.junit.jupiter.api.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = AbleronAutoConfiguration.class)
public class SpringContextTest {

  @Test
  public void whenSpringContextIsBootstrapped_thenNoExceptions() {
  }
}
