module devtools.javafx {
  requires org.slf4j;
  requires org.lwjgl;
  requires org.lwjgl.natives;
  requires org.lwjgl.glfw.natives;
  requires org.lwjgl.opengl.natives;
  requires org.lwjgl.glfw;
  requires devtools.apis;
  requires devtools.components;
  requires javafx.controls;
  requires javafx.fxml;
  requires org.lwjgl.opengl;
  requires lwjgl;
  requires kotlin.stdlib;
  requires org.joml;
  uses de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
  uses de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
  opens de.hhn.it.devtools.javafx.controllers to javafx.fxml;
  opens de.hhn.it.devtools.javafx.controllers.coffeemaker to javafx.fxml;
  opens de.hhn.it.devtools.javafx.controllers.renderer to javafx.fxml;
  exports de.hhn.it.devtools.javafx;
  exports de.hhn.it.devtools.javafx.controllers;
  exports de.hhn.it.devtools.javafx.controllers.coffeemaker;
}
