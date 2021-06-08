public class Stack {
    static class StackByLinkedList {

        private class Node {
            char data;
            Node link;
        }

        Node top;
        StackByLinkedList() {
            this.top = null;
        }

        public boolean isEmpty() {
            if (top==null) {
                return true;
            }
            else {
                return false;
            }
        }

        public char stackTop(){
            if (!isEmpty()) { 
                return top.data; 
            } 
            else { 
                // System.out.println("Stack is empty"); 
                return 'n'; 
            } 
        }

        public void push(char x){

            Node temp = new Node();
            temp.link = top;
            temp.data = x;
            // System.out.println(x + " pushed into stack");
            top = temp;
            return;

        }

        public void pop() {
            if (top==null) {
                // System.out.println("Underflow");
                return;
            }
            else {
                // System.out.println(top.data + " Removed from the stack");
                top = top.link;
                return;
            }
        }

        public void showStack() {
            Node temp = top;
            while (temp!=null) {
                System.out.println(temp.data+ " --> ");
                temp = temp.link;
            }
        }
    }
}
