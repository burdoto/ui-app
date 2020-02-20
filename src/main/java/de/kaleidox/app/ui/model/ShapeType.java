package de.kaleidox.app.ui.model;

import java.awt.Graphics;
import java.awt.Point;
import java.util.Objects;
import java.util.function.Function;

import de.kaleidox.app.math.GeometryHelper2D;

import static de.kaleidox.app.ui.model.CanvasShape.X_COORD;
import static de.kaleidox.app.ui.model.CanvasShape.Y_COORD;

public enum ShapeType {
    RECTANGLE {
        @Override
        public void apply(Graphics graphics, int... args) {
            graphics.fillRect(args[X_COORD], args[Y_COORD], args[2], args[3]);
        }

        @Override
        public void overlaps(Point where, int... args) {
            super.overlaps(where, args);
            GeometryHelper2D.sumArea()
        }
    },
    SQUARE(RECTANGLE, args -> new int[]{args[X_COORD], args[Y_COORD], args[2], args[2]}),
    CIRCLE {
        @Override
        public void apply(Graphics graphics, int... args) {
            graphics.drawOval(args[0], args[X_COORD], args[Y_COORD], args[3]);
        }

        @Override
        public void overlaps(Point where, int... args) {
            super.overlaps(where, args);
        }
    };

    private final ShapeType proxyTarget;
    private final Function<int[], int[]> argsProcessor;

    ShapeType() {
        proxyTarget = null;
        argsProcessor = null;
    }

    ShapeType(ShapeType proxyTarget, Function<int[], int[]> argsProcessor) {
        this.proxyTarget = proxyTarget;
        this.argsProcessor = argsProcessor;
    }

    public void apply(Graphics graphics, int... args) {
        if (proxyTarget != null)
            proxyTarget.apply(graphics, Objects.requireNonNull(argsProcessor).apply(args));
        
        throw new AssertionError(new AbstractMethodError("Method apply is abstract on " + this.getClass().getName()));
    }

    public void overlaps(Point where, int... args) {
        if (proxyTarget != null)
            proxyTarget.overlaps(where, Objects.requireNonNull(argsProcessor).apply(args));
        
        throw new AssertionError(new AbstractMethodError("Method overlaps is abstract on " + this.getClass().getName()));
    }
}
