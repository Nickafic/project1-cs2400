public class LinkedTest{
    public static void main(String[] args){
        LinkedBag<String> bag1 = new LinkedBag<>();
        LinkedBag<String> bag2 = new LinkedBag<>();

        bag1.add("y");
        bag1.add("h");
        bag2.add("h");
        LinkedBag<String> bag3 = bag1.difference(bag2);
        bag3.print();
    
        
    }
}
