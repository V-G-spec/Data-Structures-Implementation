public class stacksLinkedList {

    static class StackByLinkedList {

        private class Node {
            Object data;
            Node link;
        }

        Node top;
        StackByLinkedList() {
            top = null; //Was this.top earlier, might give an error
        }

        public boolean isEmpty() {
            if (top==null) {
                return true;
            }
            else {
                return false;
            }
        }

        public Object stackTop(){
            if (!isEmpty()) { 
                return top.data; 
            } 
            else { 
                System.out.println("Stack is empty"); 
                return -1; 
            } 
        }

        public void push(Object x){

            Node temp = new Node();
            temp.link = top;
            temp.data = x;
            System.out.println(x + " pushed into stack");
            top = temp;
            return;

        }

        public void pop() {
            if (top==null) {
                System.out.println("Underflow");
                return;
            }
            else {
                System.out.println(top.data + " Removed from the stack");
                top = top.link;
                return;
            }
        }

        public void showStack() {
            Node temp = top;
            while (temp!=null) {
                System.out.print(temp.data+ " --> ");
                temp = temp.link;
            }
        }
    }

    public static void main(String[] args) {
        StackByLinkedList s = new StackByLinkedList();
        s.push(10);
        s.push(50);
        s.push(241);
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
            System.out.println("The Stack is not empty");
        }
        s.pop();
        if (s.isEmpty()) {
            System.out.println("The Stack is Empty");
        } else {
            System.out.println("The Stack is not empty");
        }
        s.pop();

    }
}