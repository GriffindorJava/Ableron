package io.ableron;

public class Ableron {

  private final AbleronConfig ableronConfig;

  public Ableron(AbleronConfig ableronConfig) {
    this.ableronConfig = ableronConfig;
  }

  public boolean isEnabled() {
    return Boolean.parseBoolean(ableronConfig.getProperty(AbleronConfigParams.ENABLED));
  }
}
