import java.util.*;

public class PQ {
    private Vector<Integer> pQ;

    public PQ(){
        pQ = new Vector<Integer>();
    }

    public PQ(int initialCapacity) {
        pQ = new Vector<Integer>(initialCapacity);
    }

    public int parentIndex(int i){
        if (i==0) return i;
        else return ((i-1)/2);
    }

    public int leftChildIndex(int i){
        return (2*i)+1;
    }

    public int rightChildIndex(int i){
        return (2*i)+2;
    }

    public int size(){
        return pQ.size();
    }

    public void exchange(int i, int j){
        int temp = pQ.get(i);
        pQ.set(i, pQ.get(j));
        pQ.set(j, temp);
    }


    public void heapify(int idx){ //Top down approach O(log N)

        if (idx<size()/2){
            int lc = leftChildIndex(idx);
            int rc = rightChildIndex(idx);
            int smallest=idx;
            if ( ( lc<size() && pQ.get(lc)<pQ.get(idx) ) || ( rc<size() && pQ.get(rc)<pQ.get(idx) ) ){
                
                if (lc<size() && rc<size() && (pQ.get(lc)<pQ.get(rc))) smallest = lc;
                else if (rc<size()) smallest = rc;
            }
            
            if (smallest!=idx){
                exchange(idx, smallest);

                heapify(smallest);
            }
        }
    }

    public void heapify2(int idx){ //Bottom up approach //O(log N) but assumes rest of the heap is Heapified
        if (idx>0 && pQ.get(parentIndex(idx))>pQ.get(idx)){
            exchange(idx, parentIndex(idx));
            heapify2(parentIndex(idx));
        }
    }

    public void insert(int p){ // O(log N)
        pQ.addElement(p);
        heapify2(size()-1);
    }

    public void insert2(int p){
        pQ.addElement(p);
        int i=(size()-2)/2; //Parent of inserted element
        int pos = size()-1;
        while(pQ.get(i)>p){
            exchange(i, pos);
            pos = i;
            i=(i-1)/2;
        }
    }

    public int deleteMin(){ //O(log N)
        int t = pQ.get(0);
        pQ.removeElementAt(0);
        heapify(0);
        return t;
    }

    public int findMin(){
        return pQ.get(0);
    }

    public void buildHeap(){ //O(n)
        for (int i= (size()/2)-1; i>=0; i--){
            heapify(i);
        }
    }
    public void buildHeap2(){
        for (int i= size()-1; i>=0; i--){
            heapify2(i);
        }
    }

    public void heapSort(){ //O(n log(n))
        this.buildHeap();
        for (int i=size()-1; i>0;i--){
            System.out.print(deleteMin()+ " ");
        }
    }

    public boolean isHeap(int i){ //O(n) //Comparisons take O(1) and then recursion over every internal node
        if (i>=(size()/2)) return true;

        if (  pQ.get(i)<=pQ.get(leftChildIndex(i)) && pQ.get(i)<=pQ.get(rightChildIndex(i)) && isHeap(leftChildIndex(i)) && isHeap(rightChildIndex(i)) ) return true;
        return false;
    }

    public boolean isHeap2(){
        for (int i=size()-1; i>0;i--){
            if (pQ.get(i)<pQ.get(parentIndex(i))){
                return false;
            }
        }
        return true;
    }


    public void displayQueue(){
        int i;
        for (i=0;i<size()-1;i++){
            System.out.print(pQ.get(i)+", ");
        }
        System.out.println(pQ.get(i));
    }



    public static void main(String[] args) {
        PQ A = new PQ();
        // Insert
        A.insert(11);
        A.insert(17);
        A.insert(13);
        A.insert(18);
        A.insert(21);
        A.insert(19);
        A.insert(17);
        A.insert(43);
        A.insert(23);
        A.insert(26);
        A.insert(2);
        A.displayQueue();
        
        PQ B = new PQ();
        // Insert2
        B.insert2(3);
        B.insert2(2);
        B.insert2(15);
        B.insert2(5);
        B.insert2(4);
        B.insert2(11);
        B.insert2(1);
        B.deleteMin();
        System.out.println(B.isHeap2());
        B.displayQueue();
        PQ H = new PQ();
        // BuildHeap
        H.add(3);
        H.add(2);
        H.add(15);
        H.add(5);
        H.add(4);
        H.add(11);
        H.add(1);
        H.add(25);
        H.add(10);
        H.add(9);
        // H.heapSort();
        H.buildHeap();
        System.out.println(H.isHeap2());
        H.displayQueue();
        PQ G = new PQ();
        // BuildHeap2
        G.add(3);
        G.add(22);
        G.add(15);
        G.add(51);
        G.add(4);
        G.add(11);
        G.add(1);
        G.add(5);
        G.add(0);
        G.add(9);
        // G.heapSort();
        System.out.println(G.isHeap2());
        G.buildHeap2();
        G.displayQueue();
        System.out.println(G.isHeap2());
        // System.out.println(H.isHeap(0));
        G.displayQueue();
    }



    public void add(int p){
        pQ.addElement(p);
    }
}
