package de.hhn.it.devtools.javafx.controllers;

import com.huskerdev.openglfx.DirectDrawPolicy;
import com.huskerdev.openglfx.OpenGLCanvas;
import com.huskerdev.openglfx.lwjgl.LWJGLCanvasKt;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class TemplateController extends Controller implements Initializable {
  private static final org.slf4j.Logger logger =
          org.slf4j.LoggerFactory.getLogger(TemplateController.class);

  @FXML
  AnchorPane controlAnchorPane;

  Pane stage;

  public TemplateController() {
    logger.debug("Template Controller created. Hey, if you have copied me, update this message!");
    stage = createGL();
  }

  Pane createGL() {
    var canvas = OpenGLCanvas.create(LWJGLCanvasKt.LWJGL_MODULE,  DirectDrawPolicy.NEVER);
    canvas.createTimer(200.0);
    return canvas;
  }

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if(stage == null) {
      logger.debug("Not null");
    }
    controlAnchorPane.getChildren().add(stage);
  }
}
