import java.util.*;
public class MyHashTable extends COL106HashTable {
    public MyHashTable(int n, int test_id) {
        super(n, test_id);
    }

    
    int insert(int val) {
        int index = h1(val);
        if(table[index]!=0 && table[index]!=-1){
            int i;
            for(i = 1; i <=size; ++i){
                int index2 = h2(val);
                index2 = (index + i* index2)%size;
                if(table[index2]!=0 && table[index]!=-1){
                    continue;
                }
                else{
                    table[index2] = val;
                    return 0;
                }
            }
            if(i>size){
               return -1;
            }
        }
        else{
            table[index] = val;
            return 0;
        }
        return -1;
    }

    int search(int val) {
        // write your code here
        int index = h1(val);
        if(table[index]!=val){
            int i;
            
            for(i = 1; i <=size; ++i){
                int index2 = h2(val);
                index2 = (index + i* index2)%size;
                if(table[index2]!=val){
                    continue;
                }
                else{
                    return index2;
                }
            }
            if(i>size){
               return -1;
            }
        }
        else{
            
            return index;
        }
        return -1;
    }
} 
