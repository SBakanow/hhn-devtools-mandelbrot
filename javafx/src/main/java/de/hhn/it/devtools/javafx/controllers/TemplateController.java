package de.hhn.it.devtools.javafx.controllers;

import static org.lwjgl.opengl.GL11.*;

import com.huskerdev.openglfx.DirectDrawPolicy;
import com.huskerdev.openglfx.OpenGLCanvas;
import com.huskerdev.openglfx.lwjgl.LWJGLCanvasKt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.lwjgl.opengl.GL11;

public class TemplateController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
      org.slf4j.LoggerFactory.getLogger(TemplateController.class);

  @FXML
  AnchorPane controlAnchorPane;

  Pane stage;

  private float counter = 0;

  public TemplateController() {
    logger.debug("Template Controller created. Hey, if you have copied me, update this message!");
    stage = createGL();
  }

  Pane createGL() {
    var canvas = OpenGLCanvas.create(LWJGLCanvasKt.LWJGL_MODULE, DirectDrawPolicy.NEVER);
    canvas.createTimer(200.0);
    canvas.setMinHeight(300);
    canvas.setMinWidth(300);
    canvas.onReshape(() -> {
      glMatrixMode(GL_PROJECTION);
      glLoadIdentity();
      glOrtho(0.0, canvas.getScene().getWidth(), 0.0, canvas.getScene().getHeight(), -1.0, 100.0);
    });
    canvas.onRender(() -> {
      GL11.glClearColor(counter, 0.0f, 0.0f, 1.0f);
      GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
      counter += 0.02;
      if (counter >= 1) {
        counter = 0;
      }
    });
    return canvas;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (stage == null) {
      logger.debug("Not null");
    }
    controlAnchorPane.getChildren().add(stage);
    Platform.runLater(() -> {
      logger.debug("{} {}", stage.getWidth(), stage.getHeight());
    });
  }
}
