

public class GraphEdge  {
    private Node<GraphEdge> listNode;
private GraphNode src;
private GraphNode dst;

    public GraphEdge(GraphNode src, GraphNode dst)
    {
        this.src=src;
        this.dst=dst;
    }



    public GraphNode getSrc() {
        return src;
    }



    public GraphNode getDst() {
        return this.dst;
    }

    public Node<GraphEdge> getListNode() {
        return listNode;
    }

    public void setListNode(Node<GraphEdge> listNode) {
        this.listNode = listNode;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphEdge) {
            GraphEdge other = (GraphEdge) obj;
            if ((this.src.getKey() == other.getSrc().getKey()) && (this.dst.getKey() == other.getDst().getKey())) {
                return true;
            }
        }
        return false;
    }
}
