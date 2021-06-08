import java.util.*;

class Path{
    
    //Do not change the function signature
    String uniquePath2(TreeNode root, int u, int v)throws Exception{

        int value = root.getVal();
        if (value==u && value==v){
            return String.valueOf(value);
        }
        else if (root.getLeft()==null && root.getRight()==null){
            throw new Exception("Element not found");
        }

        else if (value == u && value<v) return value + " " + uniquePath2(root.getRight(), root.getRight().getVal(), v);
        else if (value == u && value>v) return value + " " + uniquePath2(root.getLeft(), root.getLeft().getVal(), v);
        else if (value == v && value<u) return uniquePath2(root.getRight(), u, root.getRight().getVal()) + " " + value;
        else if (value == v && value>u) return uniquePath2(root.getLeft(), u, root.getLeft().getVal()) + " " + value;

        else if (value<u && value>v) return uniquePath2(root.getRight(), u, root.getRight().getVal()) + " " +value + " "+ uniquePath2(root.getLeft(), root.getLeft().getVal(), v);
        else if (value<v && value>u) return uniquePath2(root.getLeft(), u, root.getLeft().getVal()) + " " +value + " "+ uniquePath2(root.getRight(), root.getRight().getVal(), v);
        else if (value<u && value<v) return uniquePath2(root.getRight(), u, v);
        else return uniquePath2(root.getLeft(), u, v);
        



    }

    Vector pathRootToNode(TreeNode root, int a) throws Exception{
        Vector<Integer> vec = new Vector<Integer>(); //To initialize a vector of Integers.
        
        while(root!=null){
            int value = root.getVal();
            if (value<a){
                vec.add(value);
                root=root.getRight();
            } else if (value>a){
                vec.add(value);
                root = root.getLeft();
            } else if (value==a){
                vec.add(value);
                break;
            }
        }
        int n = vec.size();

        if (vec.isEmpty() || vec.get(n-1)!=a) throw new Exception();
        else return vec;
    }


    String uniquePath3(TreeNode root, int u, int v)throws Exception{
        
        
        Vector v1 = pathRootToNode(root, u); //1
        Vector v2 = pathRootToNode(root, v); // 1 2 3 4

        int n1=v1.size(); // 1
        int n2 = v2.size(); // 4

        int i =0;
        while ( i<Math.min(n1, n2) && v1.get(i) == v2.get(i) ){
            i++;
        } // i = 1
        i-=1; //i=0
        String unique = "";
        for (int j=n1-1; j>i-1; j--){ //0->~
            unique+=v1.get(j);
            unique+=" ";
        }
        for (int j=i+1; j<n2; j++){ //0-->3
            unique = unique + v2.get(j) + " ";
        }

        return unique;
    }


    String uniquePath(TreeNode root, int u, int v)throws Exception{
        int key = root.getVal();
        while(true){
            int value = root.getVal();

            if ((value<=u && value>=v) || (value<=v && value>=u) ){
                break;
            }
            else if (value<u && value<v){
                root = root.getRight();
            } else if (value>u && value>v){
                root = root.getLeft();
            }
        }

        Vector v1 = pathRootToNode(root, u);
        Vector v2 = pathRootToNode(root, v);
        int n1=v1.size(); // 1
        int n2 = v2.size();
        String unique = "";
        for (int i=n1-1; i>=0; i--){
            unique = unique + " " + v1.get(i);
        }
        for (int i=1; i<n2; i++){
            unique = unique + " " + v2.get(i);
        }

        return unique;
    }




}

//Do not change anything below this
class TreeNode{
    private int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(int v){
        this.val = v;
        this.left = null;
        this.right = null;
    }
    
    int getVal(){
        return this.val;
    }
    
    TreeNode getLeft(){
        return this.left;
    }
    
    TreeNode getRight(){
        return this.right;
    }
    
}



class BST{
    
    TreeNode root;

    public BST(){
        this.root = null;
    }    

    void insert(int v){
        
        TreeNode toInsert = new TreeNode(v);
        
        if(this.root == null){
            this.root = toInsert;
        }
        else{
            TreeNode prev = null;
            TreeNode temp = this.root;
            
            while(temp != null){
                
                prev = temp;
                if(v < temp.getVal())
                    temp = temp.left;
                else
                    temp = temp.right;
                
            }
            
            if(v < prev.getVal())
                prev.left = toInsert;
            else
                prev.right = toInsert;
        }
    }
}

// Input: 8 6 3 7 1 2 10 9 12 11 13                                                
// u = 1, v = 13                                                                   
// Your Output: 8 10 12 13                                                         
// Expected Output: 1 3 6 8 10 12 13                                               
// Incorrect                                                                       
// Input: 3 4 1 2                                                                  
// u = 1, v = 4                                                                    
// Your Output: 3 4                                                                
// Expected Output: 1 3 4                                                          
// Incorrect  