import java.util.*;

/**
 * AdjacencyList
 */
public class AdjacencyList {
    public static void addEdge(ArrayList<ArrayList<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public static void printGraph(ArrayList<ArrayList<Integer>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print("\nVertex " + i + ":");
            for (int j = 0; j < adjList.get(i).size(); j++) {
                System.out.print(" -> " + adjList.get(i).get(j));
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
        for (int i = 0; i < num; i++) {
            adjList.add(new ArrayList<Integer>());
        }
        int numOfOper = sc.nextInt();
        for (int i=0; i<numOfOper; i++) {
            addEdge(adjList, sc.nextInt(), sc.nextInt());
        }
        printGraph(adjList);
        sc.close();
    }
}