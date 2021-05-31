import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Graph {
    int vertices;
    LinkedList<WeightedGraph.Edge>[] adjacencylist;
    HashMap<String,Integer> trads;
    int actualIndex;
    int[][] matrix;

    Graph(int vertices) {
        this.vertices = vertices;
        this.trads = new HashMap<String,Integer>();
        this.actualIndex = 0;
        adjacencylist = new LinkedList[vertices];
        //initialize adjacency lists for all the vertices
        for (int i = 0; i <vertices ; i++) {
            adjacencylist[i] = new LinkedList<>();
        }
    }

    public void addEdge(String source, String destination, int weight) {
        if(!trads.containsKey(source)){
            trads.put(source,actualIndex);
            System.out.println(trads.get(source));
            actualIndex++;
        }
        WeightedGraph.Edge edge = new WeightedGraph.Edge(source, destination, weight);
        adjacencylist[trads.get(source)].addFirst(edge);
    }

    public void printGraph(){
        for (int i = 0; i <vertices ; i++) {
            LinkedList<WeightedGraph.Edge> list = adjacencylist[i];
            for (int j = 0; j <list.size() ; j++) {
                System.out.println("vertex-" + list.get(0).getSource() + " is connected to " +
                        list.get(j).destination + " with weight " +  list.get(j).weight);
            }
        }
    }

    public void modify(String source, String destiny,Integer weight){
        matrix[trads.get(source)][trads.get(destiny)] = weight;
    }

    public void GenerateMatrix(){
        matrix = new int[trads.size()][trads.size()];
        for (int i = 0; i < trads.size() ; i++){
            for (int j = 0; j < trads.size(); j++){
                if (i!=j) matrix[i][j] = 99999;
            }
        }
        LinkedList<WeightedGraph.Edge>[] local = adjacencylist;
        for (int i = 0; i < trads.size() ; i++){
            for (int j = 0; j < local[i].size();j++){
                matrix[i][trads.get(local[i].get(j).getDestination())] = local[i].get(j).getWeight();
            }
        }
        /*
        for (int i = 0; i < trads.size() ; i++){
            for (int j = 0; j < trads.size();j++){
                System.out.print(matrix[i][j] + "  ");
            }
            System.out.print("\n");
        }*/
        //floydWarshall(matrix);
    }


    // Implementing floyd warshall algorithm
    int[][] floydWarshall(int graph[][]) {
        int nV = trads.size();
        int m[][] = new int[nV][nV];
        int i, j, k;

        for (i = 0; i < nV; i++)
            for (j = 0; j < nV; j++)
                m[i][j] = graph[i][j];

        // Adding vertices individually
        for (k = 0; k < nV; k++) {
            for (i = 0; i < nV; i++) {
                for (j = 0; j < nV; j++) {
                    if (m[i][k] + m[k][j] < m[i][j])
                        m[i][j] = m[i][k] + m[k][j];
                }
            }
        }
        return m;
    }

    int askForRoute(String source,String destination){
        return  floydWarshall(matrix)[trads.get(source)][trads.get(destination)];
    }

    void printMatrix(int matrix[][]) {
        int INF = 99999, nV = trads.size();
        for (int i = 0; i < nV; ++i) {
            for (int j = 0; j < nV; ++j) {
                if (matrix[i][j] == INF)
                    System.out.print("INF\t");
                else
                    System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
    }

    String getCenter(){
        ArrayList<Integer> eccentricity = new ArrayList<>();
        int nV = trads.size();
        for (int i = 0; i < nV; ++i) {
            int biggest = 0;
            for (int j = 0; j < nV; ++j) {
                if (matrix[i][j] != 0 &&  matrix[i][j] > biggest) biggest = matrix[i][j];
            }
            eccentricity.add(biggest);
        }
        int lowest = 99999;
        int lowestIndex = -1;
        for (int i = 0; i < nV; ++i){
            if(eccentricity.get(i) < lowest){
                lowest = eccentricity.get(i);
                lowestIndex = i;
            }
        }
        if (lowestIndex == -1){
            return "No hay centro";
        }
        return adjacencylist[lowestIndex].getFirst().getSource();
    }

    public void verifyVerts(){
        for (int i = 0; i < adjacencylist.length; ++i) {
            int biggest = 0;
            for (int j = 0; j < adjacencylist[i].size(); ++j) {
                if (!trads.containsKey(adjacencylist[i].get(j).getDestination())){
                    addEdge(adjacencylist[i].get(j).getDestination(),adjacencylist[i].get(j).getDestination(),0);
                }
            }
        }
    }
}