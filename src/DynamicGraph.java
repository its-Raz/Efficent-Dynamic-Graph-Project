

public class DynamicGraph {
    NodeLinkedList<GraphNode> Nodes;


    public DynamicGraph()
    {
        this.Nodes = new NodeLinkedList<GraphNode>();

    }

    public GraphNode insertNode(int key)
    {
        GraphNode newNode = new GraphNode(key,Nodes.getLast());

        Nodes.insert(newNode);
        return newNode;

    }

    public void deleteNode(GraphNode node)
    {
        if(node.getInDegree()==0 && node.getOutDegree()==0)
        {
            this.Nodes.delete(node);
        }
        else{
        System.out.println("in/out degree are not 0");}
    }

    public GraphEdge insertEdge(GraphNode src, GraphNode dst)
    {
        src.increaseOutDeg();
        dst.increaseInDeg();
        NodeLinkedList<GraphEdge> srcNeighbors = src.getNeighborsList();
        GraphEdge newEdge = new GraphEdge(src,dst);
        srcNeighbors.insert(newEdge);
        return newEdge;
    }

    public void deleteEdge(GraphEdge edge)
    {

        GraphNode src = edge.getSrc();
        GraphNode dst = edge.getDst();
        src.decreaseOutDeg();
        dst.decreaseInDeg();
        NodeLinkedList<GraphEdge> srcNeighbors = src.getNeighborsList();
        srcNeighbors.delete(edge);
    }

    public RootedTree scc(){return new RootedTree();};

    public RootedTree bfs(GraphNode source)
    {
        RootedTree bfsTree = new RootedTree();
        bfsTree.setRoot(source);
        bfsInit(source);
        NodeLinkedList<RootedTree> queue = new NodeLinkedList<>();
        queue.insert(bfsTree);
        RootedTree currentSubTree = bfsTree;
        GraphNode currentNode;
        GraphNode currentNeighbor;
        NodeLinkedList<GraphEdge> currNeighborsList;
        GraphEdge currentEdge;
        while(queue.getSize()!=0)
        {
            currentSubTree = queue.dequeue();
            currentNode = currentSubTree.getRoot();
            currNeighborsList = currentNode.getNeighborsList();
            currentEdge = currNeighborsList.getLast();
            while(currentEdge!=null)
            {
                currentNeighbor = currentEdge.getDst();

                if(currentNeighbor.getColor() == Color.WHITE)
                {
                    RootedTree neighborSubTree = new RootedTree(currentSubTree,currentNeighbor, currentNeighbor.getKey());
                    currentSubTree.addChild(neighborSubTree);
                    currentNeighbor.setColor(Color.GRAY);
//                    currentNeighbor.setDistance(currentNode.getDistance()+1);
//                    currentNeighbor.setParent(currentNode);
                    queue.insert(neighborSubTree);

                }
                currentEdge = (GraphEdge) currentEdge.getPrev();
            }
            currentNode.setColor(Color.BLACK);
        }
        return bfsTree;
    };

    public void bfsInit(GraphNode source)
    {
        NodeLinkedList<GraphEdge>  neighborsList = source.getNeighborsList();
        GraphNode currentNode = neighborsList.getFirst().getDst();
        while(currentNode!=null)
        {
            currentNode.setDistance(Integer.MAX_VALUE);
            currentNode.setColor(Color.WHITE);
            currentNode.setParent(null);
            currentNode = currentNode.getNext();
        }
        source.setDistance(0);
        source.setColor(Color.GRAY);
        source.setParent(null);
    }




}
