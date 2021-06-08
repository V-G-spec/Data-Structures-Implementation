import java.util.*;


public class AVLTree {


    public static class Node {
        public int data, height;
        public Node left, right, parent;

        public Node(int data) {
            this.data = data;
            this.height = 1;
            left = null;
            right = null;
            parent=null;
        }
        public Node(int data, int height) {
            this.data = data;
            this.height = height;
            left = null;
            right = null;
            parent=null;
        }
    }
    public Node root;
    



    public  int getData(Node N) {
        if (N==null) {
            return 0;
        }
        return N.data;
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
            System.out.print(N.data + " ");
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


    public static void levelOrderTraversal(Node root) { //Can be used to make AVL tree from BST without rotation
		Queue<Node> queue= new LinkedList<Node>();
		queue.add(root);
		while(queue.isEmpty()==false)
		{
			Node temp =queue.poll();
			System.out.print(temp.data+" ");
			if(temp.left!=null)
				queue.add(temp.left);
			if(temp.right!=null)
				queue.add(temp.right);
		}
    }



    // Rotation, Insertion, delete 


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


    // public static int Successor(Node root) {
    //     if (root.right!=null){
    //         return minimum(root.right);
    //     }
    //     Node y = root.parent;
    //     while (y!=null && searchBST(y.right, root.data) ){
    //         root = y;
    //         y = y.parent;
    //     }
    //     return y.data;
    // }



    // public static int Predecessor(Node root) {
    //     if (root.left!=null){
    //         return maximum(root.left);
    //     }
    //     Node y = root.parent;
    //     while (y!=null && searchBST(y.left, root.data) ){
    //         root = y;
    //         y = y.parent;
    //     }
    //     return y.data;
    // }

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





    public static boolean checkHeight(Node N){ //Checks if tree (The node sent as parameter) is balanced
        int a,b;
        if (N.right==null && N.left==null) {
            a=0;
            b=0;
        }
        else if (N.right==null){
            a=N.left.height;
            b=0;
        }
        else if (N.left==null){
            a=N.right.height;
            b=0;
        }
        else {
            a=N.right.height;
            b=N.left.height;
        }

        return (Math.abs(a-b)<=1);
    }




    
    public static void assignHeight(Node N){
        int a,b;
        if (N.right==null && N.left==null){
            a=0;
            b=0;
        }
        else if (N.right==null){
            a=N.left.height;
            b=0;
        }
        else if (N.left==null){
            a=N.right.height;
            b=0;
        }
        else {
            a=N.right.height;
            b=N.left.height;
        }
        N.height = Math.max(a,b) +1;
    }




    public static int heightImbalance(Node N){ // Returns height(right) - height(left)
        if (N.right==null && N.left==null) return 0;   // Changed 1 to 0, might cause errors
        else if (N.right == null) return (0-N.left.height);
        else if (N.left == null) return (N.right.height);
        else return (N.right.height - N.left.height);
    }




    public static Node rightRotation(Node z){

        Node y = z.left;
        Node x = y.right;
        y.right = z;
        if (z!=null) z.parent = y;
        z.left = x;
        if (x!=null) x.parent =z;
        assignHeight(z);
        assignHeight(y);
        return y;

    }

    public static Node LeftRotation(Node z){

        Node y = z.right;
        Node x = y.left;
        y.left = z;
        if (z!=null) z.parent = y;
        z.right = x;
        if (x!=null) x.parent = z;
        assignHeight(z);
        assignHeight(y);
        return y;

    }




    public static Node InsertAVL(Node root, int val){

        if (root == null){
            Node nChild =  new Node(val);
            nChild.parent = root;
            return nChild;
        }
        else if (val<root.data){
            Node lchild = InsertAVL(root.left, val);
            root.left = lchild;
            lchild.parent = root;
        } 
        else {
            Node rChild = InsertAVL(root.right, val);
            root.right = rChild;
            rChild.parent = root;
        }
        
        
            
        int h = heightImbalance(root);
        if (h>1){
            if (heightImbalance(root.right)<0){
                root.right = rightRotation(root.right);
            }
            root = LeftRotation(root);
        }
        else if (h<-1){
            if (heightImbalance(root.left)>0){
                root.left = LeftRotation(root.left);
            }
            root=rightRotation(root);
        }
    

        
        assignHeight(root);
        // if (root.parent!=null){
        //     System.out.println("Tha parent of " + root.data + " is " + root.parent.data);
        // }
        return root;
    }








    public static Node deleteAVL(Node root, int val) throws IllegalArgumentException{
        
        if (root == null){
            return root; //Changed return New Node(val) to this, might produce errors
        }
        else if (root.right.data==val) root.right=null;
        else if (root.left.data==val) root.left=null;
        else if (val<root.data){
            Node lchild = deleteAVL(root.left, val);
            root.left = lchild;
        } 
        else {
            Node rChild = deleteAVL(root.right, val);
            root.right = rChild;
        }
        
        
            
        int h = heightImbalance(root);
        if (h>1){
            if (heightImbalance(root.right)<0){
                root.right = rightRotation(root.right);
            }
            root = LeftRotation(root);
        }
        else if (h<-1){
            if (heightImbalance(root.left)>0){
                root.left = LeftRotation(root.left);
            }
            root=rightRotation(root);
        }
    

        
        assignHeight(root);
        
        if (checkHeight(root)) return root;
        else throw new IllegalArgumentException("Nah Fam");
        
    }



    public static void printParents(Node root){
        if (root==null) return;
        else if (root.parent!=null) {
            System.out.println("The parent of " + root.data + " is " + root.parent.data);
            printParents(root.left);
            printParents(root.right);
        }
    }




    // @SuppressWarnings("unchecked")
    public static void inorderTraversal2vector(Node N, Vector<Integer> v) {

        if (N==null) {return;}
        else {
            inorderTraversal2vector(N.left,v);
            v.add(N.data);
            inorderTraversal2vector(N.right,v);
        }
    }
    public static Vector findMedian(Node root){
        
        Vector<Integer> v1 = new Vector<Integer>();
        inorderTraversal2vector(root, v1);
        return v1;
    }

    




    public static int countGreater(Node N, int val, int count){
        if (N==null) return count;
        else if (N.data<=val){
                N=N.right;
                count = countGreater(N, val, count);
        }
        else{
                count+=N.data;
                count = countGreater(N.right, val, count);
                count = countGreater(N.left, val, count);                
        }
        return count;
    }






    public static void main(String[] args) {
        AVLTree tree = new AVLTree();
        tree.root= InsertAVL(tree.root, 10);
        tree.root= InsertAVL(tree.root, 20);
        tree.root= InsertAVL(tree.root, 15);
        tree.root= InsertAVL(tree.root, 25);
        tree.root= InsertAVL(tree.root, 30);
        tree.root= InsertAVL(tree.root, 16);
        tree.root= InsertAVL(tree.root, 18);
        tree.root= InsertAVL(tree.root, 19);
        // tree.root= InsertAVL(tree.root, 2);
        // levelOrderTraversal(tree.root);
        
        preorderTraversal(tree.root);
        
        // Vector v2 = findMedian(tree.root);
        // int n = v2.size();
        // for(int i=0; i<n;i++){
        //     System.out.print(v2.get(i) + " ");
        // }

        // printParents(tree.root);
        // tree.root= deleteAVL(tree.root, 30);
        // preorderTraversal(tree.root);
        int count = 0;
        // count = countGreater(tree.root, 5, count);
        System.out.println();
        // System.out.println(count);

    }
}




/* 
If there are n nodes in AVL tree, minimum height of AVL tree is floor(log2n).
If there are n nodes in AVL tree, maximum height can’t exceed 1.44*log2n.
If height of AVL tree is h, maximum number of nodes can be 2^(h+1) – 1.
Minimum number of nodes in a tree with height h can be represented as:
            N(h) = N(h-1) + N(h-2) + 1 for n>2 where N(0) = 1 and N(1) = 2.
The complexity of searching, inserting and deletion in AVL tree is O(log n). 
*/





// Node minValueNode(Node node)  
//     {  
//         Node current = node;  
  
//         /* loop down to find the leftmost leaf */
//         while (current.left != null)  
//         current = current.left;  
  
//         return current;  
//     }  
  
//     Node deleteNode(Node root, int key)  
//     {  
//         // STEP 1: PERFORM STANDARD BST DELETE  
//         if (root == null)  
//             return root;  
  
//         // If the key to be deleted is smaller than  
//         // the root's key, then it lies in left subtree  
//         if (key < root.key)  
//             root.left = deleteNode(root.left, key);  
  
//         // If the key to be deleted is greater than the  
//         // root's key, then it lies in right subtree  
//         else if (key > root.key)  
//             root.right = deleteNode(root.right, key);  
  
//         // if key is same as root's key, then this is the node  
//         // to be deleted  
//         else
//         {  
  
//             // node with only one child or no child  
//             if ((root.left == null) || (root.right == null))  
//             {  
//                 Node temp = null;  
//                 if (temp == root.left)  
//                     temp = root.right;  
//                 else
//                     temp = root.left;  
  
//                 // No child case  
//                 if (temp == null)  
//                 {  
//                     temp = root;  
//                     root = null;  
//                 }  
//                 else // One child case  
//                     root = temp; // Copy the contents of  
//                                 // the non-empty child  
//             }  
//             else
//             {  
  
//                 // node with two children: Get the inorder  
//                 // successor (smallest in the right subtree)  
//                 Node temp = minValueNode(root.right);  
  
//                 // Copy the inorder successor's data to this node  
//                 root.key = temp.key;  
  
//                 // Delete the inorder successor  
//                 root.right = deleteNode(root.right, temp.key);  
//             }  
//         }  
  
//         // If the tree had only one node then return  
//         if (root == null)  
//             return root;  
  
//         // STEP 2: UPDATE HEIGHT OF THE CURRENT NODE  
//         root.height = max(height(root.left), height(root.right)) + 1;  
  
//         // STEP 3: GET THE BALANCE FACTOR OF THIS NODE (to check whether  
//         // this node became unbalanced)  
//         int balance = getBalance(root);  
  
//         // If this node becomes unbalanced, then there are 4 cases  
//         // Left Left Case  
//         if (balance > 1 && getBalance(root.left) >= 0)  
//             return rightRotate(root);  
  
//         // Left Right Case  
//         if (balance > 1 && getBalance(root.left) < 0)  
//         {  
//             root.left = leftRotate(root.left);  
//             return rightRotate(root);  
//         }  
  
//         // Right Right Case  
//         if (balance < -1 && getBalance(root.right) <= 0)  
//             return leftRotate(root);  
  
//         // Right Left Case  
//         if (balance < -1 && getBalance(root.right) > 0)  
//         {  
//             root.right = rightRotate(root.right);  
//             return leftRotate(root);  
//         }  
  
//         return root;  
//     }  
