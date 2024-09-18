package advanced;

public class MaxmumNumberArray1 {

    public int maximumNumber(int[] array) {
        int max = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > max) {
                max = array[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] array = { 1, 2, 3, 4, 5, 6,22, 7, 8, 9, 10 };
        MaxmumNumberArray1 maxmumNumberArray1 = new MaxmumNumberArray1();
        System.out.println(maxmumNumberArray1.maximumNumber(array));

    }
}
