public class NodeLinkedList<T extends Node> {
    private T first;
    private T last;

    private int size;

    public NodeLinkedList() {
        this.size = 0;
    }

    /**
     * adds a node to the end of the queue
     * @param Node- the node to add
     */
    public void insert(T Node)
    {

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
    }
    public T dequeue()
    {
        T dequeued = this.first;
        this.delete(this.first);
        return dequeued;
    }
    /**
     * removes the head and returns the data of the head
     * @return the data of the head
     */
    public void delete(T Node)
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
            else if(Node.equals(this.first))
            {
                T next = (T)first.getNext();
                this.first = next;
                next.setPrev(null);

            }
            else if(Node.equals(this.last))
            {
                T prev = (T)last.getPrev();
                this.last = prev;
                prev.setNext(null);
            }
             else {
                T prev = (T) Node.getPrev();
                T next = (T) Node.getNext();
                prev.setNext(next);
                next.setPrev(prev);
            }
        size--;
        }

    }


    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getLast() {
        return last;
    }

    public void setLast(T last) {
        this.last = last;
    }

    public int getSize() {
        return size;
    }
}
