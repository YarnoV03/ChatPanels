package be.yarno.chatpanels.panel;

public class PanelBounds {

  private int x;
  private int y;

  private int width;
  private int height;

  public PanelBounds(int x, int y, int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be greater than 0.");
    }

    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public int getWidth() {
    return width;
  }

  public int getHeight() {
    return height;
  }

  public void setPosition(int x, int y) {
    this.x = x;
    this.y = y;
  }

  public void setSize(int width, int height) {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Width and height must be greater than 0.");
    }

    this.width = width;
    this.height = height;
  }
}