

public class DynamicGraph {
    NodeLinkedList<GraphNode> Nodes;


    public DynamicGraph()
    {
        this.Nodes = new NodeLinkedList<GraphNode>();

    }

    public GraphNode insertNode(int key)
    {
        GraphNode newGraphNode = new GraphNode(key);
        Node<GraphNode> listNode = Nodes.insert(newGraphNode);
        newGraphNode.setListNode(listNode);
        return newGraphNode;

    }

    public void deleteNode(GraphNode node)
    {
        if(node.getInDegree()==0 && node.getOutDegree()==0)
        {
            this.Nodes.delete(node.getListNode());
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
        Node<GraphEdge> listNode = srcNeighbors.insert(newEdge);
        newEdge.setListNode(listNode);
        return newEdge;
    }

    public void deleteEdge(GraphEdge edge)
    {

        GraphNode src = edge.getSrc();
        GraphNode dst = edge.getDst();
        src.decreaseOutDeg();
        dst.decreaseInDeg();
        NodeLinkedList<GraphEdge> srcNeighbors = src.getNeighborsList();
        srcNeighbors.delete(edge.getListNode());
    }

    public RootedTree scc(){return new RootedTree();};

    public RootedTree bfs(GraphNode source)
    {
        RootedTree bfsTree = new RootedTree();
        NodeLinkedList<RootedTree> treeQueue = new NodeLinkedList<>();
        NodeLinkedList<GraphNode> nodeQueue = new NodeLinkedList<>();
        bfsInit(source);

        bfsTree.setRoot(source.getKey());


        treeQueue.insert(bfsTree);
        nodeQueue.insert(source);

        RootedTree currentSubTree;
        GraphNode currentGraphNode;
        Node<GraphEdge> currentListEdge;
        GraphNode currentGraphNeighbor;


        while(nodeQueue.getSize()!=0)
        {
            currentSubTree = treeQueue.dequeue();
            currentGraphNode = nodeQueue.dequeue();
            currentListEdge=null;

            if(currentGraphNode.getNeighborsList().getSize()!=0)
            {
                currentListEdge = currentGraphNode.getNeighborsList().getLast().getListNode();
            }


            while(currentListEdge!=null)
            {

                currentGraphNeighbor = currentListEdge.getData().getDst();
                if(currentGraphNeighbor.getColor() == Color.WHITE)
                {
                    currentGraphNeighbor.setColor(Color.GRAY);
                   RootedTree newTree = new RootedTree(currentSubTree,currentGraphNeighbor.getKey());
                   newTree.setNodeList(currentSubTree.addChild(newTree));
                   treeQueue.insert(newTree);
                   nodeQueue.insert(currentGraphNeighbor);
                }
                currentListEdge = currentListEdge.getPrev();
            }
            currentGraphNode.setColor(Color.BLACK);
        }
        return bfsTree;
    };

    public void bfsInit(GraphNode source)
    {
        NodeLinkedList<GraphEdge>  neighborsList = source.getNeighborsList();
        Node<GraphNode> currentNode = neighborsList.getFirst().getDst().getListNode();
        while(currentNode!=null)
        {
            currentNode.getData().setColor(Color.WHITE);
            currentNode = currentNode.getNext();
        }

        source.setColor(Color.GRAY);

    }




}
