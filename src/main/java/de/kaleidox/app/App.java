package de.kaleidox.app;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import de.kaleidox.app.ui.EditorCanvas;
import de.kaleidox.app.ui.shape.ShapeType;

public enum App implements Runnable {
    INSTANCE;

    private final JFrame frame;
    private final EditorCanvas canvas;

    App() {
        this.canvas = new EditorCanvas();

        this.frame = new JFrame("App");
        frame.add(canvas);
        frame.setSize(canvas.getSize());
   
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        // rectangles
        for (int[] rect : new int[][]{
                new int[]{20, 20, 80, 30},
                new int[]{20, 120, 80, 30}
        }) {
            canvas.addShape(ShapeType.RECTANGLE, config -> config
                    .at(rect[0], rect[1])
                    .dim(rect[2], rect[3]));
        }

        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        INSTANCE.run();
    }
}
