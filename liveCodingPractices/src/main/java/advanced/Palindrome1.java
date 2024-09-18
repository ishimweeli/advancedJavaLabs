package advanced;

public class Palindrome1 {

    public boolean isPalindrome(String x) {
        String reversed = new StringBuilder(x).reverse().toString();
        return x.equals(reversed);
    }

    public static void main(String[] args) {
        Palindrome1 p = new Palindrome1();
        System.out.println(p.isPalindrome("madam"));

    }

}