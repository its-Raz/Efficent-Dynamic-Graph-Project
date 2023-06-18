public abstract class Node {

    private Node prev;
    private Node next;




    public Node(Node prev,Node next)
    {
        if(prev!=null){ this.prev = prev;}
        if(next!=null){this.next=next;}
    }


    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public Node getPrev() {
        return prev;
    }

    public void setPrev(Node prev) {
        this.prev = prev;
    }


}
