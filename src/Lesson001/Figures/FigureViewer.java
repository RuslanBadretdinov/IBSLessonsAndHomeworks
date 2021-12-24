package Lesson001.Figures;

public class FigureViewer {
    public static void main(String[] args) {
        Figure t = new Trio(1,3);
        Figure r = new Romb(4, 5);
        Figure q = new Quadro(5, 5);
        System.out.println(t.getS());
        System.out.println(r.getS());
        System.out.println(q.getS());

    }
}