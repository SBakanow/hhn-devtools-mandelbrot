package de.hhn.it.devtools.apis.examples.renderservice;

import de.hhn.it.devtools.apis.renderer.RenderService;
import de.hhn.it.devtools.apis.renderer.Shape;

public class RenderServiceUsageDemo {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RenderServiceUsageDemo.class);

  public static void main(String[] args) throws InterruptedException {
    RenderService renderService = null;
    Shape cube = Shape.CUBE;
    double zoomInFactor = 0.5;
    double zoomOutFactor = 1.5;
    double rotationX = 4.0;
    double rotationY = 10.0;

    int red = 255;
    int green = 0;
    int blue = 0;

    logger.info(">>> render");
    // Render the shape given
    renderService.render(cube);

    Thread.sleep(3000);

    logger.info(">>> zoom in");
    // Zoom into the render
    renderService.zoom(zoomInFactor);

    Thread.sleep(1000);

    logger.info(">>> rotate");
    // Rotate the shape vertically and horizontally
    renderService.rotate(rotationX, rotationY);

    Thread.sleep(1000);

    logger.info(">>> zoom out");
    // Zoom out of the render
    renderService.zoom(zoomOutFactor);

    Thread.sleep(1000);

    logger.info(">>> change color to red");
    // Change the color of the shape to red
    renderService.changeColor(red, green, blue);
  }
}
