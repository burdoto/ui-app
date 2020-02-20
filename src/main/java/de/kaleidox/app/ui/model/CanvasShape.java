package de.kaleidox.app.ui.model;

import java.awt.Graphics;

import static de.kaleidox.app.math.GeometryHelper2D.X_COORD;
import static de.kaleidox.app.math.GeometryHelper2D.Y_COORD;

public class CanvasShape {
    private final ShapeType shapeType;
    private final int[] args;
    
    private int z = 0;

    public CanvasShape(ShapeType shapeType, int[] args) {
        this.shapeType = shapeType;
        this.args = args;
    }

    public void apply(Graphics graphics) {
        shapeType.apply(graphics, args);
    }

    public int getX() {
        return args[X_COORD];
    }
    
    public CanvasShape setX(int x) {
        this.args[X_COORD] = x;
        
        return this;
    }
    
    public int getY() {
        return args[Y_COORD];
    }
    
    public CanvasShape setY(int y) {
        this.args[Y_COORD] = y;
        
        return this;
    }
    
    public int getZ() {
        return z;
    }

    public CanvasShape setZ(int z) {
        this.z = z;
        
        return this;
    }

    public static CanvasShape simple(ShapeType shapeType, int[] args) {
        return new CanvasShape(shapeType, args);
    }
}
