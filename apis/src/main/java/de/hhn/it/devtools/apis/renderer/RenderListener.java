package de.hhn.it.devtools.apis.renderer;

/**
 * Listener interface which the JavaFxOpenGL renderer uses to get the updated byte stream.
 * The byte stream provided by this interface will be used in the vertex buffer.
 */
public interface RenderListener {

  /**
   * The method that gets called after any change in the render service.
   * This method will be called after every new calculation.
   *
   * @param buffer The buffer which will be used for the vertex buffer
   */
  void updateVertexBuffer(byte[] buffer);
}
