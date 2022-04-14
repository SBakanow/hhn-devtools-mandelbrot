package de.hhn.it.devtools.apis.examples.renderservice;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.renderer.RenderService;
import de.hhn.it.devtools.apis.renderer.Shape;

public class RenderServiceUsageDemo {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RenderServiceUsageDemo.class);

  public static void main(String[] args) throws IllegalParameterException, InterruptedException {
    RenderService renderService = null;
    Shape cube = Shape.CUBE;
    double zoomInFactor = 1.5;
    double zoomOutFactor = 0.5;
    double rotationX = 4.0;
    double rotationY = 10.0;

    float red = 1.0f;
    float green = 0.2f;
    float blue = 0.1f;
    float alpha = 1.0f;

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

    logger.info(">>> change color");
    // Change the color of the shape
    renderService.changeColor(red, green, blue, alpha);
  }
}
