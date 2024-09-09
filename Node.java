public class Node<T>
{
    private T data;
    private Node<T> nextNode;

    public Node(T newData){
        data = newData;
        nextNode = null;
    }

    public Node(T newData, Node<T> newNextNode){
        data = newData;
        nextNode = newNextNode;
    }

    public T getData(){
        return data;
    }

    public Node<T> getNextNode(){
        return nextNode;
    }

    public void setData(T newData){
        data = newData;
    }

    public void setNextNode(Node<T> newNextNode){
        nextNode = newNextNode;
    }
}
