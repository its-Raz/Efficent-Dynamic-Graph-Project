
import java.util.Arrays;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Random;
import static java.lang.System.out;
public class myTester {
    public static void main(String[] args) throws IOException {
        DataOutputStream outStream = new DataOutputStream(out);


        DynamicGraph graph = createGraph(); // Create a sample graph

        GraphNode source = graph.Nodes.getFirst(); // Set the source node for BFS

        RootedTree bfsTree = graph.bfs(source); // Perform BFS
        bfsTree.printByLayer(outStream);



//        // Print parent-child relationships
//        System.out.println("Parent-Child Relationships:");
//        printParentChildRelationships(graph.Nodes);
    }

    private static DynamicGraph createGraph() {
        DynamicGraph graph = new DynamicGraph();

        GraphNode node1 = graph.insertNode(1);
        GraphNode node2 = graph.insertNode(2);
        GraphNode node3 = graph.insertNode(3);
        GraphNode node4 = graph.insertNode(4);
        GraphNode node5 = graph.insertNode(5);

        graph.insertEdge(node1, node2);
        graph.insertEdge(node1, node3);
        graph.insertEdge(node2, node4);
        graph.insertEdge(node3, node4);
        graph.insertEdge(node4, node5);

        return graph;
    }

    private static void printTree(GraphNode root) {
        if (root != null) {
            printTreeRecursive(root, 0);
        }
    }

    private static void printTreeRecursive(GraphNode node, int depth) {
        StringBuilder indentation = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            indentation.append("  ");
        }

        System.out.println(indentation.toString() + node.getKey());

        NodeLinkedList<GraphEdge> neighborsList = node.getNeighborsList();
        GraphEdge edge = neighborsList.getFirst();

        while (edge != null) {
            printTreeRecursive(edge.getDst(), depth + 1);
            edge = (GraphEdge) edge.getNext();
        }
    }

    private static void printDistances(NodeLinkedList<GraphNode> nodeList) {
        GraphNode node = nodeList.getFirst();

        while (node != null) {
            System.out.println("Node " + node.getKey() + ": Distance = " + node.getDistance());
            node = node.getNext();
        }
    }


}


