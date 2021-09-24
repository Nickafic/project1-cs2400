public class ResizeableArrayBag<T>
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
    public int getCurrentSize(){
        checkIntegrity();
        return numberOfEntries;
    }

    public ResizeableArrayBag<T> Union(ResizeableArrayBag<T> bag2){
        checkIntegrity();
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>(bag2.numberOfEntries + this.numberOfEntries);
        for(int i =0; i<bag2.numberOfEntries+this.numberOfEntries; i++){
            if(i<this.numberOfEntries)
                newBag.add(this.bag[i]);
            else    
                newBag.add(bag2.bag[i-this.numberOfEntries]);           
        }
        newBag.toArray();
        return newBag;
    }

    public boolean remove(T entry){
        checkIntegrity();
        for(int i =0; i<numberOfEntries; i++){
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
        return removeEntry(numberOfEntries-1);
    }
    
    public T removeEntry(int entry){
        checkIntegrity();
        T contents = bag[entry];
        if(entry<numberOfEntries && entry >=0){
            bag[entry] = null;
            bag[entry] = bag[numberOfEntries-1];
            numberOfEntries--;
        }
        return contents;
    }

    public ResizeableArrayBag<T> intersection(ResizeableArrayBag<T> bagTwo)
    {
        ResizeableArrayBag<T> intersect = new ResizeableArrayBag<>(this.numberOfEntries + bagTwo.numberOfEntries);
        boolean statForInt[] = new boolean[bagTwo.numberOfEntries];

        for (int i = 0; i < this.numberOfEntries; i++)
        {
            for (int j = 0; j < bagTwo.numberOfEntries; j++)
            {
                if ((this.bag[i] == bagTwo.bag[j]) && (statForInt[j]==false))
                {
                     intersect.add(this.bag[i]);
                     statForInt[j] = true;
                }
            }
        }
        return intersect;
    }
    public ResizeableArrayBag<T> Difference(ResizeableArrayBag<T> array2)
    {
        checkIntegrity();
        ResizeableArrayBag<T> bagCopy = new ResizeableArrayBag<>(this.numberOfEntries+array2.numberOfEntries);
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
        checkIntegrity();
        for(int i =0; i<numberOfEntries; i++){
            if(bag[i] == item)
                return true;
        }
        return false;
    }

    public T[] toArray(){
        checkIntegrity();
        @SuppressWarnings("unchecked")
        T[] copybag = (T[]) new Object[numberOfEntries];
        for(int i =0; i<numberOfEntries; i++){
            copybag[i] = bag[i];
        }
        return copybag;
    }

    public void print(){
        for(int i=0;i<numberOfEntries;i++){
            System.out.print(bag[i]);
        }
    }
    
    public void clear(){
        while(!isEmpty())
            remove();
    }

    private void checkIntegrity(){
        if(!integrityOK){
            throw new SecurityException("Array Bag object is corrupt");
        }
    }

    public boolean isFull(){
        checkIntegrity();
        return numberOfEntries == bag.length;
    }
}
