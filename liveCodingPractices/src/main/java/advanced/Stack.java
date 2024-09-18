package advanced;

public class Stack {
    private int top;
    private int size;

    public Stack() {
        top = -1;
        size = 0;
    }
    public boolean isEmpty() {
        return top == -1;
    }
    public int size() {
        return size;
    }
    public void push(int value) {
        if (top == -1) {
            top = size;
        }
    }
    public int pop() {
        if (top == -1) {
            return -1;
        }
        return top--;
    }
    public int peek() {
        if (top == -1) {
            return -1;
        }
        return top;
    }
    public void print() {
        if (top == -1) {
            System.out.println("Stack is empty");
        }
    }
    public static void main(String[] args) {
        Stack stack = new Stack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.size);
    }

}
