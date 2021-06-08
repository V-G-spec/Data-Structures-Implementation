//Haven't checked if it is correct. Kinda scary, ngl


import java.util.*;

public class BFS {
    

    public static void BFSearch(Vector<Queue<Integer>> adjList, int s){
        ArrayList<Integer> distance = new ArrayList<Integer>(adjList.size());
        ListIterator<Integer> iterator = distance.listIterator();
        while (iterator.hasNext()) {
            iterator.set(Integer.MAX_VALUE);
        }

        Queue<Integer> oper = new LinkedList<Integer>();
        oper.add(s);
        distance.set(s, 0);
        while(oper.isEmpty()==false){
            int v = oper.remove();
            for (Integer iter: adjList.get(v)){
                if (distance.get(iter)==Integer.MAX_VALUE){
                    oper.add(iter);
                    distance.set(iter, distance.get(v)+1);
                }
            }
        }
    }


    public static void addEdge(Vector<Queue<Integer>> adjList, int u, int v) {
        adjList.get(u).add(v);
        adjList.get(v).add(u);
    }

    public static void printGraph(Vector<Queue<Integer>> adjList) {
        for (int i = 0; i < adjList.size(); i++) {
            System.out.print("\nVertex " + i + ":");
            for(Integer item: adjList.get(i)) {
                System.out.print(" -> " + item);
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter number of vertices: ");
        int numofvert = sc.nextInt();
        Vector<Queue<Integer>> graph = new Vector<Queue<Integer>>(numofvert);
        for (int i = 0; i < numofvert; i++) {
            graph.add(new LinkedList<Integer>());
        }
        System.out.println("Enter number of edges: ");
        int numofedge = sc.nextInt();
        for (int i=0; i<numofedge; i++) {
            addEdge(graph, sc.nextInt(), sc.nextInt());
        }
        sc.close();
        printGraph(graph);
    }
}
