public class stacksArray {

    static class stack {
        private static final int max=100;
        private int top=-1; //Notes the position of the topmost element
        private Object a[];

        stack(){
            a = new Object[max];
        }

        public boolean isEmpty() {
            if (top==-1) {
                return true;
            }
            else {
                return false;
            }
        }
        
        public Object stackTop(){
            return a[top];
        }

        public boolean push(int x){
            if (top>=max) {
                System.out.println("Overflow m8");
                return false;
            }
            else {
                a[++top] = x;
                System.out.println(x + " pushed into stack");
                return true;
            }
        }

        public boolean pop() {
            if (top<0) {
                System.out.println("Underflow");
                return false;
            }
            else {
                System.out.println(a[top--] + " Removed from the stack");
                return true;
            }
        }
        
        public void showStack(){
            System.out.println("The current Stack is: ");
            for(int i=0; i<=top; i++) {
                System.out.print(a[i]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        stack s = new stack();
        s.push(10);
        s.showStack();
        s.push(50);
        s.showStack();
        s.push(241);
        s.showStack();
        s.push(3);
        s.showStack();
        s.pop();
        s.showStack();
        System.out.println(s.stackTop()+ " Is on the top of the stack");
        s.pop();
        s.pop();
        if (s.isEmpty()) {
            System.out.println("The Stack is Empty");
        } else {
            System.out.println("Still have "+ (s.top+1) +" element(s) in the stack");
        }
        s.pop();
        if (s.isEmpty()) {
            System.out.println("The Stack is Empty");
        } else {
            System.out.println("Still have "+ (s.top+1) +" elements in the stack");
        }
        s.pop();

    }
}