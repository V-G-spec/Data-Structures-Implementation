import java.util.*;

import org.w3c.dom.Node;

public class RedBlack {
    

    public static class RedBlackNode{
        public RedBlackNode parent, left, right;
        public boolean black;
        public int height, data;
        
        public RedBlackNode(int data){
            this.data = data;
            black = false;
            parent=null;
            left=null;
            right=null;
            this.height=1;
        }
        public RedBlackNode(){
            black = true;
            parent=null;
            left=null;
            right=null;
        }
    }

    public RedBlackNode root;


    public static void inorderTraversal(RedBlackNode N) {

        if (N==null) {
            // System.out.println("Oh fuck")
            ;
        } else {
            inorderTraversal(N.left);
            System.out.print(N.data);
            if (N.parent == null){
                System.out.print(":: Parent : NULL");  
            } else{
                System.out.print(":: Parent : " + N.parent.data);
            }
            if (N.black) System.out.println(" :: Color : Black");
            if (!N.black) System.out.println(" :: Color : Red");
            inorderTraversal(N.right);
        }
    }

    public static void preorderTraversal(RedBlackNode N) {

        if (N==null) {
            return;
        } else {
            System.out.print(N.data + " ");
            if (N.parent == null){
                System.out.print(":: Parent : NULL");  
            } else{
                System.out.print(":: Parent : " + N.parent.data);
            }
            if (N.black) System.out.println(" :: Color : Black");
            if (!N.black) System.out.println(" :: Color : Red");
            preorderTraversal(N.left);
            preorderTraversal(N.right);
        }
    }

    public static void postorderTraversal(RedBlackNode N) {

        if (N==null) {
            return;
        } else {
            postorderTraversal(N.left);
            postorderTraversal(N.right);
            System.out.println(N.data);
            if (N.parent == null){
                System.out.print(":: Parent : NULL");  
            } else{
                System.out.print(":: Parent : " + N.parent.data);
            }
            if (N.black) System.out.println(" :: Color : Black");
            if (!N.black) System.out.println(" :: Color : Red");
        }
    }


    public static void levelOrderTraversal(RedBlackNode root) { //Can be used to make AVL tree from BST without rotation
		Queue<RedBlackNode> queue= new LinkedList<RedBlackNode>();
		queue.add(root);
		while(queue.isEmpty()==false)
		{
			RedBlackNode temp =queue.poll();
			System.out.print(temp.data+" ");
			if(temp.left!=null)
				queue.add(temp.left);
			if(temp.right!=null)
				queue.add(temp.right);
		}
    }







/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                          INSERT STARTS HERE                                  //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public static RedBlackNode brotherSister(RedBlackNode N) throws IllegalArgumentException{
        if (N.parent==null) throw new IllegalArgumentException(" ");
        else {
            if (N.parent.left == N) return N.parent.right;
            else return N.parent.left;
        }
    }


    public static void assignHeight(RedBlackNode N){
        if (N.black){
            if(N.parent==null){
                N.height = 1;
            }
            else N.height=N.parent.height+1;
        } else{
            N.height = N.parent.height;
        }
    }

