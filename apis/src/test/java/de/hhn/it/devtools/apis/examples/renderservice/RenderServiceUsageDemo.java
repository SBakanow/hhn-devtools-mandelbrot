package de.hhn.it.devtools.apis.examples.renderservice;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import de.hhn.it.devtools.apis.renderer.RenderService;
import de.hhn.it.devtools.apis.renderer.Shape;

public class RenderServiceUsageDemo {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(RenderServiceUsageDemo.class);

  public static void main(String[] args) throws IllegalParameterException, InterruptedException {
    RenderService renderService = null;
    Shape cube = new Shape.Cube();
    double zoomInX = 1.05;
    double zoomInY = 1.05;
    double zoomInZ = 1.05;
    double zoomOutX = 0.95;
    double zoomOutY = 0.95;
    double zoomOutZ = 0.95;
    double rotationAngle = 2.0;
    double rotationX = 4.0;
    double rotationY = 10.0;
    double rotationZ = 0.0;

    float red = 1.0f;
    float green = 0.2f;
    float blue = 0.1f;
    float alpha = 1.0f;

    logger.info(">>> render");
    // Render the shape given
    renderService.changeShape(cube);

    Thread.sleep(3000);

    logger.info(">>> zoom in");
    // Zoom into the render
    renderService.zoom(zoomInX, zoomInY, zoomInZ);

    Thread.sleep(1000);

    logger.info(">>> rotate");
    // Rotate the shape vertically and horizontally
    renderService.rotate(rotationAngle, rotationX, rotationY, rotationZ);

    Thread.sleep(1000);

    logger.info(">>> zoom out");
    // Zoom out of the render
    renderService.zoom(zoomOutX, zoomOutY, zoomOutZ);

    Thread.sleep(1000);

    logger.info(">>> change color");
    // Change the color of the shape
    renderService.changeColor(red, green, blue, alpha);
  }
}
