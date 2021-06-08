import java.util.*;

public class AdjacencyMatrix {
    
    public static void addEdge(boolean[][] adjMat, int u, int v) {
        adjMat[u][v] = true;
        adjMat[v][u] = true;
    }

    public static void remEdge(boolean[][] adjMat, int u, int v) {
        adjMat[u][v] = false;
        adjMat[v][u] = false;
    }
    
    public static void printGraph(boolean[][] adjMat){
        for(int i=0; i<adjMat.length; i++) {
            System.out.print("Vertex "+i + ":");
            for (int j=0; j<adjMat.length; j++){
                if (adjMat[i][j]==true) System.out.print(" "+j+",");
            }
            System.out.println();
        }
    }


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int totver = sc.nextInt();
        boolean[][] adjMat = new boolean[totver][totver];
        int numOfOper = sc.nextInt();
        for (int i=0; i<numOfOper; i++) {
            addEdge(adjMat, sc.nextInt(), sc.nextInt());
        }
        printGraph(adjMat);
        sc.close();
    }    
}
