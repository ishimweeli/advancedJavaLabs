package advanced;

public class MaxmumNumberArray {

    public int maxmumNumber(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] arr = { 1, 2, 3,23, 4, 5, 6, 7, 8, 9, 10 };
        MaxmumNumberArray maxmumNumberArray = new MaxmumNumberArray();
        maxmumNumberArray.maxmumNumber(arr);
        System.out.println(maxmumNumberArray.maxmumNumber(arr));

    }
}
