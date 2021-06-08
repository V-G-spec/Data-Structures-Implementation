import java.util.*;


public class LLRBT{


    public static class LLRBTNode{
        public LLRBTNode left, right;
        public int data;
        public boolean red;
    

        public LLRBTNode(int data){
            this.data=data;
            left=right=null;
            red=true;
        }
    }

    public LLRBTNode root=null;

    public static LLRBTNode rightRotate(LLRBTNode b) {

        LLRBTNode a ;
        LLRBTNode c;
        a= b.left;
        c = a.right;
        b.left = c;
        a.right = b;
        return a;
    }

    public static LLRBTNode leftRotate(LLRBTNode b) {
        LLRBTNode a;
        LLRBTNode c;
        a = b.right;
        c = a.left;
        a.left = b;
        b.right = c;
        return a;
    }


    

    public static void printTree(LLRBTNode root) {
        printHelper(root, "", true);
    }

    public static void printHelper(LLRBTNode root, String indent, boolean last) {
        if (root != null) {
          System.out.print(indent);
          if (last) {
            System.out.print("R----");
            indent += "   ";
          } else {
            System.out.print("L----");
            indent += "|  ";
          }
    
          String sColor = root.red ? "RED" : "BLACK";
          System.out.println(root.data + "(" + sColor + ")");
          printHelper(root.left, indent, false);
          printHelper(root.right, indent, true);
        }
      }





/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                          INSERT STARTS HERE                                  //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static LLRBTNode insertCall(LLRBTNode a, int data){
        a= insert(a, data);   
        a.red=false;
        return a;
    }

    public static LLRBTNode insert(LLRBTNode a, int data) {
            
        if (a == null)
            return new LLRBTNode(data);
        else if (a.data < data)
            a.right = insert(a.right, data);
        else if (a.data > data)
            a.left = insert(a.left, data);
        else
            return a;

        if ((a.left!=null && a.left.red) && (a.right!=null && a.right.red)) {
            if (a.red)
                a.red = false;
            else
                a.red = true;
            a.right.red = false;
            a.left.red = false;
        }
        if ((a.left!=null && a.left.red) && (a.left.left!=null && a.left.left.red)) {
            a = rightRotate(a);
            boolean temp;
            temp = a.red;
            a.red = a.right.red;
            a.right.red = temp;
        }
        if ((a.right!=null && a.right.red) && (a.left == null || a.left.red == false)) {
            a = leftRotate(a);
            boolean temp;
            temp = a.red;
            a.red = a.left.red;
            a.left.red = temp;
        }
        
        
        return a;
    }

    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                          INSERT ENDS HERE                                  //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


    public static void inorderTraversal(LLRBTNode N) {

        if (N == null) {
            // System.out.println("Oh fuck")
            ;
        } else {
            inorderTraversal(N.left);
            System.out.print(N.data);

            if (!N.red)
                System.out.println(" :: Color : Black");
            if (N.red)
                System.out.println(" :: Color : Red");
            inorderTraversal(N.right);
        }
    }

    public static void preorderTraversal(LLRBTNode N) {

        if (N == null) {
            return;
        } else {
            System.out.print(N.data + " ");

            if (!N.red)
                System.out.println(" :: Color : Black");
            if (N.red)
                System.out.println(" :: Color : Red");
            preorderTraversal(N.left);
            preorderTraversal(N.right);
        }
    }

    public static void postorderTraversal(LLRBTNode N) {

        if (N == null) {
            return;
        } else {
            postorderTraversal(N.left);
            postorderTraversal(N.right);
            System.out.println(N.data);

            if (!N.red)
                System.out.println(" :: Color : Black");
            if (N.red)
                System.out.println(" :: Color : Red");
        }
    }

    public static void levelOrderTraversal(LLRBTNode root) { // Can be used to make AVL tree from BST without rotation
        Queue<LLRBTNode> queue = new LinkedList<LLRBTNode>();
        queue.add(root);
        while (queue.isEmpty() == false) {
            LLRBTNode temp = queue.poll();
            System.out.print(temp.data + " ");
            if (temp.left != null)
                queue.add(temp.left);
            if (temp.right != null)
                queue.add(temp.right);
        }
    }

    public static void main(String[] args) {
        LLRBT tree = new LLRBT();

        tree.root = insertCall(tree.root, 10);
        // root.red=false;
        tree.root = insertCall(tree.root, 20);
        // root.red=false;
        // inorderTraversal(root);
        tree.root = insertCall(tree.root, 30);
        // root.red=false;
        // inorderTraversal(root);
        tree.root = insertCall(tree.root, 40);
        // root.red=false;
        // inorderTraversal(root);
        tree.root = insertCall(tree.root, 50);
        // root.red=false;
        // inorderTraversal(root);
        tree.root = insertCall(tree.root, 25);
        // root.red=false;
        // inorderTraversal(tree.root);
        printTree(tree.root);
    }
}