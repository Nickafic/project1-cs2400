public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private int numberOfEntries = 0;
    private static final int default_capacity = 50;
    private static final int MAX_CAPACITY = 10000;
    private boolean integrityOK = false;

    public ResizeableArrayBag(){
        integrityOK = true;
        @SuppressWarnings("unchecked")
        T[] tempbag = (T[]) new Object[default_capacity];
        bag = tempbag;
    }

    public ResizeableArrayBag(int capacity){
        if(capacity < MAX_CAPACITY){
            integrityOK = true;
            @SuppressWarnings("unchecked")
            T[] tempbag = (T[]) new Object[capacity];
            bag = tempbag;
        }
        else 
            throw new IllegalStateException("attempted to create a bag with capacity exceeding maximum");
    }
    
     private void checkIntegrity()
    {
        if(!integrityOK)
        {
            throw new SecurityException("Array Bag object is corrupt");
        }
    }

    public boolean isEmpty(){
        checkIntegrity();
        return numberOfEntries ==0;
    }

    public int getFrequencyOf(T entry){
        checkIntegrity();
        int frequency = 0;
        for(int i =0; i<numberOfEntries; i++){
            if(entry == bag[i])
                frequency++;
        }
        return frequency;
    }

    public boolean add(T entry){
        checkIntegrity();
        if(isFull()){
            @SuppressWarnings("unchecked")

            T[] tempbag = (T[]) new Object[bag.length*2];
            for(int i =0; i<numberOfEntries;i++){
                tempbag[i] = bag[i];
            }
            bag = tempbag;
            bag[numberOfEntries++] = entry;
            if(bag.length > MAX_CAPACITY){
                integrityOK = false;
            }
            return true;
        }
        else {
            bag[numberOfEntries++] = entry;
            return true;
        }
    }
    
    public int getCurrentSize()
    {
        checkIntegrity();
        return numberOfEntries;
    }

    public boolean remove(T entry)
    {
        checkIntegrity();
        for(int i =0; i<numberOfEntries; i++)
        {
            if(entry == bag[i]){
                bag[i] = bag[numberOfEntries-1];
                bag[numberOfEntries-1] = null;
                numberOfEntries--;
                return true;
            }
        }
        return false;
    }

    public T remove(){
        checkIntegrity();
        T value = bag[numberOfEntries-1];
        bag[numberOfEntries-1] = null;
        numberOfEntries--;
        return value;
    }
    
     public boolean contains(T item)
    {
        checkIntegrity();
        for(int i =0; i<numberOfEntries; i++)
        {
            if(bag[i] == item)
                return true;
        }
        return false;
    }
    
     public T[] toArray()
    {
        checkIntegrity();
        @SuppressWarnings("unchecked")
        T[] copybag = (T[]) new Object[numberOfEntries];
        for(int i =0; i<numberOfEntries; i++)
        {
            copybag[i] = bag[i];
        }
        return copybag;
    }

    public void print()
    {
        for(int i=0;i<numberOfEntries;i++)
        {
            System.out.print(bag[i]);
        }
    }
    
    public int lowestFrequency(int frequency1, int frequency2) 
    {
        if (frequency1 > frequency2)
        {
            return frequency2;
        }
        else
        {
            return frequency1;
        }
    }
    
     public void clear()
    {
        while(!isEmpty())
            remove();
    }
    
    public boolean isFull()
    {
        checkIntegrity();
        return numberOfEntries == bag.length;
    }
    public BagInterface<T> union(BagInterface<T> bag2)
    {
        BagInterface<T> newBag = new ResizeableArrayBag<T>();
      
        T[] bagTwoArray = bag2.toArray();

        for(int i =0; i<bag2.getCurrentSize()+ this.getCurrentSize(); i++)
        {
            if(i<this.numberOfEntries)
                newBag.add(this.bag[i]);
            else    
                newBag.add(bagTwoArray[i-this.numberOfEntries]);           
        }
        return newBag;
    }
    
    public BagInterface<T> intersection(BagInterface<T> bagTwo)
    {
        
        BagInterface<T> intersect = new ResizeableArrayBag<T>();
        for(int count = 0; count < this.getCurrentSize(); count++)
        {
            int lowestFreq = lowestFrequency(this.getFrequencyOf(this.bag[count]), bagTwo.getFrequencyOf(this.bag[count]));
            int element = intersect.getFrequencyOf(this.bag[count]);
            for(int i = element; i< lowestFreq; i++)
            {
                intersect.add(this.bag[count]);
            }
        }
        return intersect;
    }
    
    public BagInterface<T> difference(BagInterface<T> array2)
    {
        //checkIntegrity();
        ResizeableArrayBag<T> bagCopy = new ResizeableArrayBag<T>();
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

}
