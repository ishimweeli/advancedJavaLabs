package advanced;

public class StringReverse {
    public String reverse(String input) {
        String reversed = "";
        for(int i=input.length()-1;i>=0;i--){
            reversed +=input.charAt(i);
        }
        return reversed;
    }
    public static void main(String[] args) {
        StringReverse sr = new StringReverse();
        System.out.println(sr.reverse("Hello World"));
    }
}