package Homework001_PresentBox;

public class Candy extends Sweetness{
    private int secondUnique;

    public Candy(String name, float weight, float price, int secondUnique) {
        super(name, weight, price);
        this.secondUnique = secondUnique;
    }

    @Override
    public Object getChildUnique() {
        return secondUnique;
    }

    @Override
    public void setChildUnique(Object object) {
        this.secondUnique = (int) object;
    }
}
