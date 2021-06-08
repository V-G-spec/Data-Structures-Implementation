/* 
enQueue(value) This function is used to insert an element into the circular queue. In a circular queue, the new element is always inserted at Rear position. 
Steps:
Check whether queue is Full – Check ((rear == SIZE-1 && front == 0) || (rear == front-1)).
If it is full then display Queue is full. If queue is not full then, check if (rear == SIZE – 1 && front != 0) if it is true then set rear=0 and insert element.

deQueue() This function is used to delete an element from the circular queue. In a circular queue, the element is always deleted from front position. 
Steps:
Check whether queue is Empty means check (front==-1).
If it is empty then display Queue is empty. If queue is not empty then step 3
Check if (front==rear) if it is true then set front=rear= -1 else check if (front==size-1), if it is true then set front=0 and return the element. */


public class QueuesArrays { //Circular Array


    static class queue {
        private int size=100;
        private int rear;
        private int front;
        private int currentSize;
        private Object a[];

        queue(int sized){
            this.size = sized;
            a = new Object[size];
            rear=front=-1;
            currentSize = 0;
        }

        queue(){
            a = new Object[size];
            rear=front=-1;
            currentSize = 0;
        }

        public boolean isFull() {
            return (currentSize == a.length);
        }

        public boolean isEmpty() {
            return (currentSize == 0);
        }


        public void enQueue(Object x) {
            if (isFull()) {
                System.out.println("Circular Queue is full. Element cannot be added");
            } else {
                rear = (rear+1)%a.length;
                a[rear] = x;
                currentSize+=1;
                System.out.println(x+ " added to the Queue");

                if (front == -1) {
                    front = rear;
                }
            }
        }

        public void deQueue() {
            if (isEmpty()) {
                System.out.println("Circular Queue is Empty. Element cannot be removed");
            } else {
                Object x = a[front];
                a[front] = null;
                front = (front + 1) % a.length;
                currentSize--;
                System.out.println("Element " + x + " removed");
            }
        }

        public void displayQueue() {
            if(isEmpty()) { 
            System.out.println("Queue is Empty"); 
            } 
            else if (rear>front) {
                for(int i = front; i <= rear; i++) { 
                    System.out.print(a[i]); 
                    System.out.print(" "); 
                } 
                System.out.println();
            } else {
                for(int i = front; i < size; i++) { 
                    System.out.print(a[i]); 
                    System.out.print(" "); 
                } 

                for(int i = 0; i <= rear; i++) { 
                    System.out.print(a[i]); 
                    System.out.print(" "); 
                } 
            System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        queue q = new queue();
        q.enQueue(10);
        q.enQueue(50);
        q.enQueue(241);
        q.enQueue(3);
        q.displayQueue();
        q.deQueue();
        q.displayQueue();
        q.deQueue();
        q.deQueue();
        if (q.isEmpty()) {
            System.out.println("The Queue is Empty");
        } else {
            System.out.println("Still have element(s) in the Queue");
        }
        q.deQueue();
        if (q.isEmpty()) {
            System.out.println("The Queue is Empty");
        } else {
            System.out.println("Still have element(s) in the Queue");
        }
        q.deQueue();

    }
}