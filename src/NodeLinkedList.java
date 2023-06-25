public class NodeLinkedList<T> {
    private Node<T> first;
    private Node<T> last;

    private int size;

    public NodeLinkedList() {
        this.first = null;
        this.last = null;
        this.size = 0;
    }


    public Node<T> insert(T element)
    {
        Node<T> Node = new Node<T>(element);
        if(this.size == 0)
        {
            this.first = Node;
            this.last = first;
        }
        else
        {
            Node.setPrev(this.last);
            this.last.setNext(Node);
            this.last=Node;
        }
        size++;
        return Node;
    }
    public T dequeue()
    {
        if(size!=0)
        {
        T dequeued = this.first.getData();
        this.delete(this.first);
        return dequeued;
        }
        System.out.println("size is 0");
        return null;

    }
    /**
     * removes the head and returns the data of the head
     * @return the data of the head
     */
    public void delete(Node<T> Node)
    {
        if(this.size == 0){
            System.out.println("is empty");;
        }
        else
        {
            if(this.size == 1){
                first = null;
                last = null;
            }
            else if(Node.getData().equals(this.first.getData()))
            {
                Node<T> next = Node.getNext();
                this.first = next;
                next.setPrev(null);
            }
            else if(Node.getData().equals(this.last.getData()))
            {
                Node<T> prev = Node.getPrev();
                this.last = prev;
                prev.setNext(null);
            }
             else {
                Node<T> prev =  Node.getPrev();
                Node<T> next =  Node.getNext();
                prev.setNext(next);
                next.setPrev(prev);
            }
        size--;
        }

    }

    public void setFirst(Node<T> first) {
        this.first = first;
    }

    public void setLast(Node<T> last) {
        this.last = last;
    }

    public T getFirst() {
        return first.getData();
    }
    public Node<T> getFirstNode()
    {
        return this.first;
    }
    public Node<T> getLastNode()
    {
        return this.last;
    }



    public T getLast() {
        return last.getData();
    }


    public int getSize() {
        return size;
    }
    public void incSize()
    {
        this.size++;
    }
    public void decSize()
    {
        this.size--;
    }
}
