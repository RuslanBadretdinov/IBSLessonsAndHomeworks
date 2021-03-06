package Homework001_PresentBox;

public class PresentBox implements BoxProperties {
    private Sweetness[] sweetnessArray;
    private float currentWeight;

    public static void main(String[] args) {
        Sweetness[] sweetnessArray = new Sweetness[10];
        try {
            sweetnessArray = new Sweetness[]{
                    new Chocolate("Щедрая душа", 150.5f, 131.9f, 35d),
                    new Chocolate("Шоколадозаменитель", 100.9f, 0.1f, 1d),
                    new Candy("Ириски", 0.2f, 100.1f, 3),
                    new Candy("Мармеладные конфеты", 110f, 104.9f, 1),
                    new Marshmallow("Зефир", 155f, 70f, 'A'),
                    new Chocolate("Горький и не вкусный шоколад", 90.9f, 1000f, 1000d),
                    new Marshmallow("Marshmallow", 123f, 100f, 'A'),
                    null,
                    null,
                    null
            };
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        PresentBox presentBox = new PresentBox(sweetnessArray);
        presentBox.getInfoAboutAllObjects();
        presentBox.remove(5);

        try {
            presentBox.add(new Chocolate("Alpen Gold", 90.9f, 100f, 777d));
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }

        try {
            presentBox.add(new Marshmallow("Последний на удаление", 0.01f, 0.03f, 'z'));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        presentBox.removeLast();
        presentBox.getWeight();
        presentBox.getPrice();

        try {
            presentBox.add(new Marshmallow(null, 0.01f, 0.03f, 'z'));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            presentBox.add(new Marshmallow("Тест_1", -1000.01f, 0.03f, 'z'));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        try {
            presentBox.add(new Marshmallow("Test_2", 0.01f, -10000.03f, 'z'));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        presentBox.getInfoAboutAllObjects();

        presentBox.optimizeByBoxWeight(500);
        presentBox.optimizeByBoxPrice(170);

        presentBox.getInfoAboutAllObjects();
        presentBox.getWeight();
        presentBox.getPrice();
    }

    public PresentBox(Sweetness[] sweetnessArray) {
        this.sweetnessArray = sweetnessArray;
    }

    @Override
    public void add(Sweetness sweetness) {
        int index = -1;
        for (int i = 0; i < sweetnessArray.length; i++) {
            if (sweetnessArray[i] == null) {
                index = i;
                sweetnessArray[i] = sweetness;
                break;
            }
        }
        System.out.println("\nОперация добавления в первую свободную ячейку:\n\t"+(index == -1 ? "Все места для сладостей уже заняты, либо в коробке 0 ячеек" : "№"+index+"; "+sweetnessArray[index].toString()));
    }

    @Override
    public void remove(int index) {
        if (this.sweetnessArray[index] != null) {
            System.out.println("\nОперация удаления сладости с №"+index+"\n\t"+"№"+index+"; "+sweetnessArray[index].toString());
        } else {
            System.out.println("\nОперация удаления сладости с №"+index+"\n\t"+"Данной сладости уже не было в ячейке");
        }
        this.sweetnessArray[index] = null;
    }

    @Override
    public void removeLast() {
        System.out.println("\nОперация удаления сладости последней ячейки:");
        boolean isRemove = false;
        for (int i = sweetnessArray.length-1;i > -1; i--) {
            if (sweetnessArray[i] != null) {
                System.out.println("\tБыл определён номер последней ячейки, заполненной сладостью: №"+i+". Вызывается операция удаления ячейки №"+i);
                this.remove(i);
                isRemove = true;
                break;
            }
        }
        if (!isRemove) {
            System.out.println("\tВаши ячейки в коробке со сладостями - пусты, либо коробка не имеет ячеек. Операция удаления сладости последней ячейки не выполнена");
        }
    }

    @Override
    public float getWeight() {
        float sum = 0;
        for (Sweetness sweetness : sweetnessArray) {
            if (sweetness != null) {
                sum+= sweetness.getWeight();
            }
        }
        System.out.println("\nВывод значения веса содержимого коробки:\n\t"+sum+" условных единиц измерения массы");
        return sum;
    }

    @Override
    public float getPrice() {
        float sum = 0;
        for (Sweetness sweetness : sweetnessArray) {
            if (sweetness != null) {
                sum+= sweetness.getPrice();
            }
        }
        System.out.println("\nВывод значения стоимости содержимого коробки:\n\t"+sum+" условных денежных единиц");
        return sum;
    }

    @Override
    public void getInfoAboutAllObjects() {
        System.out.println("\nВывод информации о содержимых сладостях в коробке");
        for (int i = 0; i < sweetnessArray.length; i++) {
            if (sweetnessArray[i] != null) {
                System.out.println("\t№"+i+"; "+sweetnessArray[i].toString());
            } else {
                System.out.println("\t№"+i+"; "+"Свободная ячейка для сладости");
            }
        }
    }

    @Override
    public void removeTheSmallestPrice() {
        System.out.println("\nОперация удаления сладости из ячейки коробки с наименьшей ценой:");
        int theSmallestPriceIndex = -1;
        double currentValue = Double.MAX_VALUE;
        for (int i = 0; i < sweetnessArray.length; i++) {
            if (sweetnessArray[i] != null && sweetnessArray[i].getPrice() < currentValue) {
                currentValue = sweetnessArray[i].getPrice();
                theSmallestPriceIndex = i;
            }
        }
        if (theSmallestPriceIndex != -1) {
            System.out.println("\tБыл определён номер ячейки с наименьшей ценой: №"+theSmallestPriceIndex+". Вызывается операция удаления ячейки №"+theSmallestPriceIndex);
            this.remove(theSmallestPriceIndex);
        } else {
            System.out.println("\tВаши ячейки в коробке со сладостями - пусты, либо коробка не имеет ячеек. Операция удаления сладости последней ячейки не выполнена");
        }
    }

    @Override
    public void removeTheSmallestWeight() {
        System.out.println("\nОперация удаления сладости из ячейки коробки с наименьшим весом:");
        int theSmallestWeightIndex = -1;
        double currentValue = Double.MAX_VALUE;
        for (int i = 0; i < sweetnessArray.length; i++) {
            if (sweetnessArray[i] != null && sweetnessArray[i].getWeight() < currentValue) {
                currentValue = sweetnessArray[i].getWeight();
                theSmallestWeightIndex = i;
            }
        }
        if (theSmallestWeightIndex != -1) {
            System.out.println("\tБыл определён номер ячейки с наименьшим весом: №"+theSmallestWeightIndex+". Вызывается операция удаления ячейки №"+theSmallestWeightIndex);
            this.remove(theSmallestWeightIndex);
        } else {
            System.out.println("\tВаши ячейки в коробке со сладостями - пусты, либо коробка не имеет ячеек. Операция удаления сладости последней ячейки не выполнена");
        }
    }

    @Override
    public void optimizeByBoxWeight(float weight) {
        System.out.println("\nОперация оптимизации коробки по весу до "+weight+" условных единиц измерения массы путём поочерёдного удаления сладости с наименьшим весом:");
        if (weight < 0) System.out.println("\tОшибка ввода данных: была введена масса коробки меньше нуля");
        else {
            while (this.getWeight() >= weight) {
                removeTheSmallestWeight();
            }
            System.out.println("\tОперация по оптимизации коробки по весу - выполнена");
        }
    }

    @Override
    public void optimizeByBoxPrice(float price) {
        System.out.println("\nОперация оптимизации коробки по цене до "+price+" условных денежных единиц путём поочерёдного удаления сладости с наименьшей ценой:");
        if (price < 0) System.out.println("\tОшибка ввода данных: была введена цена коробки меньше нуля");
        else {
            while (this.getPrice() >= price) {
                removeTheSmallestPrice();
            }
            System.out.println("\tОперация по оптимизации коробки по цене - выполнена");
        }
    }
}
