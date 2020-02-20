package de.kaleidox.app.math;

import java.util.Arrays;

import static java.lang.Math.pow;
import static java.lang.Math.sqrt;

public final class GeometryHelper2D {
    public static final int X_COORD = 0;
    public static final int Y_COORD = 1;
    
    @SuppressWarnings("SwitchStatementWithTooFewBranches")
    public static double area(int[][] poly) {
        switch (poly.length) {
            case 3:
                int[][] base = Arrays.copyOfRange(poly, 0, 1);
                
                return 0.5 * dist(base) * height(poly, 0);
        }
        
        throw new IllegalArgumentException("Invalid number of coordinates. Array: " + Arrays.deepToString(poly));
    }
    
    public static double height(int[][] poly, int baseSideIndex) {
        final int[][] base = Arrays.copyOfRange(poly, baseSideIndex, baseSideIndex + 1);
        
        // todo: i am stupid
        return 0;
    }
    
    public static double dist(int[][] xy) {
        if (xy.length != 2)
            throw new IllegalArgumentException("Illegal number of coordinates. Array: " + Arrays.deepToString(xy));
        
        return sqrt(pow(xy[1][X_COORD] - xy[0][X_COORD], 2) + pow(xy[1][Y_COORD] - xy[0][Y_COORD], 2));
    }
}
