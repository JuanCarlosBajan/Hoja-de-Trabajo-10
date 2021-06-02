import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    @Test
    void addEdge() {
        int vertices = 100;
        Graph graph = new Graph(vertices);
        graph.addEdge("Juanca", "Luisa", 1);
        graph.addEdge("Juanca", "Papa", 7);
        graph.addEdge("Mama", "Papa", 4);
        graph.addEdge("Juanca", "Mama", 2);
        graph.addEdge("Papa", "Mama", 3);
        graph.addEdge("Luisa", "Juanca", 1);
        graph.GenerateMatrix();
        System.out.println(graph.askForRoute("Luisa","Papa"));
    }

    @Test
    void floydWarshall() {
        int vertices = 100;
        Graph graph = new Graph(vertices);
        graph.addEdge("Juanca", "Luisa", 1);
        graph.addEdge("Juanca", "Papa", 7);
        graph.addEdge("Mama", "Papa", 4);
        graph.addEdge("Juanca", "Mama", 2);
        graph.addEdge("Papa", "Mama", 3);
        graph.addEdge("Luisa", "Juanca", 1);
        graph.GenerateMatrix();
        graph.floydWarshall(graph.getMatrix());
        System.out.println(graph.askForRoute("Luisa","Papa"));
        System.out.println(graph.getCenter());
    }

    @Test
    void getCenter() {
        int vertices = 100;
        Graph graph = new Graph(vertices);
        graph.addEdge("Juanca", "Luisa", 1);
        graph.addEdge("Juanca", "Papa", 7);
        graph.addEdge("Mama", "Papa", 4);
        graph.addEdge("Juanca", "Mama", 2);
        graph.addEdge("Papa", "Mama", 3);
        graph.addEdge("Luisa", "Juanca", 1);
        graph.GenerateMatrix();
        System.out.println(graph.askForRoute("Luisa","Papa"));
        System.out.println(graph.getCenter());
    }

    @Test
    void verifyVerts() {
    }
}