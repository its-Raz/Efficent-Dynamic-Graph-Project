import java.util.HashMap;

public class GraphEdge extends Node {
private GraphNode src;
private GraphNode dst;

    public GraphEdge(GraphEdge prev,GraphEdge next,GraphNode src, GraphNode dst)
    {
        super(prev,next);
        this.src=src;
        this.dst=dst;
    }
public GraphEdge(GraphEdge prev,GraphNode src, GraphNode dst)
{
    this(prev,null,src,dst);
}
public GraphEdge(GraphNode src, GraphNode dst)
{
    this(null,null,src,dst);
}

    public GraphNode getSrc() {
        return src;
    }



    public GraphNode getDst() {
        return this.dst;
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
