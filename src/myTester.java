import java.io.DataOutputStream;
import java.io.IOException;
import static java.lang.System.out;
public class myTester {
    public static void main(String[] args) throws IOException{
        // Create a new dynamic graph
        DynamicGraph graph = new DynamicGraph();

        // Insert graph nodes
        GraphNode node1 = graph.insertNode(1);
        GraphNode node2 = graph.insertNode(2);
        GraphNode node3 = graph.insertNode(3);
        GraphNode node4 = graph.insertNode(4);
        GraphNode node5 = graph.insertNode(5);
        GraphNode node6 = graph.insertNode(6);
        GraphNode node7 = graph.insertNode(7);
        GraphNode node8 = graph.insertNode(8);


        // Insert graph edges
        graph.insertEdge(node1, node2);
        graph.insertEdge(node1, node3);
        graph.insertEdge(node2, node7);
        graph.insertEdge(node2, node8);
        graph.insertEdge(node3, node4);

        graph.insertEdge(node4, node6);
        graph.insertEdge(node4, node5);

        // Perform BFS starting from node1
        RootedTree bfsTree = graph.bfs(node1);
        DataOutputStream outStream = new DataOutputStream(out);
        bfsTree.printByLayer(outStream);
        bfsTree.preorderPrint(outStream);
    }
    public static void printGraph(DynamicGraph graph) {
        NodeLinkedList<GraphNode> nodes = graph.Nodes;
        Node<GraphNode> currNode = nodes.getFirst().getListNode();

        while (currNode != null) {
            GraphNode node = currNode.getData();
            System.out.println("Node: " + node.getKey());

            NodeLinkedList<GraphEdge> neighbors = node.getNeighborsList();
            if(neighbors.getSize()!=0){
            Node<GraphEdge> currNeighbor = neighbors.getFirst().getListNode();
            while (currNeighbor != null) {
                GraphEdge edge = currNeighbor.getData();
                System.out.println("Edge: " + edge.getSrc().getKey() + " -> " + edge.getDst().getKey());
                currNeighbor = currNeighbor.getNext();
            }

        }
            currNode = currNode.getNext();
        }
    }
}