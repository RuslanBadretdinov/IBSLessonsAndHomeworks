package Homework001_PresentBox;

public class Chocolate extends Sweetness{
    private double secondUnique;

    public Chocolate(String name, float weight, float price, double secondUnique) {
        super(name, weight, price);
        this.secondUnique = secondUnique;
    }

    @Override
    public Object getChildUnique() {
        return secondUnique;
    }

    @Override
    public void setChildUnique(Object object) {
        this.secondUnique = (double) object;
    }
}
