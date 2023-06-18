

public class GraphNode extends Node {
    private int key;
    private int inDegree;
    private int outDegree;
    private NodeLinkedList<GraphEdge> neighborsList;

    private GraphNode parent;
    private Color color;

    private int distance;


    public GraphNode(int key, GraphNode prev,GraphNode next) {
        super(prev,next);
        this.key = key;
        this.inDegree = 0;
        this.outDegree = 0;
        this.neighborsList = new NodeLinkedList<GraphEdge>();

    }
    public GraphNode(int key, GraphNode prev) {
        this(key,prev,null);

    }
    public GraphNode(int key)
    {
        this(key,null,null);
    }


    public GraphNode getNext() {
        return (GraphNode) super.getNext();
    }

    public void setNext(GraphNode next) {
        super.setNext(next);
    }

    public GraphNode getPrev() {
        return (GraphNode) super.getPrev();
    }

    public void setPrev(GraphNode prev) {
        super.setPrev(prev);
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

    public GraphNode getParent() {
        return parent;
    }

    public void setParent(GraphNode parent) {
        this.parent = parent;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public NodeLinkedList<GraphEdge> getNeighborsList() {
        return neighborsList;
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
