package Lesson001.Figures;

public class Figure {
    protected int a;
    protected int h;
    protected float s;

    public Figure(int a, int b) {
        this.a = a;
        this.h = b;
        this.s = a*b;
    }

    public float getS() {
        return s;
    }
}
