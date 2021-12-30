package Lesson002;

import Lesson001.Figures.Figure;
import Lesson001.Figures.Quadro;
import Lesson001.Figures.Romb;
import Lesson001.Figures.Trio;

import javax.swing.*;
import java.util.*;

public class CheckCollection {

    public static void main(String[] args) {
        ArrayList<Object> list = new ArrayList<>();
        Object object1 = new Object();
        list.add(object1);
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        list.add(new Object());
        Object object2 = object1;
        list.add(object2);

        System.out.println(list);
        System.out.println(checkElement(list, object1));
        System.out.println(haveRepeatedElements(list));

        System.out.println(returnOnlyNotRepeatedElements(list));
        System.out.println(haveRepeatedElements(returnOnlyNotRepeatedElements(list)));
        System.out.println(checkElement(returnOnlyNotRepeatedElements(list), object1));

    }

    public static boolean haveRepeatedElements(List<?> list) {
        Set<?> set = new HashSet<>(list);
        return set.size() < list.size();
    }

    public static List<?> returnOnlyNotRepeatedElements(List<?> list) {
        LinkedHashMap<Object, Object> map = new LinkedHashMap();
        for (Object object : list) {
           if (map.containsKey(object)) {
               map.put(object, 1);
           } else {
               map.put(object, null);
           }
        }
        List<Object> list2 = new ArrayList<>();
        for (Map.Entry<Object, Object> entry : map.entrySet())
        {
            if (entry.getValue() == null) {
                list2.add(entry.getKey());
            }
        }
        return list2;
    }
    public static boolean checkElement(List<? extends Object> list, Object t) {
        for (Object object : list) {
            if (t==object) {
                return true;
            }
        }
        return false;
    }

}
