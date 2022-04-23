package de.hhn.it.devtools.javafx.controllers.renderer;

import static org.lwjgl.opengl.GL11.GL_FALSE;
import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL11.GL_MODELVIEW;
import static org.lwjgl.opengl.GL11.GL_PROJECTION;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_INT;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL11.glLoadIdentity;
import static org.lwjgl.opengl.GL11.glMatrixMode;
import static org.lwjgl.opengl.GL11.glOrtho;
import static org.lwjgl.opengl.GL11.glRotatef;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_ELEMENT_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_STATIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.GL_COMPILE_STATUS;
import static org.lwjgl.opengl.GL20.GL_FRAGMENT_SHADER;
import static org.lwjgl.opengl.GL20.GL_INFO_LOG_LENGTH;
import static org.lwjgl.opengl.GL20.GL_LINK_STATUS;
import static org.lwjgl.opengl.GL20.GL_VERTEX_SHADER;
import static org.lwjgl.opengl.GL20.glAttachShader;
import static org.lwjgl.opengl.GL20.glCompileShader;
import static org.lwjgl.opengl.GL20.glCreateProgram;
import static org.lwjgl.opengl.GL20.glCreateShader;
import static org.lwjgl.opengl.GL20.glDisableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glGetProgramInfoLog;
import static org.lwjgl.opengl.GL20.glGetProgrami;
import static org.lwjgl.opengl.GL20.glGetShaderInfoLog;
import static org.lwjgl.opengl.GL20.glGetShaderi;
import static org.lwjgl.opengl.GL20.glLinkProgram;
import static org.lwjgl.opengl.GL20.glShaderSource;
import static org.lwjgl.opengl.GL20.glUseProgram;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

import com.huskerdev.openglfx.DirectDrawPolicy;
import com.huskerdev.openglfx.OpenGLCanvas;
import com.huskerdev.openglfx.lwjgl.LWJGLCanvasKt;
import de.hhn.it.devtools.javafx.controllers.Controller;
import java.net.URL;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.lwjgl.BufferUtils;

public class RenderController extends Controller implements Initializable {

  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(
          de.hhn.it.devtools.javafx.controllers.renderer.RenderController.class);

  @FXML
  AnchorPane controlAnchorPane;

  Pane stage;

  private float counter = 0;
  private float red = 0;
  private float green = 0;
  private float blue = 0;

  // Test -----------------------------------------------------------------------------------------
  private String vertexShaderSrc = "#version 330 core\n" +
      "layout (location=0) in vec3 aPos;\n" +
      "layout (location=1) in vec4 aColor;\n" +
      "\n" +
      "out vec4 fColor;\n" +
      "\n" +
      "void main()\n" +
      "{\n" +
      "    fColor = aColor;\n" +
      "    gl_Position = vec4(aPos, 1.0);\n" +
      "}";

  private String fragmentShaderSrc = "#version 330 core\n" +
      "\n" +
      "in vec4 fColor;\n" +
      "\n" +
      "out vec4 color;\n" +
      "\n" +
      "void main()\n" +
      "{\n" +
      "    color = fColor;\n" +
      "}";

  private int vertexID, fragmentID, shaderProgram;

  private float[] vertexArray = {
      // position            //color
       0.5f, -0.5f, 0.0f,    1.0f, 0.0f, 0.0f, 1.0f,   // Bottom right 0
      -0.5f,  0.5f, 0.0f,    0.0f, 1.0f, 0.0f, 1.0f,   // Top left     1
       0.5f,  0.5f, 0.0f,    0.0f, 0.0f, 1.0f, 1.0f,   // Top right    2
      -0.5f, -0.5f, 0.0f,    1.0f, 1.0f, 0.0f, 1.0f    // Bottom left  3
  };

  // IMPORTANT: Must be in counter-clockwise order
  private int[] elementArray = {
      /*
              1     2

              3     0
       */
      2, 1, 0,   // Top right triangle
      0, 1, 3    // Bottom left triangle
  };

  private int vaoID, vboID, eboID;
  // Test -----------------------------------------------------------------------------------------

  public RenderController() {
    logger.debug("Template Controller created. Hey, if you have copied me, update this message!");
    stage = createGL();
  }

