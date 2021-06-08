import java.util.*;


// Random rand = new Random();
// nextInt as provided by Random is exclusive of the top value so you need to add 1 
// int randomNum = rand.nextInt((max - min) + 1) + min;

public class exchange {
    
    public static void exchangeRadix(int[] A, int left, int right, int numDig){
        //  For binary, simply just take a vector and 0 vaale starting mein, 1 vaale end mein
        int[][] bucket = new int[10][right-left+1];
        int[] count = new int[10];
        int divisor = (int) Math.pow(10, numDig-1);
        if (divisor>0 && left<right){
            
            for (int i=left; i<=right; i++){
                bucket[(A[i]/divisor)%10][count[(A[i]/divisor)%10]] = A[i];
                count[(A[i]/divisor)%10]++;        
            }

            
            int i=left;
            int ct1 = 0;
            int ct2 = 0;
            while(i<=right){
                ct2=0;
                while( ct1<10 && ct2 < right-left+1 &&  bucket[ct1][ct2]!=0){
                    A[i] = bucket[ct1][ct2];
                    i++;
                    ct2++;
                }
                ct1++;
            }


            
            ///// For checking ////
            // System.out.println(numDig);
            // for (int u =0; u<bucket.length; u++){
            //     for (int o =0; o<bucket[0].length; o++)
            //     {
            //         System.out.print(bucket[u][o] + " ");
            //     }
            //     System.out.println();
            // }
            // System.out.println();
            // System.out.println();
            // for (int u =0; u<A.length; u++){
            //     System.out.print(A[u] + " ");
            // }
            // System.out.println();
            // System.out.println();

            // System.out.println();
            // for (int u =0; u<count.length; u++){
            //     System.out.print(count[u] + " ");
            // }
            // System.out.println();
            // System.out.println();


            if (count[0]>1) exchangeRadix(A, left, left+count[0]-1, numDig-1);

            for (int j=1; j<10; j++){
                int p = count[j];
                count[j] = count[j-1]+count[j];
                if (p>1){
                    exchangeRadix(A, left+count[j-1], left+count[j]-1 , numDig-1);
                }
            }

            // Now sort for other 10, too much effort
        } return ;
    }
    
    public static void straightRadix(int[] A, int left, int right, int numDig) {
        // int length = String.valueOf(A[left]).length();
        
        for(int i=0; i<numDig; i++){
            int dividend = 10^(i+1);
            for (int j=left; j<=right; j++){

            }
        }
    }




    public static void main(String[] args) {
        Random rd = new Random(); 
        int[] A = new int[64];
        int Ndig = 4;
        for (int i = 0; i < A.length; i++) {
             A[i] = Math.abs(rd.nextInt((int) (9 * Math.pow(10, Ndig - 1)))) + 10 ^ (Ndig - 1);
        }
        // int[] A = {1032, 1004, 7068, 2047};
        for (int i =0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
        System.out.println();
        exchangeRadix(A, 0, A.length-1, Ndig);
        for (int i =0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
    }
}
