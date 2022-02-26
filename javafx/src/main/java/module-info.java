module devtools.javafx {
  requires org.slf4j;
  requires devtools.apis;
  requires devtools.components;
  requires javafx.controls;
  requires javafx.fxml;
  uses de.hhn.it.devtools.apis.examples.coffeemakerservice.CoffeeMakerService;
  uses de.hhn.it.devtools.apis.examples.coffeemakerservice.AdminCoffeeMakerService;
  opens de.hhn.it.pp.javafx.controllers to javafx.fxml;
  opens de.hhn.it.pp.javafx.controllers.coffeemaker to javafx.fxml;
  exports de.hhn.it.pp.javafx;
  exports de.hhn.it.pp.javafx.controllers;
  exports de.hhn.it.pp.javafx.controllers.coffeemaker;
        }