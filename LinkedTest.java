public class LinkedTest{
    public static void main(String[] args){
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> bag2 = new LinkedBag<>();

        bag1.add("y");
        bag1.add("h");
        bag2.add("h");
        bag2.add("j");
        BagInterface<String> bag3 = bag1.union(bag2);

    
        
    }
}
