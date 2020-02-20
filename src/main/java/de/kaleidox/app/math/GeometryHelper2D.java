package de.kaleidox.app.math;

import java.util.Arrays;
import java.util.stream.IntStream;

import de.kaleidox.app.ui.shape.ShapeConfig;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public final class GeometryHelper2D {

    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static double area(int[][] poly) {
        switch (poly.length) {
            case 3:
                // heron algorithm https://www.arndt-bruenner.de/mathe/9/herondreieck.htm
                final int[] sides = sides(poly);
                final int s = IntStream.of(sides).sum() / 2;
                
                return sqrt(s * (s - sides[0]) * (s - sides[1]) * (s - sides[2]));
        }
        
        throw new IllegalArgumentException("Invalid number of coordinates. Array: " + Arrays.deepToString(poly));
    }

    private static int[] sides(int[][] poly) {
        final int[] yields = new int[poly.length];
        
        for (int i = 0; i < poly.length; i++) {
            final int[] here = poly[i];
            final int[] next = poly[(i + 1) < poly.length ? i + 1 : 0];

            yields[i] = (int) dist(new int[][]{here, next});
        }
        
        return yields;
    }

    public static int around(int[][] poly) {
        double yield = 0;
        
        for (int i = 0; i < poly.length; i++) {
            final int[] here = poly[i];
            final int[] next = poly[(i + 1) < poly.length ? i + 1 : 0];
            
            yield += dist(new int[][]{here, next});
        }
        
        return (int) yield;
    }

    public static double dist(int[][] xy) {
        if (xy.length != 2)
            throw new IllegalArgumentException("Illegal number of coordinates. Array: " + Arrays.deepToString(xy));
        
        return sqrt(pow(xy[1][ShapeConfig.X] - xy[0][ShapeConfig.X], 2) + pow(xy[1][ShapeConfig.Y] - xy[0][ShapeConfig.Y], 2));
    }
}
