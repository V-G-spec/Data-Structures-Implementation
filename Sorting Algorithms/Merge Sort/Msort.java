import java.util.*;

// import sun.security.util.Length;

public class Msort {
    
    public static void sort(int[] A, int left, int right){
        if (left<right){
            int mid = (left+right)/2;
            sort(A, left, mid);
            sort(A, mid+1, right);
            merge(A, left, right);
        } else return;
    }

    public static void merge(int[] A, int left, int right) {
        int j = (left+right)/2 + 1;
        int i = left;
        if (A[(left+right)/2]<=A[(left+right)/2 + 1]) {
            return;
        }
        
        while (i< j && j<=right){    
            if(A[i]<=A[j]) i+=1;
            else {
                int val = A[j];
                int pos = j;

                while(pos!=i){
                    A[pos] = A[pos-1];
                    pos-=1;
                }
                A[i] = val;
                i++;
                j++;
            } 
        }
    }






    public static void main(String[] args) {
        Random rd = new Random(); 
        int[] A = new int[20];
        for (int i = 0; i < A.length; i++) {
             A[i] = Math.abs(rd.nextInt(101)); //Create array with values between 0 and 100, inclusive
        }
        sort(A, 0, A.length-1);
        for (int i =0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }
}
