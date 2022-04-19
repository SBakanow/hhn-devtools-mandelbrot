package de.hhn.it.devtools.apis.renderer;

import de.hhn.it.devtools.apis.exceptions.IllegalParameterException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

/**
 * Listener interface which the JavaFxOpenGL renderer uses to get the updated buffer.
 * The float buffer provided by this interface will be used in the vertex buffer.
 */
public interface RenderListener {

  /**
   * The method gets called after a change of the shape.
   * Both parameters should always be @NotNull.
   *
   * @param vertexBuffer The buffer which will be used for the vertex buffer
   * @param indexBuffer The buffer which will be used for the index buffer
   *
   * @throws IllegalParameterException If either of the parameters are null
   */
  void updateVertexArrayObject(FloatBuffer vertexBuffer, IntBuffer indexBuffer) throws
      IllegalParameterException;

  /**
   * The method gets called after a change of the viewProjectionMatrix.
   *
   * @param viewProjectionMatrix The parameter is a 4x4 transformation matrix
   *                             OpenGL specifies it as a column major ordered matrix
   * @throws IllegalParameterException If the parameter is null or has != 16 elements
   */
  void updateViewProjectionMatrix(FloatBuffer viewProjectionMatrix) throws IllegalParameterException;
}
