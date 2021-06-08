// import java.util.*;

// public class Submit {
// 	public static void sort(int[] arr, int n){
// 		for(int i = n-2; i >= 0; i--){
// 			int j = i;
// 			int key = arr[j];
// 			while(j <= n-2 && arr[j+1] < key){
// 				arr[j] = arr[j+1];
// 				j++;
// 			}
// 			arr[j] = key;
// 			for(int k = 0; k <= n-1; k++){
// 				System.out.print(arr[k] + " ");
// 			}
// 			System.out.println();
// 		}
// 	}
// 	public static void main(String[] args){
// 		sort(a, n);
// 	}
// }


import java.util.*;

public class Submit {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int [] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = scanner.nextInt();
		}
		

		for (int j=n-2; j>=0; j--){
			int i=j;
			int key = a[j];
			while (i<=n-2 && a[i+1]<key){
				a[i] = a[i+1];
				i++;
			}
			a[i]=key;

			for(int k = 0; k <= n-1; k++){
				System.out.print(a[k] + " ");
			}
			System.out.println();
		
		scanner.close();

		}

	}
}