public class Node <T> {
    private T data;
    private Node<T> prev;
    private Node<T> next;



    public Node(T data)
    {
        this.data=data;
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

    public T getData() {
        return data;
    }


}
