package de.hhn.it.devtools.apis.examples.coffeemakerservice;

/**
 * Callback to notify observers about a state change of a coffee maker.
 */
public interface CoffeeMakerListener {
  void newState(State state);
}
