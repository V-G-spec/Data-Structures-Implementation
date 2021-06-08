import java.util.*;

/**
 * Qsort
 */
public class Qsort {

    public static void sort(int[] a, int left, int right){
        int x;
        int p;
        if (left<right){
            x=a[left];
            p = partition(a, x, left+1, right);    
            int temp = a[left];
            a[left] = a[p];
            a[p] = temp;
            sort(a, left, p-1);
            sort(a, p+1, right);
        }
        else return;
    }

    public static int partition(int[] a, int x, int l, int r){
        int i =l;
        int j = r;
        while (i<=j){
            if (a[i]<=x) {
                i+=1;
            } else {
                int temp = a[i];
                a[i] = a[j];
                a[j] = temp;
                j-=1;
            }
        }
        
        
        return j;
    }

    public static void Randomsort(int[] a, int left, int right){
        int x;
        int p;
        Random rand = new Random();
        if (left<right){
            int pos = rand.nextInt(right - left + 1) + left;
            x=a[pos];
            p = partition(a, x, left+1, right);    
            int temp = a[pos];
            a[pos] = a[p];
            a[p] = temp;
            sort(a, left, p-1);
            sort(a, p+1, right);
        }
        else return;
    }



    public static void main(String[] args) {
        Random rd = new Random(); 
        int[] A = {21, 11, -46, 48, 36, 11, 40 ,-25 ,-24, 30, 21, -36, -5 ,-23 ,37,-49, -9, 44, -16, -45};
        // for (int i = 0; i < A.length; i++) {
        //      A[i] = Math.abs(rd.nextInt(101)); //Create array with values between 0 and 100, inclusive
        // }
        
        
        
        // int[] A = {17, 12, 6, 19, 23, 8, 5, 10};
        sort(A, 0, A.length-1);
        for (int i =0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
        
        
        // int[] B = {17, 12, 6, 19, 23, 8, 5, 10};
        Randomsort(A, 0, A.length-1);
        for (int i =0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
    }
}