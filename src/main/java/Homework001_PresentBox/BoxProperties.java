package Homework001_PresentBox;

public interface BoxProperties {
    void add(Sweetness sweetness);
    void remove(int index);
    void removeLast();
    float getWeight();
    float getPrice();
    void getInfoAboutAllObjects();
    void removeTheSmallestPrice();
    void removeTheSmallestWeight();
    void optimizeByBoxWeight(float weight);
    void optimizeByBoxPrice(float weight);
}
