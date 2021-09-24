public class LinkedBag<T> implements BagInterface<T>  {
    private Node firstNode;
    private int numberOfEntries;

    public LinkedBag(){
        firstNode = null;
        numberOfEntries = 0;
    }
    public boolean add(T newEntry){
        Node newNode = new Node(newEntry);
        newNode.next = firstNode;
        firstNode = newNode;
        numberOfEntries++;
        return true;
    }

    public int getCurrentSize(){
        return numberOfEntries;
    }

    public boolean isEmpty(){
        return numberOfEntries == 0;
    }

    public void clear(){
        while(!isEmpty()){
            remove();
        }
    }

    public T remove(){
        if(firstNode != null){
            T data = firstNode.getData();
            firstNode = firstNode.getNextNode();
            numberOfEntries--;
            return data;
        }
        return null;
    }
    public boolean remove(T entry){
        Node tracker = firstNode;
        Node previous = firstNode;
        while(tracker != null && tracker.getData() != entry){
            previous = tracker;
            tracker = tracker.getNextNode();
        }
        if(tracker != null && tracker.getData() == entry){
            previous.setNextNode(tracker.getNextNode());
            numberOfEntries--;
            return true;
        }
        else    
            return false;
    }
    public LinkedBag<T> union(LinkedBag<T> bag2){
        Node nextNode1 = firstNode;
        Node nextNode2 = bag2.firstNode;
        LinkedBag<T> newBag = new LinkedBag<>();
        for(int i =0; i<this.numberOfEntries; i++){
            newBag.add(nextNode1.getData());
            nextNode1 = nextNode1.getNextNode();
        }
        for(int i =0; i<bag2.numberOfEntries; i++){
            newBag.add(nextNode2.getData());
            nextNode2 = nextNode2.getNextNode();
        }
        return newBag;
    }
    public LinkedBag<T> difference(LinkedBag<T> array2)
    {
        LinkedBag<T> bagCopy = new LinkedBag<>();
        T[] array1 = this.toArray();

        for(T x : array1)
        {
            bagCopy.add(x);
        }

        T[] array3 = array2.toArray();
        for(T x: array3){
            if(bagCopy.contains(x)){
                bagCopy.remove(x);
            }
        }

        return bagCopy;
    }

    public boolean contains(T item){
        Node currentNode = firstNode;
        for(int i =0; i<numberOfEntries && currentNode != null; i++){
            if(currentNode.getData() == item){
                return true;
            }
            currentNode = currentNode.getNextNode();
        }
        return true;
    }
    
    public T[] toArray(){
        @SuppressWarnings("unchecked")
        T[] result = (T[])new Object[numberOfEntries];
        Node currentNode = firstNode;
        for(int i=0; i<numberOfEntries && currentNode != null; i++){
            result[i] = currentNode.getData();
            currentNode = currentNode.getNextNode();
        }
        return result;
    }

    public int getFrequencyOf(T entry){
        Node currentNode = firstNode;
        int frequency = 0;
        for(int i =0; i<numberOfEntries && currentNode != null; i++){
            if(currentNode.getData() == entry){
                frequency++;
            }
            currentNode = currentNode.getNextNode();
        }
        return frequency;
    }

    public void print(){
        Node current = firstNode;
        for(int i =0; i<numberOfEntries; i++){
            System.out.printf("%s", current.getData());
            current = current.getNextNode();
        }
    }

    private class Node{
        private T data;
        private Node next;
        private Node(T dataPortion){
            this(dataPortion, null);
        }
        private Node(T dataPortion, Node nextNode){
            data = dataPortion;
            next = nextNode;
        }
        private T getData(){
            return data;
        }
        private void setData(T newData){
            data = newData;
        }
        private Node getNextNode(){
            return next;
        }
        private void setNextNode(Node nextNode){
            next = nextNode;
        }
    }
}