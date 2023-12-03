import java.io.File;
import java.util.*;

public class App {
    public static void main(String[] args) throws Exception {
        Graph graph = new Graph();
        Scanner scanner = new Scanner(new File("graph.txt"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.length() == 1) {
                graph.addVertex(new Vertex(line));
            } else {
                String[] parts = line.split(" ");
                graph.addEdge(
                        new Edge(graph.getVertex(parts[0]), graph.getVertex(parts[1]), Integer.parseInt(parts[2])));
            }
        }
        scanner.close();

        System.out.println("Enter a vertex:");
        scanner = new Scanner(System.in);
        String start = scanner.nextLine();
        scanner.close();

        Map<Vertex, Integer> shortestPaths = graph.dijkstra(graph.getVertex(start));
        for (Map.Entry<Vertex, Integer> entry : shortestPaths.entrySet()) {
            System.out.println(entry.getKey().getName() + " " + entry.getValue());
        }
    }
}
