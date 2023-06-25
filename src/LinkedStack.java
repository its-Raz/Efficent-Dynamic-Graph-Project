public class LinkedStack<T> extends NodeLinkedList<T>{

    public Node<T> push(T element)
    {
        Node<T> Node = new Node<T>(element);
        if(super.getSize() == 0)
        {
            super.setFirst(Node);
            super.setLast(super.getFirstNode());
        }
        else
        {
            Node.setNext(super.getFirstNode());
            super.getFirstNode().setPrev(Node);
            super.setFirst(Node);

        }
        super.incSize();
        return Node;
    }
    public T pop()
    {
        return super.dequeue();
    }

}
