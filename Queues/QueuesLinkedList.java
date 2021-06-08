// First --> ... --> Last

public class QueuesLinkedList {
    static class Deque {
        private class Node {
            Object data;
            Node next;
            Node previous;
        }

        Node first;
        Node last;
        Deque() {
            this.first = null;
            this.last = null;
        }

        public boolean isEmpty() {
            return first == null;
        }

        public void addFirst(Object item) {    
            Node newFirst = new Node();
            newFirst.data = item;
    
            if (first != null) {
                newFirst.next = first;
                first.previous = newFirst;
            }
            first = newFirst;
            if (last == null) {
                last = first;
            }

        }

        public void removeFirst() {
            if (isEmpty()) {
                System.out.println("The Queue is Empty");
            } else {
    
            Node oldFirst = first;
            first = first.next;
    
            if (first == null) {
                last = null;
            } else{
                first.previous = null;    
            }
            System.out.println(oldFirst.data + " Removed");
            }
        }

        public void addLast(Object item) {
    
            Node newLast = new Node();
            newLast.data = item;
    
            if (last != null) {
                newLast.previous = last;
                last.next = newLast;
            }
            last = newLast;
            if (first == null) first = last;
    
        }
    
        public void removeLast() {
            if (isEmpty()) {
                System.out.println("The Queue is Empty");
            } else {
    
            Node oldLast = last;
            last = oldLast.previous;
    
            if (last == null)
                first = null;
            else
                last.next = null;
        
                System.out.println(oldLast.data + " Removed");
            }
        }
    
    
    }
}