///////////////////////////////////////////////////////////////////////////
public static void updateEveryHeight(RedBlackNode root){
    // Increase every height by 1
    if(root==null) ;
    else {
        assignHeight(root);
        assignHeight(root.left);
        assignHeight(root.right);
    }
}
///////////////////////////////////////////////////////////////////////////

    public static void handleCases(RedBlackNode prev, RedBlackNode curr){
        
        
        if (prev.parent == null && prev.black==false) {
            prev.black=true;
            updateEveryHeight(prev);
        } else if ((brotherSister(prev)==null || brotherSister(prev).black) && prev.parent.data<prev.data){ // Case 1
            if (prev.left==curr) rightRotate(prev);
            prev = leftRotate(prev.parent);
        } else if ((brotherSister(prev)==null || brotherSister(prev).black)){ // Case 1
            if (prev.right==curr) leftRotate(prev);
            prev = rightRotate(prev.parent);
        } else { // Case 2

            prev.black = true;
            brotherSister(prev).black = true;
            prev.parent.black=false;
            if(prev.parent.parent!=null){
                if (prev.parent.parent.black==false) handleCases(prev.parent.parent, prev.parent);
            } else {
                prev.parent.black = true;
                updateEveryHeight(prev.parent);
            }
        }
    }




    public static RedBlackNode rightRotate(RedBlackNode b){

        RedBlackNode a = b.left;
        b.left = a.right;
        if (a.right!=null){
            a.right.parent = b;
        }
        if (b.parent==null) a.parent=null;
        else if (b == b.parent.right) {
            a.parent = b.parent;
            b.parent.right = a;
        } else {
            a.parent = b.parent;
            b.parent.left = a;
        }
        a.right = b;
        b.parent = a;
        a.black = true;
        b.black = false;
        return a;
    }


    public static RedBlackNode leftRotate(RedBlackNode b){
        RedBlackNode a = b.right;
        b.right = a.left;
        if(a.left!=null){
            a.left.parent = b;
        }
        if (b.parent==null) a.parent=null;
        else if (b == b.parent.right) {
            a.parent = b.parent;
            b.parent.right = a;
        } else {
            a.parent = b.parent;
            b.parent.left = a;
        }
        a.left = b;
        b.parent = a;
        a.black = true;
        b.black = false;
        return a;
        }



    public static RedBlackNode insertRBTree(RedBlackNode root, int val){
        
        RedBlackNode curr = root;
        RedBlackNode prev;
        prev = null;
        while(curr!=null){
            if (val>curr.data) {
                prev = curr;
                curr=curr.right;

            } else {
                prev = curr;
                curr = curr.left;
            }
        }


        if (prev == null) {
            root = new RedBlackNode(val);
            root.black = true;
            
        } else{
            curr = new RedBlackNode(val);
            curr.parent = prev;
            if (curr.data<prev.data) prev.left = curr;
            else prev.right = curr;
            if (prev.black==false){
                handleCases(prev, curr);
            }
        }
        if (root.parent==null) return root;
        else return findRoot(root);
    } 

    public static RedBlackNode findRoot(RedBlackNode N){
        while (N.parent!=null){
            N=N.parent;
        }
        return N;
    }
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                          INSERT ENDS HERE                                  //
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                          DELETE STARTS HERE                                  //
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

 
 public static RedBlackNode searchVal(RedBlackNode root, int val){
    while(root.data!=val){
        if (root.data>val) return searchVal(root.left, val);
        else return searchVal(root.right, val);
    }
    return root;
 }

 public static boolean isLeaf(RedBlackNode N){
    if (N.left==null && N.right==null) return true;
    else return false;
 }

 public static RedBlackNode inOrderSuccessorNode(RedBlackNode root) { //Just for Deletion
    while(root.left!=null) {
        root = root.left;
    }
    return root;
 }


 public static RedBlackNode swapToLeaf(RedBlackNode pos, int val){
    
    RedBlackNode swap ;
    swap = inOrderSuccessorNode(pos.right);
    int temp;
    temp = swap.data;
    swap.data=pos.data;
    pos.data = temp;
    return swap;

 } 


 public static void deleteRedBlack(RedBlackNode root, int val) {
    RedBlackNode curr = searchVal(root, val);
    if (curr.left!=null && curr.right!=null) curr = swapToLeaf(curr, val); 
    RedBlackNode prev = curr.parent;

    if (curr.black==false) {
        if (prev.left==curr) {
            RedBlack n = new RedBlackNode(null);
            // n.parent = prev;
            prev.left=n;
        }
        else {
            RedBlack n = new RedBlackNode(null);
            // n.parent = prev;
            prev.right=n;
        } 
    } else if (curr.left.black==false && curr.left!=null){
        curr.left.black=true;
        if (prev.left==curr) {
            prev.left=curr.left;
            curr.left.parent = prev;
        }
        else {
            prev.right=curr.left;
            curr.left.parent = prev;
        }
    }

    else if (curr.left==null && curr.right==null){
        RedBlackNode brother = brotherSister(curr);
        if (prev.black=false){ // Case 1
            if (brother.black && (brother.left.black==false || brother.right.black==false)){ //Case 1.1
                RedBlackNode nephew = brother.left.black==false? brother.left : brother.right;
                if (nephew.data>brother.data){
                    nephew = leftRotate(brother);
                    
                } else {

                }
            } else { //Case 1.2

            }
        }
        else { // Case 2
            if (brother.black==false){ //Case 2.1

                if (brother.right.left.black && brother.right.right.black){ // Case 2.1.2

                } else { // Case 2.1.1

                }


            } else { //Case 2.2
                
                if (brother.black && (brother.left.black==false || brother.right.black==false)){ //Case 2.2.1

                } else { //Case 2.2.2
    
                }

            }

        }
    }



 }







 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
            //                                          DELETE ENDS HERE                                    //
 /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////































    public static void main(String[] args) {
        RedBlack tree = new RedBlack();
        tree.root= insertRBTree(tree.root, 41);
        inorderTraversal(tree.root);
        System.out.println();
        tree.root= insertRBTree(tree.root, 38);
        inorderTraversal(tree.root);
        System.out.println();
        tree.root= insertRBTree(tree.root, 31);
        inorderTraversal(tree.root);
        System.out.println();
        tree.root= insertRBTree(tree.root, 12);
        inorderTraversal(tree.root);
        System.out.println();
        tree.root= insertRBTree(tree.root, 19);
        inorderTraversal(tree.root);
        System.out.println();
        tree.root= insertRBTree(tree.root, 8);   
        inorderTraversal(tree.root);
        System.out.println();
        preorderTraversal(tree.root);
    }
}















