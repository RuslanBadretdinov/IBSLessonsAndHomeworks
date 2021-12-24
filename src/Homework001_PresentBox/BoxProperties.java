package Homework001_PresentBox;

public interface BoxProperties {
    void add(Sweetness sweetness);
    void remove(int index);
    void removeLast();
    void getWeight();
    void getPrice();
    void getInfoAboutAllObjects();
    void removeTheSmallestPrice();
    void removeTheSmallestWeight();
}
