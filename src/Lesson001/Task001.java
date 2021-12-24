package Lesson001;

public class Task001 {

    public static void main(String[] args) {
        System.out.println(Task001.isWholeAverage(new int[]{1,2,3,4,5,6,7,8}));
        System.out.println(Task001.isWholeAverage(new int[]{1,1,1,1,1,1,1,1}));
    }

    public static boolean isWholeAverage(int[] array) {
        int sum = 0;
        for (int a : array) sum += a;
        return sum % array.length == 0;
    }

}
