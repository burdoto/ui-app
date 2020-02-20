package de.kaleidox.app.ui.shape;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import java.util.function.Consumer;

import de.kaleidox.app.math.GeometryHelper2D;

import static de.kaleidox.app.ui.shape.ShapeConfig.DIMENSION;
import static de.kaleidox.app.ui.shape.ShapeConfig.HEIGHT;
import static de.kaleidox.app.ui.shape.ShapeConfig.WIDTH;
import static de.kaleidox.app.ui.shape.ShapeConfig.X;
import static de.kaleidox.app.ui.shape.ShapeConfig.Y;

public abstract class ShapeType {
    public static final ShapeType RECTANGLE = new ShapeType() {
        @Override
        public void apply(Graphics graphics, ShapeConfig args) {
            final int[] pos = args.getPos();
            final int[] dim = args.getDim();
            
            if (args.isFill()) 
                graphics.fillRect(pos[X], pos[Y], dim[WIDTH], dim[HEIGHT]);
            else graphics.drawRect(pos[X], pos[Y], dim[WIDTH], dim[HEIGHT]);
        }

        @Override
        public void overlaps(Point where, ShapeConfig args) {
            final int[] pos = args.getPos();
            final int[] dim = args.getDim();
            
            int areaAPD = GeometryHelper2D.area(new int[][]{
                    
            });
        }
    };
    public static final ShapeType SQUARE = new ShapeType.Proxy(RECTANGLE, args -> {
        final int[] dim = args.getDim();
        
        dim[HEIGHT] = dim[DIMENSION];
    });
    public static final ShapeType POLYGON = new ShapeType() {
        @Override
        public void apply(Graphics graphics, ShapeConfig args) {
            graphics.drawRect();
        }

        @Override
        public void overlaps(Point where, ShapeConfig args) {
            super.overlaps(where, args);
        }
    };
    public static final ShapeType CIRCLE = new ShapeType() {
        @Override
        public void apply(Graphics graphics, ShapeConfig args) {
            graphics.drawOval(args[0], args[X], args[Y], args[3]);
        }

        @Override
        public void overlaps(Point where, ShapeConfig args) {
            super.overlaps(where, args);
        }
    };

    private final ShapeType proxyTarget;
    private final Consumer<ShapeConfig> argsProcessor;

    ShapeType() {
        proxyTarget = null;
        argsProcessor = null;
    }

    ShapeType(ShapeType proxyTarget, Consumer<ShapeConfig> argsProcessor) {
        this.proxyTarget = Objects.requireNonNull(proxyTarget);
        this.argsProcessor = Objects.requireNonNull(argsProcessor);
    }

    public abstract void apply(Graphics graphics, ShapeConfig args);

    public abstract void overlaps(Point where, ShapeConfig args);

    private static class Proxy extends ShapeType {
        private final ShapeType proxyTarget;
        private final Consumer<ShapeConfig> reconfigurer;

        public Proxy(ShapeType proxyTarget, Consumer<ShapeConfig> reconfigurer) {
            this.proxyTarget = proxyTarget;
            this.reconfigurer = reconfigurer;
        }

        @Override
        public void apply(Graphics graphics, ShapeConfig args) {
            proxyTarget.apply(graphics, args);
        }

        @Override
        public void overlaps(Point where, ShapeConfig args) {
            proxyTarget.overlaps(where, args);
        }
    }
}
