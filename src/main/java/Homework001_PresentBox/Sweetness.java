package Homework001_PresentBox;

public abstract class Sweetness {
    private String name;
    private float weight;
    private float price;

    public Sweetness(String name, float weight, float price) {
        if (weight > 0 && price > 0 && name != null) {
            this.name = name;
            this.weight = weight;
            this.price = price;
        } else {
            if (weight <= 0) throw new IllegalArgumentException("\nБыло получено неправильное значение веса, поле weight:"+weight+".\n у объекта "+super.toString());
            else if (price <= 0) throw new IllegalArgumentException("\nБыло получено неправильное значение цены, поле price:"+price+".\n у объекта "+super.toString());
            else if (name == null) throw new NullPointerException("\nБыло получено значение null для названия сладости, поле name:"+".\n у объекта "+super.toString());
        }
    }

    public Sweetness(String name, float price) {
        this.name = name;
        this.price = price;
    }

    private Sweetness() {
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public float getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public abstract Object getChildUnique();
    public abstract void setChildUnique(Object object);

    @Override
    public String toString() {
        return this.getClass().getSimpleName()+"{название: "+this.getName()+", вес: "+this.getWeight()+", цена: "+this.getPrice()+", уникальный параметр: "+this.getChildUnique()+"}";
    }
}
