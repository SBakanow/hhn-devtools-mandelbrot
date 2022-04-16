package de.hhn.it.devtools.apis.renderer;

/**
 * Available shapes/pattern of the renderer.
 */
public enum Shape {
  // The Mandelbrot function is a recursive function: z^2 + c . Iteration start: z=0 .
  MANDELBROT,

  CUBE,

  // Random generated function (or parameters???) results in random fractals.
  RANDOM_FRACTAL,

  // Julia set is a recursion of a function.
  JULIA
}
