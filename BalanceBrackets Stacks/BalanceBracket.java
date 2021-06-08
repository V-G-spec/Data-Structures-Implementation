import java.util.Scanner;

public class BalanceBracket extends Stack {
    public static void main(String[] args) {
        StackByLinkedList s = new StackByLinkedList();
        boolean flag;
        flag = true;
        Scanner sc = new Scanner(System.in);
        String input = sc.next();
        for(int i=0; i<input.length(); i++) {
            char a = input.charAt(i);
            if (a=='{' | a=='(' | a=='[') {
                s.push(a);
            }

            else if ((a=='}' & s.stackTop()=='{') | ( a==')' & s.stackTop()=='(') | (a==']' & s.stackTop()=='[') ) {
                s.pop();
            }

            else flag=false;
        }
        if ((s.isEmpty()!=true) | (flag== false)) {
            System.out.println("NO");
        } else {
            System.out.println("YES");
        }
        

        sc.close();

        
    }   
}
