import java.util.*;

public class CyclePeriod {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		int [] a = new int[n];
		for (int i=0; i<n; i++) {
			a[i] = scanner.nextInt();
		}
		scanner.close();
		
		// Eg. Input = 10 0 1 2 3 4 5 2 3 4 5
		// then 4 will be the cycle period.
		// therefore output = 4 (you just have to print 4)
		// IF there is No Cycle found output will be 0.

		int flag =-1 ;
		int idx =0;
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>(); // Key is array element and value is index 
		for (; idx<n; idx++) {
			if (hm.get(a[idx])!=null) {
				flag = hm.get(a[idx]);
				break;
			} else {
				hm.put(a[idx], idx);
			}
		}
		if (flag!=-1){
			System.out.println(idx-flag);
		} else {
			System.out.println(0);
		}

	}
}

// Errors:
//  7 -1 -1 -1 -1 -1 -1 -1
// Expected: 1, My: 0
//  21 0 1 2 8 4 3 0 1 2 8 4 3 0 1 2 8 4 3 0 1 2
// Expected: 6, My: 0