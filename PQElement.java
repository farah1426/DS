package COMM;

public class PQElement<T> {
    public T data;
    public float priority;
    
    public PQElement(T data, float priority) {
        this.data = data;
        this.priority = priority;
    }
}