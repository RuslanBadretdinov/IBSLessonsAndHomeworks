package Homework001_PresentBox;

public class Marshmallow extends Sweetness{
    private char secondUnique;

    public Marshmallow(String name, float weight, float price, char secondUnique) {
        super(name, weight, price);
        this.secondUnique = secondUnique;
    }

    @Override
    public Object getChildUnique() {
        return secondUnique;
    }

    @Override
    public void setChildUnique(Object object) {
        this.secondUnique = (char) object;
    }
}
