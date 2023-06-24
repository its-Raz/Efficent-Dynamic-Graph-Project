

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
        return;}
    }

    public GraphEdge insertEdge(GraphNode src, GraphNode dst)
    {
        src.increaseOutDeg();
        dst.increaseInDeg();
        NodeLinkedList<GraphEdge> srcNeighbors = src.getNeighborsList();
        GraphEdge newEdge = new GraphEdge(src,dst);
        Node<GraphEdge> listNode = srcNeighbors.insert(newEdge);
        newEdge.setListNode(listNode);

        NodeLinkedList<GraphEdge> dstNeighbors = dst.getNeighborsListTranspose();
        GraphEdge newTranEdge = new GraphEdge(dst,src);
        Node<GraphEdge> listTranNode = dstNeighbors.insert(newTranEdge);
        newTranEdge.setListNode(listTranNode);

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

    public void bfsInit(GraphNode source) {
        NodeLinkedList<GraphEdge> neighborsList = source.getNeighborsList();
        if (neighborsList.getSize() != 0) {
            Node<GraphNode> currentNode = neighborsList.getFirst().getDst().getListNode();

            while (currentNode != null) {
                currentNode.getData().setColor(Color.WHITE);
                currentNode = currentNode.getNext();
            }

            source.setColor(Color.GRAY);

        }


    }
    public RootedTree scc()
    {
        RootedTree tree = new RootedTree(null,0);
        NodeLinkedList<GraphNode> stack = new NodeLinkedList<>();
        DFS(this.Nodes,tree,stack,Boolean.FALSE);
        RootedTree tree_2 = new RootedTree(null,0);
        NodeLinkedList<GraphNode> stack_2 = new NodeLinkedList<>();
        DFS(stack,tree_2,stack_2,Boolean.TRUE);



        return tree_2;
    }
    public void DFS(NodeLinkedList<GraphNode> nodes,RootedTree tree,NodeLinkedList<GraphNode> stack,Boolean isSCC )
    {


        Integer time = 0;
        Node<GraphNode> currentNode;
        if(nodes.getSize()!=0)
        {
            currentNode = nodes.getLast().getListNode();
            while(currentNode!=null)
            {
                currentNode.getData().setColor(Color.WHITE);
                currentNode = currentNode.getPrev();
            }
            if(!isSCC){
            currentNode = nodes.getLast().getListNode();}
            else{
                currentNode = nodes.dequeue().getListNode();
            }
            while(currentNode!=null)
            {
                if(currentNode.getData().getColor() == Color.WHITE)
                {
                    RootedTree subTree = new RootedTree(tree,currentNode.getData().getKey());
                    Node<RootedTree> nodeList = tree.addChild(subTree);
                    subTree.setNodeList(nodeList);
                    DFS_Visit(currentNode.getData(),stack,time,subTree);
                }
                if(!isSCC)
                {
                    currentNode = currentNode.getPrev();
                }
                else {
                    if(nodes.getSize()!=0){
                    currentNode = nodes.dequeue().getListNode();}
                    else{currentNode=null;}
                }
            }
        }

    }
    public void DFS_Visit(GraphNode vertex,NodeLinkedList<GraphNode> stack,Integer time,RootedTree tree)
    {

        time+=1;
        vertex.setDisc(time);
        vertex.setColor(Color.GRAY);
        if(vertex.getNeighborsList().getSize()!=0)
        {
            Node<GraphNode> currentNode = vertex.getNeighborsList().getLast().getDst().getListNode();
            while(currentNode!=null)
            {
                if(currentNode.getData().getColor() == Color.WHITE)
                {
                    RootedTree subTree = new RootedTree(tree,currentNode.getData().getKey());
                    Node<RootedTree> nodeList = tree.addChild(subTree);
                    subTree.setNodeList(nodeList);
                    DFS_Visit(currentNode.getData(),stack,time,subTree);
                }
                currentNode = currentNode.getPrev();
            }
        }
        vertex.setColor(Color.BLACK);
        time+=1;
        vertex.setRet(time);
        stack.insert(vertex);
    }





}
