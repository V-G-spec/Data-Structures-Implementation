package RedBlack;
import java.util.ArrayList;

import Util.RBNodeInterface;

import java.util.List;

import Trie.Person;

public class RedBlackNode<T extends Comparable, E> implements RBNodeInterface<E> {
	T key=null;
	E value=null;
	ArrayList<E> arr = new ArrayList<E>();
	boolean clr=false;
	RedBlackNode<T,E> parent = null;
	RedBlackNode<T,E> uncle = null;
	RedBlackNode<T,E> left = null;
	RedBlackNode<T,E> right = null;
	
	public RedBlackNode(T key, E value, boolean clr) {
		this.key = key;
		this.value = value;
		this.clr = clr;
		arr.add(value);
	}
	
	public void change() {
		if(this.clr==false) {
			this.clr = true;
		}
		else {
			this.clr = false;
		}
	}
	

    @Override
    public E getValue() {
        return value;
    }

    @Override
    public List<E> getValues() {
    	ArrayList<E> arry = new ArrayList<E>();
    	for(int i =0 ; i<arr.size();i++) {
    		Person p=(Person)arr.get(i);
    		String s = p.getprint();
    		arry.add((E)s);
    		
    	}
        return arry;
    }
}
