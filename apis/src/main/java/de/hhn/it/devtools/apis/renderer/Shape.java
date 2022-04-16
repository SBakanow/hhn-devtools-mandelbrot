package de.hhn.it.devtools.apis.renderer;

/**
 * Available shapes for which different parameters can be defined.
 */
public sealed interface Shape {
  /*// The Mandelbrot function is a recursive function: z^2 + c . Iteration start: z=0 .
  MANDELBROT,

  CUBE,

  // Random generated function (or parameters???) results in random fractals.
  RANDOM_FRACTAL,

  // Julia set is a recursion of a function.
  JULIA
  */

  final class Mandelbrot implements Shape {

  }

  final class Cube implements Shape {

  }

  final class Julia implements Shape {

  }

  final class RandomFractal implements Shape {

  }
}
