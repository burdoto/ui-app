package de.kaleidox.app.ui.shape;

public class ShapeConfig {
    //region Array indices
    public static final int X = 0;
    public static final int Y = 1;
    
    public static final int DIMENSION = 0;
    public static final int WIDTH = 0;
    public static final int HEIGHT = 1;
    //endregion
    
    private int x = 0;
    private int y = 0;

    private int[] dim = new int[]{0, 0};
    
    private boolean fill = true;

    public ShapeConfig at(int x, int y) {
        this.x = x;
        this.y = y;

        return this;
    }

    public int[] getPos() {
        return new int[]{x, y};
    }

    /**
     * Usage Profiles:
     * - dim(int dimension)
     * - dim(int width, int height)
     * 
     * @param dim
     * @return
     */
    public ShapeConfig dim(int... dim) {
        this.dim = dim;

        return this;
    }
    
    public int[] getDim() {
        return dim;
    }

    public boolean isFill() {
        return fill;
    }

    public void setFill(boolean fill) {
        this.fill = fill;
    }
}
