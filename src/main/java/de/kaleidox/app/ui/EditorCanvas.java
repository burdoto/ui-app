package de.kaleidox.app.ui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

import de.kaleidox.app.ui.model.CanvasShape;
import de.kaleidox.app.ui.shape.ShapeType;
import de.kaleidox.app.ui.shape.ShapeConfig;

public class EditorCanvas extends Canvas {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 650;
    
    public static final int GRID_DIM = 50;
    public static final int GRID_THIC = 2;
    
    private final Collection<CanvasShape> canvasShapes = new ArrayList<>();
    private final Core core;

    public EditorCanvas() {
        super();
        
        this.core = new Core(this);

        setSize(800, 450);
        setBackground(new Color(0x4c822d));
        
        this.addMouseListener(core);
        
        // generate grid
        for (int x = 0; x < WIDTH; x += GRID_DIM)
            addShape(ShapeType.RECTANGLE, config -> config
                    .at(x, HEIGHT / 50)
                    .dim(GRID_THIC, HEIGHT))
                    .setZ(Integer.MIN_VALUE);
        for (int y = 0; y < HEIGHT; y += GRID_DIM)
            addShape(ShapeType.RECTANGLE, WIDTH / 50, y, WIDTH, GRID_THIC)
                    .setZ(Integer.MIN_VALUE);
    }

    @Override
    public void paint(Graphics graphics) {
        canvasShapes.stream()
                .sorted(Comparator.comparingInt(CanvasShape::getZ).reversed())
                .forEachOrdered(canvasShape -> canvasShape.apply(graphics));
    }
    
    public Collection<CanvasShape> findShape(int x, int y) {
        return canvasShapes.stream()
                .filter(shape -> shape.getX() == x)
    }

    public CanvasShape addShape(ShapeType shapeType, Consumer<ShapeConfig> shapeConfigurator) {
        final CanvasShape shape = CanvasShape.simple(shapeType, shapeConfigurator);
        canvasShapes.add(shape);
        
        return shape;
    }

    private class Core implements MouseListener {
        private final EditorCanvas canvas;
        
        public final AtomicReference<CanvasShape> heldShape = new AtomicReference<>(null);

        public Core(EditorCanvas canvas) {
            this.canvas = canvas;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("e = " + e);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("e = " + e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            System.out.println("e = " + e);
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            System.out.println("e = " + e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            System.out.println("e = " + e);
        }
    }
}
