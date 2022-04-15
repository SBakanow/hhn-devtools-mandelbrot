package de.hhn.it.devtools.apis.renderer;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;

/**
 * This RenderService is an interface for calculating the currently selected shape.
 * The representation is done in the JavaFxOpenGL Renderer with the data the interface provides.
 */
public interface RenderService {

  /**
   * In this function the currently selected shape gets calculated and preprocessed,
   * so it can be added into a Vertex.
   *
   * @param shape shape to be rendered
   * @throws IllegalParameterException if the shape does not exist
   */
  void render(Shape shape) throws IllegalParameterException;

  /**
   * Updates the zoom value, which the render function uses.
   *
   * @param zoomX specifies scale factors along the X axis
   * @param zoomY specifies scale factors along the Y axis
   * @param zoomZ specifies scale factors along the Z axis
   */
  void zoom(double zoomX, double zoomY, double zoomZ);

  /**
   * Updates the rotation values, which the render function uses.
   *
   * @param angle     specifies the angle of the rotation in degrees
   * @param rotationX currentRotation on the X axis
   * @param rotationY currentRotation on the Y axis
   * @param rotationZ currentRotation on the Z axis
   */
  void rotate(double angle, double rotationX, double rotationY, double rotationZ);

  /**
   * Updates the color values, which the render function uses.
   *
   * @param red   represents the red channel of the color spectre in OpenGL
   * @param green represents the green channel of the color spectre in OpenGL
   * @param blue  represents the blue channel of the color spectre in OpenGL
   * @param alpha represents the alpha channel of the color spectre in OpenGL
   * @throws IllegalParameterException if the values are out of range of the OpenGLColor standard [0,1]
   */
  void changeColor(float red, float green, float blue, float alpha)
      throws IllegalParameterException;

  /**
   * Adds a listener to get updates on the state of the render calculations.
   *
   * @param renderListener object implementing the listener interface
   * @throws IllegalParameterException if the listener is a null reference
   */
  void addCallback(RenderListener renderListener) throws IllegalParameterException;

  /**
   * Removes a listener.
   *
   * @param renderListener listener to be removed
   * @throws IllegalParameterException if the listener is a null reference
   */
  void removeCallback(RenderListener renderListener) throws IllegalParameterException;
}
