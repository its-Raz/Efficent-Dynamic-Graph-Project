import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.SQLOutput;

public class RootedTree extends Node {
    RootedTree parent;
    GraphNode root;
    int rootKey;
    NodeLinkedList<RootedTree> children;

    public RootedTree()
    {
        super(null,null);
        children = new NodeLinkedList<>();
    }
    public RootedTree(RootedTree parent,GraphNode root,int key)
    {
        this();
        this.parent=parent;
        this.root=root;
        this.rootKey=key;
    }

    public GraphNode getRoot() {
        return root;
    }
    public void addChild(RootedTree child)
    {
        this.children.insert(child);
    }

    public void setRoot(GraphNode root) {
        this.root = root;
        this.rootKey = root.getKey();
    }

    public void printByLayer(DataOutputStream out) throws IOException {
        pLayer(out,this);
        out.close();
    }
    public void pLayer(DataOutputStream out,RootedTree tree) throws IOException
    {
        if(tree.getRoot().getKey()==5)
        {
            System.out.println("here");
        }
        out.writeBytes(tree.getRoot().getKey() + " ");
        if(tree.children.getSize()!=0)
        {
            RootedTree currentChild = tree.children.getFirst();
            while(currentChild!=null)
            {
                pLayer(out,currentChild);
                currentChild = (RootedTree) currentChild.getNext();
            }
        }

    }

    public void preorderPrint(DataOutputStream out){}


}
