package COMM;

public class LinkedPQ<T> {
    private LinkedList<PQElement<T>> list;
    
    public LinkedPQ() {
        list = new LinkedList<>();
    }
    
    public void enqueue(T data, float priority) {
        PQElement<T> element = new PQElement<>(data, priority);
        list.insert(element);
    }
    
    public PQElement<T> serve() {
        if (list.empty()) return null;
        
        list.findFirst();
        PQElement<T> highest = list.retrieve();
        int highestPosition = 0;
        int currentPosition = 0;
        
        list.findFirst();
        while (!list.last()) {
            if (list.retrieve().priority > highest.priority) {
                highest = list.retrieve();
                highestPosition = currentPosition;
            }
            list.findNext();
            currentPosition++;
        }
        
        if (!list.last() && list.retrieve().priority > highest.priority) {
            highest = list.retrieve();
            highestPosition = currentPosition;
        }
        
        list.findFirst();
        for (int i = 0; i < highestPosition; i++) {
            list.findNext();
        }
        PQElement<T> served = list.retrieve();
        list.remove();
        
        return served;
    }
    
    public int length() {
        return list.size();
    }
    
    public boolean empty() {
        return list.empty();
    }

}

