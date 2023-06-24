

public class GraphNode {
    private Node<GraphNode> listNode;
    private int key;
    private int inDegree;
    private int outDegree;
    private NodeLinkedList<GraphEdge> neighborsList;
    private NodeLinkedList<GraphEdge> neighborsListTranspose;

    private Color color;
    private int disc;
    private int ret;


    public GraphNode(int key) {
        this.key = key;
        this.inDegree = 0;
        this.outDegree = 0;
        this.neighborsList = new NodeLinkedList<GraphEdge>();
        this.neighborsListTranspose = new NodeLinkedList<GraphEdge>();
    }






    public int getOutDegree(){return this.outDegree;}

    public int getInDegree(){return this.inDegree;}




    public int getKey(){return this.key;}

    public void increaseInDeg()
    {
        this.inDegree++;
    }
    public void increaseOutDeg()
    {
        this.outDegree++;
    }
    public void decreaseInDeg()
    {
        this.inDegree--;
    }
    public void decreaseOutDeg()
    {
        this.outDegree--;
    }




    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setListNode(Node<GraphNode> node) {
        this.listNode = node;
    }

    public Node<GraphNode> getListNode() {
        return listNode;
    }

    public NodeLinkedList<GraphEdge> getNeighborsList() {
        return neighborsList;
    }

    public int getDisc() {
        return disc;
    }

    public void setDisc(int disc) {
        this.disc = disc;
    }

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public NodeLinkedList<GraphEdge> getNeighborsListTranspose() {
        return neighborsListTranspose;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof GraphNode) {
            GraphNode other = (GraphNode) obj;
            if ((this.getKey() == other.getKey())) {
                return true;
            }
        }
        return false;
    }


}
