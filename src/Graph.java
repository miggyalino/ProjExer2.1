import java.util.*;

public class Graph {
    private Map<String, Vertex> vertices;

    public Graph() {
        this.vertices = new HashMap<>();
    }

    public void addVertex(Vertex vertex) {
        vertices.put(vertex.getName(), vertex);
    }

    public Vertex getVertex(String name) {
        return vertices.get(name);
    }

    public void addEdge(Edge edge) {
        edge.getStart().addEdge(edge);
    }

    public Map<Vertex, Integer> dijkstra(Vertex start) {
        Map<Vertex, Integer> distances = new HashMap<>();
        for (Vertex vertex : vertices.values()) {
            distances.put(vertex, Integer.MAX_VALUE);
        }
        distances.put(start, 0);

        PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(distances::get));
        queue.add(start);

        while (!queue.isEmpty()) {
            Vertex current = queue.poll();
            for (Edge edge : current.getEdges()) {
                Vertex neighbor = edge.getEnd();
                int distance = distances.get(current) + edge.getWeight();
                if (distance < distances.get(neighbor)) {
                    distances.put(neighbor, distance);
                    queue.remove(neighbor);
                    queue.add(neighbor);
                }
            }
        }

        return distances;
    }
}