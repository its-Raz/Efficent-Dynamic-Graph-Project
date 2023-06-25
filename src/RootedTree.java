import java.io.DataOutputStream;
import java.io.IOException;


public class RootedTree {
    RootedTree parent;
    int rootKey;
    GraphNode root;
    NodeLinkedList<RootedTree> children;
    Node<RootedTree> nodeList;


    public RootedTree() {

        children = new NodeLinkedList<>();
    }

    public RootedTree(RootedTree parent, int key) {
        this();
        this.parent = parent;
        this.rootKey = key;
    }
    public RootedTree(RootedTree parent, int key,GraphNode root) {
        this();
        this.parent = parent;
        this.rootKey = key;
        this.root=root;
    }

    public GraphNode getRoot() {
        return root;
    }

    public RootedTree getParent() {
        return parent;
    }

    public int getRootKey() {
        return rootKey;
    }

    public Node<RootedTree> addChild(RootedTree child) {
        return children.insert(child);
    }
    public void deleteChild(RootedTree child){this.children.delete(child.getNodeList());}

    public void setRoot(int rootKey) {
        this.rootKey = rootKey;
    }

    public void setParent(RootedTree parent) {
        this.parent = parent;
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        NodeLinkedList<RootedTree> queue = new NodeLinkedList<>();
        queue.insert(this);

        while (queue.getSize() != 0) {
            int size = queue.getSize();

            // Process nodes at the current layer
            for (int i = 0; i < size; i++) {
                RootedTree current = queue.dequeue();
                out.writeBytes(current.rootKey+"");
                if(i!=(size-1))
                {
                    out.writeBytes(",");
                }


                // Enqueue the children for the next layer
                if(current.getChildren().getSize()!=0){
                Node<RootedTree> child = current.children.getFirst().getNodeList();
                while (child != null) {
                    queue.insert(child.getData());
                    child = child.getNext();
                }
                }
            }
            out.writeBytes("\n");;
        }



    }

    public void preorderPrint(DataOutputStream out) throws IOException{
        prePrint(out,this);

    }
    public void prePrint(DataOutputStream out,RootedTree tree) throws IOException
    {
        out.writeBytes(tree.rootKey + "");

        NodeLinkedList<RootedTree> childNodes = tree.children;
        if (childNodes.getSize()!=0) {
            Node<RootedTree> currentNode = childNodes.getFirst().getNodeList();
            while (currentNode != null) {
                RootedTree currentTree = currentNode.getData();
                out.writeBytes(",");
                prePrint(out,currentTree);
                currentNode = currentNode.getNext();
            }
        }
    }



    public void setNodeList (Node < RootedTree > nodeList) {
        this.nodeList = nodeList;
    }
    public NodeLinkedList<RootedTree> getChildren () {
        return this.children;
    }

    public Node<RootedTree> getNodeList() {
        return nodeList;
    }
}


