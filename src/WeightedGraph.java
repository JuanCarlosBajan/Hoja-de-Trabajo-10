public class WeightedGraph {
    static class Edge {
        String source;
        String destination;
        int weight;

        public Edge(String source, String destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }

        public String getSource() {
            return source;
        }

        public String getDestination() {
            return destination;
        }

        public int getWeight() {
            return weight;
        }
    }
}