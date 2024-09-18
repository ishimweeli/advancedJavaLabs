package advanced;

public class StringReverse1 {

    public class ReverseString {
//        public String reverse(String str) {
//            return new StringBuilder(str).reverse().toString();
//        }
        public String reverse(String str) {
            String reversed = "";
            for (int i = str.length() - 1; i >= 0; i--) {
                reversed += str.charAt(i);
            }
            return reversed;
        }
    }



    public static void main(String[] args) {

//StringReverse1 stringReverse1=new StringReverse1();
//ReverseString reverseString=stringReverse1.new ReverseString();
//System.out.println(reverseString.reverse("Hello World"));
        StringReverse1 str = new StringReverse1();
       ReverseString reverseString= str.new ReverseString();
       System.out.println(reverseString.reverse("Hello World"));
    }


}