/*



private boolean isNil(RedBlackNode node){

		// return appropriate value
		return node == nil;

}// end isNil(RedBlackNode node)





public void remove(RedBlackNode v){

    RedBlackNode z = search(v.data);

    // Declare variables
    RedBlackNode x = nil;
    RedBlackNode y = nil;

    // if either one of z's children is nil, then we must remove z
    if (isNil(z.left) || isNil(z.right))
        y = z;

    // else we must remove the successor of z
    else y = treeSuccessor(z);

    // Let x be the left or right child of y (y can only have one child)
    if (!isNil(y.left))
        x = y.left;
    else
        x = y.right;

    // link x's parent to y's parent
    x.parent = y.parent;

    // If y's parent is nil, then x is the root
    if (isNil(y.parent))
        root = x;

    // else if y is a left child, set x to be y's left sibling
    else if (!isNil(y.parent.left) && y.parent.left == y)
        y.parent.left = x;

    // else if y is a right child, set x to be y's right sibling
    else if (!isNil(y.parent.right) && y.parent.right == y)
        y.parent.right = x;

    // if y != z, trasfer y's satellite data into z.
    if (y != z){
        z.key = y.key;
    }

    // Update the numLeft and numRight numbers which might need
    // updating due to the deletion of z.key.
    fixNodeData(x,y);

    // If y's color is black, it is a violation of the
    // RedBlackTree properties so call removeFixup()
    if (y.color == RedBlackNode.BLACK)
        removeFixup(x);
}// end remove(RedBlackNode z)


public RedBlackNode<T> treeSuccessor(RedBlackNode<T> x){

    // if x.left is not nil, call treeMinimum(x.right) and
    // return it's value
    if (!isNil(x.left) )
        return treeMinimum(x.right);

    RedBlackNode<T> y = x.parent;

    // while x is it's parent's right child...
    while (!isNil(y) && x == y.right){
        // Keep moving up in the tree
        x = y;
        y = y.parent;
    }
    // Return successor
    return y;
}// end treeMinimum(RedBlackNode x)









private void fixNodeData(RedBlackNode<T> x, RedBlackNode<T> y){

		// Initialize two variables which will help us traverse the tree
		RedBlackNode<T> current = nil;
		RedBlackNode<T> track = nil;


		// if x is nil, then we will start updating at y.parent
		// Set track to y, y.parent's child
		if (isNil(x)){
			current = y.parent;
			track = y;
		}

		// if x is not nil, then we start updating at x.parent
		// Set track to x, x.parent's child
		else{
			current = x.parent;
			track = x;
		}

		// while we haven't reached the root
		while (!isNil(current)){
			// if the node we deleted has a different key than
			// the current node
			if (y.key != current.key) {

				// if the node we deleted is greater than
				// current.key then decrement current.numRight
				if (y.key.compareTo(current.key) > 0)
					current.numRight--;

				// if the node we deleted is less than
				// current.key thendecrement current.numLeft
				if (y.key.compareTo(current.key) < 0)
					current.numLeft--;
			}

			// if the node we deleted has the same key as the
			// current node we are checking
			else{
				// the cases where the current node has any nil
				// children and update appropriately
				if (isNil(current.left))
					current.numLeft--;
				else if (isNil(current.right))
					current.numRight--;

				// the cases where current has two children and
				// we must determine whether track is it's left
				// or right child and update appropriately
				else if (track == current.right)
					current.numRight--;
				else if (track == current.left)
					current.numLeft--;
			}

			// update track and current
			track = current;
			current = current.parent;

		}

}//end fixNodeData()



	// @param: x, the child of the deleted node from remove(RedBlackNode v)
	// Restores the Red Black properties that may have been violated during
	// the removal of a node in remove(RedBlackNode v)
private void removeFixup(RedBlackNode<T> x){

    RedBlackNode<T> w;

    // While we haven't fixed the tree completely...
    while (x != root && x.color == RedBlackNode.BLACK){

        // if x is it's parent's left child
        if (x == x.parent.left){

            // set w = x's sibling
            w = x.parent.right;

            // Case 1, w's color is red.
            if (w.color == RedBlackNode.RED){
                w.color = RedBlackNode.BLACK;
                x.parent.color = RedBlackNode.RED;
                leftRotate(x.parent);
                w = x.parent.right;
            }

            // Case 2, both of w's children are black
            if (w.left.color == RedBlackNode.BLACK &&
                        w.right.color == RedBlackNode.BLACK){
                w.color = RedBlackNode.RED;
                x = x.parent;
            }
            // Case 3 / Case 4
            else{
                // Case 3, w's right child is black
                if (w.right.color == RedBlackNode.BLACK){
                    w.left.color = RedBlackNode.BLACK;
                    w.color = RedBlackNode.RED;
                    rightRotate(w);
                    w = x.parent.right;
                }
                // Case 4, w = black, w.right = red
                w.color = x.parent.color;
                x.parent.color = RedBlackNode.BLACK;
                w.right.color = RedBlackNode.BLACK;
                leftRotate(x.parent);
                x = root;
            }
        }
        // if x is it's parent's right child
        else{

            // set w to x's sibling
            w = x.parent.left;

            // Case 1, w's color is red
            if (w.color == RedBlackNode.RED){
                w.color = RedBlackNode.BLACK;
                x.parent.color = RedBlackNode.RED;
                rightRotate(x.parent);
                w = x.parent.left;
            }

            // Case 2, both of w's children are black
            if (w.right.color == RedBlackNode.BLACK &&
                        w.left.color == RedBlackNode.BLACK){
                w.color = RedBlackNode.RED;
                x = x.parent;
            }

            // Case 3 / Case 4
            else{
                // Case 3, w's left child is black
                    if (w.left.color == RedBlackNode.BLACK){
                    w.right.color = RedBlackNode.BLACK;
                    w.color = RedBlackNode.RED;
                    leftRotate(w);
                    w = x.parent.left;
                }

                // Case 4, w = black, and w.left = red
                w.color = x.parent.color;
                x.parent.color = RedBlackNode.BLACK;
                w.left.color = RedBlackNode.BLACK;
                rightRotate(x.parent);
                x = root;
            }
        }
    }// end while

    // set x to black to ensure there is no violation of
    // RedBlack tree Properties
    x.color = RedBlackNode.BLACK;
}// end removeFixup(RedBlackNode x)











public RedBlackNode search(int key){

		// Initialize a pointer to the root to traverse the tree
		RedBlackNode current = root;

		// While we haven't reached the end of the tree
		while (!isNil(current)){

			// If we have found a node with a key equal to key
			if (current.key==(key))

				// return that node and exit search(int)
				return current;

			// go left or right based on value of current and key
			else if (current.key.compareTo(key) < 0)
				current = current.right;

			// go left or right based on value of current and key
			else
				current = current.left;
		}

		// we have not found a node whose key is "key"
		return null;


	}// end search(int key)
*/