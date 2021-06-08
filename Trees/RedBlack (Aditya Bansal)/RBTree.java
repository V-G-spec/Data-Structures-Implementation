package RedBlack;


public class RBTree<T extends Comparable, E> implements RBTreeInterface<T, E>  {

	RedBlackNode<T,E> root = null;
	
	public void recolor(RedBlackNode<T,E> rbn) {
			if(rbn.parent.clr!=false && rbn.uncle.clr==true) {
				rbn.parent.parent.clr=true;
				rbn.uncle.change();
				rbn.parent.change();
					balance(rbn.parent.parent);
				}
				
	}
		
	
	
	public void swap(RedBlackNode<T,E> r1,RedBlackNode<T,E> r2) {
		boolean temp=r1.clr;
		r1.clr=r2.clr;
		r2.clr=temp;
	}
	
	public void rotateRight(boolean check,RedBlackNode<T,E> gp) {
		RedBlackNode<T,E> parent = gp.left;
		RedBlackNode<T,E> ggparent = gp.parent;
		
			gp.left = parent.right;
			if(gp.left!=null) {
				gp.left.parent = gp;
			}
			
				
			gp.parent=parent;
			parent.right=gp;
			parent.parent = ggparent;
			if(ggparent==root&&check==false) {root=parent;}
			if(gp==root&&check==true) {root=parent;}
			if(ggparent!=null) {
				if(ggparent.right==gp) {
					ggparent.right=parent;
				}
				else {
					ggparent.left=parent;
				}
			}
	}

	public void rotateLeft(boolean check,RedBlackNode<T,E> gp) {
		RedBlackNode<T,E> parent = gp.right;
		RedBlackNode<T,E> ggparent = gp.parent;
		
			gp.right = parent.left;
			if(gp.right!=null) {
				gp.right.parent = gp;
				
			}
				
			gp.parent=parent;
			parent.left=gp;
			parent.parent = ggparent;
			if(ggparent==root&&check==false) {root=parent;}
			if(gp==root&&check==true) {root=parent;}
			if(ggparent!=null) {
				if(ggparent.right==gp) {
					ggparent.right=parent;
				}
				else {
					ggparent.left=parent;
				}
			}
	}
	
	public void rotate(RedBlackNode<T,E> rbn) {
		if(rbn.uncle!=null){
			if(rbn.uncle.clr==false && rbn.parent.clr!=false) {
				if(rbn.parent.parent.left==rbn.parent && rbn.parent.left==rbn) {
					rotateRight(true,rbn.parent.parent);
					swap(rbn.parent,rbn.parent.right);
				}
				
				else if(rbn.parent.parent.right==rbn.parent && rbn.parent.right==rbn) {
					rotateLeft(true,rbn.parent.parent);
					swap(rbn.parent,rbn.parent.left);
				}
				
				else if(rbn.parent.parent.left==rbn.parent && rbn.parent.right==rbn) {
					rotateLeft(false,rbn.parent);
					rotateRight(true,rbn.parent);
					swap(rbn,rbn.right);
				}
				
				else if(rbn.parent.parent.right==rbn.parent && rbn.parent.left==rbn) {
					rotateRight(false,rbn.parent);
					rotateLeft(true,rbn.parent);
					swap(rbn,rbn.left);
				}
			}
		}
		
		
		else if(rbn.parent!=null) {
			if(rbn.parent.parent!=null) {
				if(rbn.parent.parent.left==rbn.parent && rbn.parent.left==rbn) {
					rotateRight(true,rbn.parent.parent);
					swap(rbn.parent,rbn.parent.right);
				}
				
				else if(rbn.parent.parent.right==rbn.parent && rbn.parent.right==rbn) {
					rotateLeft(true,rbn.parent.parent);
					swap(rbn.parent,rbn.parent.left);
				}
				
				else if(rbn.parent.parent.left==rbn.parent && rbn.parent.right==rbn) {
					rotateLeft(false,rbn.parent);
					rotateRight(true,rbn.parent);
					swap(rbn,rbn.right);
				}
				
				else if(rbn.parent.parent.right==rbn.parent && rbn.parent.left==rbn) {
					rotateRight(false,rbn.parent);
					rotateLeft(true,rbn.parent);
					swap(rbn,rbn.left);
				}
			}
		}
			
	}
	
	public void balance(RedBlackNode<T,E> rbn) {
		rbn.uncle=unclify(rbn);
		if(rbn.uncle!=null) {
			recolor(rbn);
		}
		
			rotate(rbn);
	}
	
	public RedBlackNode<T,E> unclify(RedBlackNode<T,E> rbn) {
		if(rbn.parent!=null) {
			if(rbn.parent.parent!=null) {
				if(rbn.parent.parent.left==rbn.parent && rbn.parent.parent.right!=null) {
					return rbn.parent.parent.right;
				}
				else if(rbn.parent.parent.right==rbn.parent && rbn.parent.parent.left!=null) {
					return rbn.parent.parent.left;
				}
			}
			
		}
		return null;
	}
	
	public void ins(RedBlackNode<T,E> rt,RedBlackNode<T,E> rbn) {
		RedBlackNode<T,E> parent = rt;
		if(parent.key.compareTo(rbn.key)>0) {
			if(parent.left==null) {
				rbn.parent = parent;
				parent.left=rbn;
				rbn.uncle=unclify(rbn);
				balance(rbn);
			}
			else {
				ins(parent.left,rbn);
			}
		}
		else if(parent.key.compareTo(rbn.key)<0) {
			if(parent.right==null) {
				rbn.parent=parent;
				parent.right=rbn;
				rbn.uncle=unclify(rbn);
				balance(rbn);
			}
			else {
				ins(parent.right,rbn);
			}
		}
		else {
			parent.arr.add(rbn.value);
		}
		if(root!=null) {
			if(root.clr==true) {
				root.change();
			}
		}
	}
	
    @Override
    public void insert(T key, E value) {
    	RedBlackNode<T,E> rbn =new RedBlackNode<T,E>(key,value,true);
    	if(root==null) {
    		root = rbn;
    		root.change();
    	}
    	
    	else{
    		ins(root,rbn);
    	}
//    	printTree(root);
    }

    @Override
    public RedBlackNode<T, E> search(T key) {
    	//printTree(root);
    	RedBlackNode<T, E> rt = root;
		boolean b = true;
		while(b!=false && rt!=null) {
			if(rt.key.compareTo(key)>0) {
				rt=rt.left;
			}
			else if(rt.key.compareTo(key)<0) {
				rt=rt.right;
			}
			else if (rt.key.compareTo(key)==0){
				b=false;
			}
		}
		if(b==false) {
			return rt;
		}
		else {
			return null;
		}
		
    }
    
    
void printTree(RedBlackNode<T,E> root)
  {
  	if(root==null)
  	return;
  	else
  	{
  		System.out.println("{"+root.key+" "+root.clr+"}");
  		printTree(root.left);
  		
  		printTree(root.right);
  	}
  }

void iprintTree(RedBlackNode<T,E> root)
{
	if(root==null)
	return;
	else
	{
		
		iprintTree(root.left);
		System.out.println("{"+root.key+" "+root.clr+"}");
		iprintTree(root.right);
		
	}
}

}