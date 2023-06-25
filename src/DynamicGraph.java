

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
        NodeLinkedList<GraphNode> nodes = this.Nodes;
        if (nodes.getSize() != 0) {
            Node<GraphNode> currentNode = nodes.getFirst().getListNode();

            while (currentNode != null) {
                currentNode.getData().setColor(Color.WHITE);
                currentNode = currentNode.getNext();
            }

            source.setColor(Color.GRAY);

        }


    }
    public RootedTree scc()
    {
        LinkedStack<GraphNode> fStack = new LinkedStack<>();
        DFS(this.Nodes,fStack);
        RootedTree tree = sccDFS(fStack);
        return tree;
    }
    public void DFS(NodeLinkedList<GraphNode> nodes,LinkedStack<GraphNode> stack )
    {
        RootedTree virtualTree = new RootedTree();
        int[] time = {0};
        Node<GraphNode> currentNode;

        if(nodes.getSize()!=0)
        {
            currentNode = nodes.getLast().getListNode();

            while(currentNode!=null)
            {
                currentNode.getData().setColor(Color.WHITE);
                currentNode = currentNode.getPrev();
            }

            currentNode = nodes.getLast().getListNode();


            while(currentNode!=null)
            {
                if( currentNode.getData().getColor() == Color.WHITE )
              {
                    DFS_Visit(currentNode.getData(),stack,time,virtualTree,Boolean.FALSE);
                }
                currentNode = currentNode.getPrev();

            }
        }
        }

        public RootedTree sccDFS(LinkedStack<GraphNode> stack)
        {
            int[] time = {0};
            Node<GraphNode> currentNode;
            RootedTree tree = new RootedTree(null,0);
            if(stack.getSize()!=0)
            {
                currentNode = stack.getLastNode();

                while(currentNode!=null)
                {
                    currentNode.getData().setColor(Color.WHITE);
                    currentNode = currentNode.getPrev();
                }
                currentNode = stack.pop().getListNode();
                while(currentNode!=null)
                {
                    if( currentNode.getData().getColor() == Color.WHITE )
                    {
                    RootedTree subTree = new RootedTree(tree,currentNode.getData().getKey(),currentNode.getData());

                    Node<RootedTree> nodeList = tree.addChild(subTree);
                    subTree.setNodeList(nodeList);
                        DFS_Visit(currentNode.getData(),stack,time,subTree,Boolean.TRUE);
                    }
                    if(stack.getSize()!=0){
                    currentNode = stack.pop().getListNode();}
                    else{currentNode=null;}

                }
        }

        return tree;
        }


    public void DFS_Visit(GraphNode vertex,LinkedStack<GraphNode> stack,int[] time,RootedTree tree,Boolean isTranspose)
    {

        time[0]+=1;
        vertex.setDisc(time[0]);
        vertex.setColor(Color.GRAY);
        Node<GraphNode> currentNode = null;
        Node<GraphEdge> currentEdge = null;
            if((!isTranspose) && vertex.getNeighborsList().getSize()!=0)
            {
            currentEdge = vertex.getNeighborsList().getLastNode();
            currentNode = currentEdge.getData().getDst().getListNode();
            }
            if((isTranspose) && vertex.getNeighborsListTranspose().getSize()!=0)
            {
                currentEdge = vertex.getNeighborsListTranspose().getLastNode();
                currentNode = currentEdge.getData().getDst().getListNode();
            }

            while(currentNode!=null)
            {
                if(currentNode.getData().getColor() == Color.WHITE)
                {
                    if(isTranspose  && currentNode.getData().getRet() > tree.getRoot().getRet())
                    {
                            RootedTree parent = tree.getParent();
                            while(parent.getRoot().getRet() < currentNode.getData().getRet())
                            {
                                tree = parent;
                                parent = parent.getParent();
                            }
                            try{
                            parent.deleteChild(tree);}
                            catch(Exception e){
                                System.out.println("here");
                            }
                            RootedTree subTree = new RootedTree(parent,currentNode.getData().getKey(),currentNode.getData());
                            Node<RootedTree> nodeList = subTree.addChild(tree);
                            tree.setParent(subTree);
                            tree.setNodeList(nodeList);
                            nodeList = parent.addChild(subTree);
                            subTree.setNodeList(nodeList);
                            DFS_Visit(currentNode.getData(),stack,time,tree,isTranspose);

                    }
                    else
                    {
                    RootedTree subTree = new RootedTree(tree,currentNode.getData().getKey(),currentNode.getData());
                    Node<RootedTree> nodeList = tree.addChild(subTree);
                    subTree.setNodeList(nodeList);
                    DFS_Visit(currentNode.getData(),stack,time,subTree,isTranspose);
                    }
                }
                if(currentEdge.getPrev()!=null){
                currentEdge = currentEdge.getPrev();
                currentNode = currentEdge.getData().getDst().getListNode();
                }
                else{currentNode=null;}
            }

        vertex.setColor(Color.BLACK);
        time[0]+=1;
        if(!isTranspose){
        vertex.setRet(time[0]);}
        stack.push(vertex);
    }

}
