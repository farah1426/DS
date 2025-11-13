package COMM;

public class LinkedList<T> {
    private Node<T> head;
    private Node<T> current;
    private int size;
    
    public LinkedList() {
        head = null;
        current = null;
        size = 0;
    }
    
    public boolean empty() {
        return head == null;
    }
    
    public void insert(T data) {
        Node<T> newNode = new Node<>(data);
        if (empty()) {
            head = newNode;
            current = head;
        } else {
            newNode.next = head;
            head = newNode;
            current = head;
        }
        size++;
    }
    
    public void findFirst() {
        current = head;
    }
    
    public void findNext() {
        if (current != null) {
            current = current.next;
        }
    }
    
    public T retrieve() {
        return current != null ? current.data : null;
    }
    
    public void remove() {
        if (current == null) return;
        
        if (current == head) {
            head = head.next;
            current = head;
        } else {
            Node<T> temp = head;
            while (temp != null && temp.next != current) {
                temp = temp.next;
            }
            if (temp != null) {
                temp.next = current.next;
                current = temp.next;
            }
        }
        size--;
    }
    
    public boolean last() {
        return current == null;
    }
    
    public int size() {
        return size;
    }
    
    public void print() {
        Node<T> temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }
}