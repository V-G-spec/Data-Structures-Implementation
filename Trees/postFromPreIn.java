import java.util.*;

public class postFromPreIn {

    public static class Node {
        public int data;
        public Node left, right, parent;

        public Node(int data) {
            this.data = data;
            left = null;
            right = null;
            parent = null;
        }
    }

    public static int search(int[] arr, int key){
        for(int i = 0; i < arr.length; i++){
            if(arr[i] == key) return i;
        }
        return -1;
    }


    public static void preInToPost(int[] pre, int[] in, int n, int startIn, int endIn, int startPre, int endPre){

        if(startPre > endPre || startIn > endIn) return;
        
        int rootIdx = -1;
        for(int i = 0; i < in.length; i++){
            if(arr[i] == pre[startPre]) {
                rootIdx= i;
            }
        }

        if(rootIdx != 0) preInToPost(preorder, inorder, n, startIn, rootIdx - 1, startPre + 1, startPre + rootIdx);
        if(rootIdx != n-1) preInToPost(preorder, inorder, n, rootIdx + 1, endIn, startPre + 1 + rootIdx, endPre);
        
        System.out.println(pre[startPre] + " ");
    }



    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] preorder = new int[n];
        int[] inorder = new int[n];
        for(int i = 0; i < n; i++){
            preorder[i] = input.nextInt();
        }
        for(int i = 0; i < n; i++){
            inorder[i] = input.nextInt();
        }
        preInToPost(preorder, inorder, n, 0, n-1, 0, n-1);
        input.close();

    }
}