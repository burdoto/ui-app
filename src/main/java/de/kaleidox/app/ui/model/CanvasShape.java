package de.kaleidox.app.ui.model;

import java.awt.Graphics;
import java.util.function.Consumer;

import de.kaleidox.app.ui.shape.ShapeConfig;
import de.kaleidox.app.ui.shape.ShapeType;

import static de.kaleidox.app.ui.shape.ShapeConfig.X;
import static de.kaleidox.app.ui.shape.ShapeConfig.Y;

public class CanvasShape {
    private final ShapeType shapeType;
    private final ShapeConfig config;
    
    private int z = 0;

    public CanvasShape(ShapeType shapeType, Consumer<ShapeConfig> configurator) {
        this.shapeType = shapeType;
        this.config = new ShapeConfig();
        
        configurator.accept(this.config);
    }

    public void apply(Graphics graphics) {
        shapeType.apply(graphics, config);
    }

    public ShapeConfig getShapeDim() {
        return config;
    }

    public int getX() {
        return config.getPos()[X];
    }
    
    public CanvasShape setX(int x) {
        this.config.getPos()[X] = x;
        
        return this;
    }
    
    public int getY() {
        return config.getPos()[Y];
    }
    
    public CanvasShape setY(int y) {
        this.config.getPos()[Y] = y;
        
        return this;
    }
    
    public int getZ() {
        return z;
    }

    public CanvasShape setZ(int z) {
        this.z = z;
        
        return this;
    }

    public static CanvasShape simple(ShapeType shapeType, Consumer<ShapeConfig> configurator) {
        return new CanvasShape(shapeType, configurator);
    }
}
