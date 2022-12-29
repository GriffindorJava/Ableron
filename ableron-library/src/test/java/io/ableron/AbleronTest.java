package io.ableron;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AbleronTest {

  @Test
  public void shouldHandlePropertyEnabled() {
    AbleronConfig ableronConfig = new AbleronConfig();
    Ableron ableron = new Ableron(ableronConfig);

    assertFalse(ableron.isEnabled());

    ableronConfig.put(AbleronConfigParams.ENABLED, "true");
    assertTrue(ableron.isEnabled());

    ableronConfig.put(AbleronConfigParams.ENABLED, "false");
    assertFalse(ableron.isEnabled());

    ableronConfig.put(AbleronConfigParams.ENABLED, "TRUE");
    assertTrue(ableron.isEnabled());

    ableronConfig.put(AbleronConfigParams.ENABLED, "FALSE");
    assertFalse(ableron.isEnabled());

    ableronConfig.put(AbleronConfigParams.ENABLED, "foo");
    assertFalse(ableron.isEnabled());
  }
}
