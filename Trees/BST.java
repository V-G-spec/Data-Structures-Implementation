import java.util.*;


public class BST {

    
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
    public Node root;



    public Node findRoot(Node N) {
        while (N.parent!=null) {
            return findRoot(N.parent);
        }
        return N;
    }

    public  int getData(Node N) {
        if (N==null) {
            return 0;
        }
        return N.data;
    }

    public  Node getParent(Node N){

            return N.parent;
    }



    public  boolean isRoot(Node N) {
        if (N.parent==null) {
            return true;
        }
        return false;
    }

    public  boolean isInternal(Node N) {
        if (isLeaf(N) == false) {
            return true;
        }
        return false;
    }

    public  boolean isLeaf(Node N) {
        if (N.left==null && N.right==null) {
            return true;
        }
        return false;
    }    

    public static void inorderTraversal(Node N) {

        if (N==null) {
            // System.out.println("Oh fuck")
            ;
        } else {
            inorderTraversal(N.left);
            System.out.println(N.data);
            // if (N.parent == null){
            //     System.out.println("Parent : NULL");  
            // } else{
            //     System.out.println("Parent : " + N.parent.data);
            // }
            inorderTraversal(N.right);
        }
    }

    public static void preorderTraversal(Node N) {

        if (N==null) {
            return;
        } else {
            System.out.println(N.data);
            preorderTraversal(N.left);
            preorderTraversal(N.right);
        }
    }

    public static void postorderTraversal(Node N) {

        if (N==null) {
            return;
        } else {
            postorderTraversal(N.left);
            postorderTraversal(N.right);
            System.out.println(N.data);
        }
    }

    // public double arithmetic(Node N) {
    //     while (N.left!=null || N.right!=null) {
    //         if (N.type = 'oper') {
    //             switch (N.operation) {
    //                 case '+':
    //                     return N.left+N.right;
    //                 case '-':
    //                     return N.left-N.right;
    //                 case '*':
    //                     return N.left*N.right;
    //                 case '/':
    //                     return N.left/N.right;
    //                 default:
    //                     throw IllegalArgumentException
    //             }
    //         } else {
    //             return N.number;
    //         }
    //     }
    // }


    // public int[] makePost(int[] in, int[] post, int n) {

    // }





    // // Done: Insertion, delete, search, 
    // // Done: Successor and predecessor 
    // // Done: Minimum and maximum
    // // Done: Arithmetic Expression: inorder traversal + paranthesis and no paranthesis with number
    // Tree from pre and in order 
    // Find total number of trees given a specified pre order traversal

    public static int minimum(Node root){
        if (root.left==null){
            return root.data;
        } else{
            return minimum(root.left);
        }
    }
    
    public static int maximum(Node root){
        if (root.right==null){
            return root.data;
        } else{
            return maximum(root.right);
        }
    }


    public static int Successor(Node root) {
        if (root.right!=null){
            return minimum(root.right);
        }
        Node y = root.parent;
        while (y!=null && searchBST(y.right, root.data) ){
            root = y;
            y = y.parent;
        }
        return y.data;
    }



    public static int Predecessor(Node root) {
        if (root.left!=null){
            return maximum(root.left);
        }
        Node y = root.parent;
        while (y!=null && searchBST(y.left, root.data) ){
            root = y;
            y = y.parent;
        }
        return y.data;
    }




    public static int inOrderSuccessor(Node root) { //Just for Deletion
        int successor = root.data;
        while(root.left!=null) {
            successor = root.left.data;
            root = root.left;
        }
        return successor;
    }
    
    public static boolean searchBST(Node root, int val) {
        if (root == null){
            return false;
        } 
        if (root.data == val){
            return true;
        } 
        if (root.data> val) {
            return searchBST(root.left, val);
        } 
        else if (root.data < val) {
            return searchBST(root.right, val);
        }
        return false;
    }

    public static Node insertBST(Node root, int val) {
        if (root == null){
            return new Node(val);
        }
        if (val<root.data){
            Node lchild = insertBST(root.left, val);
            root.left = lchild;
            lchild.parent = root;
        } else if (val>root.data){
            Node rchild = insertBST(root.right, val);
            root.right = rchild;
            rchild.parent=root;
        }
        
        // if (root.parent!=null){
        //     System.out.println("Tha parent of " + root.data + " is " + root.parent.data);
        // }
        return root;

    }