  Pane createGL() {
    var canvas = OpenGLCanvas.create(LWJGLCanvasKt.LWJGL_MODULE, DirectDrawPolicy.NEVER);
    canvas.createTimer(200.0);
    canvas.setMinWidth(1280);
    canvas.setMinHeight(720);
    canvas.onReshape(() -> {
      glMatrixMode(GL_PROJECTION);
      glLoadIdentity();
      glOrtho(0.0, canvas.getScene().getWidth(), 0.0, canvas.getScene().getHeight(), -1.0, 100.0);
    });
    canvas.onInitialize(() -> {
      // Test ----------------------------------------------------------------------------------------
      vertexID = glCreateShader(GL_VERTEX_SHADER);
      glShaderSource(vertexID, vertexShaderSrc);
      glCompileShader(vertexID);

      int success = glGetShaderi(vertexID, GL_COMPILE_STATUS);
      if (success == GL_FALSE) {
        int len = glGetShaderi(vertexID, GL_INFO_LOG_LENGTH);
        System.out.println("ERROR: 'defaultShader.glsl'\n\tVertex shader compilation failed.");
        System.out.println(glGetShaderInfoLog(vertexID, len));
      }

      fragmentID = glCreateShader(GL_FRAGMENT_SHADER);
      glShaderSource(fragmentID, fragmentShaderSrc);
      glCompileShader(fragmentID);

      success = glGetShaderi(fragmentID, GL_COMPILE_STATUS);
      if (success == GL_FALSE) {
        int len = glGetShaderi(fragmentID, GL_INFO_LOG_LENGTH);
        System.out.println("ERROR: 'defaultShader.glsl'\n\tfragment shader compilation failed.");
        System.out.println(glGetShaderInfoLog(fragmentID, len));
      }

      shaderProgram = glCreateProgram();
      glAttachShader(shaderProgram, vertexID);
      glAttachShader(shaderProgram, fragmentID);
      glLinkProgram(shaderProgram);

      success = glGetProgrami(shaderProgram, GL_LINK_STATUS);
      if (success == GL_FALSE) {
        int len = glGetProgrami(shaderProgram, GL_INFO_LOG_LENGTH);
        System.out.println("ERROR: 'defaultShader.glsl'\n\tlinking of shaders failed.");
        System.out.println(glGetProgramInfoLog(shaderProgram, len));
      }

      vaoID = glGenVertexArrays();
      glBindVertexArray(vaoID);

      // Greate a float buffer of vertices
      FloatBuffer vertexBuffer = BufferUtils.createFloatBuffer(vertexArray.length);
      vertexBuffer.put(vertexArray).flip();

      // Create VBO and upload vertex buffer
      vboID = glGenBuffers();
      glBindBuffer(GL_ARRAY_BUFFER, vboID);
      glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);

      // Create the indices and upload
      IntBuffer elementBuffer = BufferUtils.createIntBuffer(elementArray.length);
      elementBuffer.put(elementArray).flip();

      eboID = glGenBuffers();
      glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, eboID);
      glBufferData(GL_ELEMENT_ARRAY_BUFFER, elementBuffer, GL_STATIC_DRAW);

      // Add the vertex attribute pointers
      int positionsSize = 3;
      int colorSize = 4;
      int floatSizeInBytes = 4;
      int vertexSizeInBytes = (positionsSize + colorSize) * floatSizeInBytes;
      glVertexAttribPointer(0, positionsSize, GL_FLOAT, false, vertexSizeInBytes, 0);
      glEnableVertexAttribArray(0);

      glVertexAttribPointer(1, colorSize, GL_FLOAT, false, vertexSizeInBytes, positionsSize * floatSizeInBytes);
      glEnableVertexAttribArray(1);
      // Test ----------------------------------------------------------------------------------------
    });
    canvas.onRender(() -> {
      // Bind shader program
      glUseProgram(shaderProgram);

      // Bind the VAO that we're using
      glBindVertexArray(vaoID);

      // Enable the vertex attribute pointers
      glEnableVertexAttribArray(0);
      glEnableVertexAttribArray(1);

      glDrawElements(GL_TRIANGLES, elementArray.length, GL_UNSIGNED_INT, 0);

      glRotatef(1.0f, 1.0f, 0.0f, 0.0f);

      // Unbind everything
      glDisableVertexAttribArray(0);
      glDisableVertexAttribArray(1);

      glBindVertexArray(0);

      glUseProgram(0);
    });
    return canvas;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (stage == null) {
      logger.debug("Not null");
    }
    controlAnchorPane.getChildren().add(stage);
  }
}