    public static Node deleteVal(Node root, int val) {
        if (root==null) {
            return root;
        }
        if (root.data> val) {
            root.left = deleteVal(root.left, val);
        }
        else if (root.data < val) {
            root.right = deleteVal(root.right, val);
        }

        else {
            if (root.left==null && root.right == null) return new Node(null);
            else if (root.left==null) {
                return root.right;
            }
            else if (root.right == null) {
                return root.left;
            }

            root.data = inOrderSuccessor(root.right);
            root.right = deleteVal(root.right, root.data); 
        
        }

        return root;
    }


    public static void countTrees(int[] pre){

    }


    
    public static int  findIndex (int[] my_array, int t) {
        if (my_array == null) return -1;
        int len = my_array.length;
        int i = 0;
        while (i < len) {
            if (my_array[i] == t) return i;
            else i=i+1;
        }
        return -1;
    }

    
    
    
    
    
    public static Node preInToTree(Node root, int[] in, int[] pre){

        int n = in.length;
       if (n==1){
            return new Node(in[0]);
        }

        else {
        int top = pre[0];
        // root.data =top;
        root = new Node(top);

        int topIdx = findIndex(in, top);
            
            if (topIdx > 0){
            
                int[] sliceInLeft = Arrays.copyOfRange(in, 0, topIdx);
                
                int nLeft = sliceInLeft.length;
                int[] subArrayPreLeft = new int[nLeft];
                int j=0;
                for (int y=0; y<n; y++){
                    
                    if (findIndex(sliceInLeft, pre[y])!=-1){
                        subArrayPreLeft[j] = pre[y];
                        j++;
                    }
                }
                root.left = preInToTree(root.left, sliceInLeft, subArrayPreLeft);
            }
        
        
            if (topIdx < n-1){
                int[] sliceInRight = Arrays.copyOfRange(in, topIdx+1, n);
                int nRight = sliceInRight.length;
                int[] subArrayPreRight = new int[nRight];
                int j=0;
                for (int y=0; y<n; y++){
                    
                    if (findIndex(sliceInRight, pre[y])!=-1){
                        subArrayPreRight[j] = pre[y];
                        j++;
                    }
                }
                root.right = preInToTree(root.right, sliceInRight, subArrayPreRight);

        }

        }
        
        return root;
    }





    public static void main(String[] args) {

        BST tree = new BST();
        tree.root = new Node(10);
        tree.root.left = new Node(5);
        tree.root.right = new Node(20);
        tree.root.left.left = new Node(4);
        tree.root.left.right = new Node(8);
        tree.root.right.left = new Node(15);
        tree.root.right.right = new Node(25);
        tree.root = insertBST(tree.root, 22);
        tree.root = insertBST(tree.root, 71);
        tree.root = insertBST(tree.root, 1);
        tree.root = insertBST(tree.root, 6);
        tree.root = insertBST(tree.root, 43);
        tree.root = insertBST(tree.root, 19);
        System.out.println("Search Value 2 is in tree? " + searchBST(tree.root, 2));
        System.out.println("Search Value 10 in tree? " + searchBST(tree.root, 10));
        inorderTraversal(tree.root);   
        // System.out.println();
        // preorderTraversal(tree.root); 
        // System.out.println();  
        // postorderTraversal(tree.root);
        System.out.println();
        System.out.println(maximum(tree.root));
        System.out.println(minimum(tree.root));   
        System.out.println();
        tree.root = deleteVal(tree.root, 71);
        tree.root = deleteVal(tree.root, 25);
        inorderTraversal(tree.root);   
        System.out.println();
        System.out.println(Successor(tree.root.left.right.left));
        System.out.println(Predecessor(tree.root.left.right));



        BST tree2 = new BST();
        int[] preorder = {1,2,3,4,6,7,5};
        int[] inorder = {3, 2, 6, 4, 7, 1, 5};
        tree2.root = new Node(0);
        tree2.root= preInToTree(tree2.root, inorder, preorder);
        System.out.println();
        System.out.println();
        inorderTraversal(tree2.root);   
        System.out.println();
        System.out.println();
        preorderTraversal(tree2.root);   
        System.out.println();
        System.out.println();
        postorderTraversal(tree2.root);   






    }








}


