public class BagTest{

    public static void main(String[] args){
        ResizeableArrayBag<String> bag1 = new ResizeableArrayBag<>(9);
        ResizeableArrayBag<String> bag2 = new ResizeableArrayBag<>(9);
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");

        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");
        
        ResizeableArrayBag<String> bag3 = bag1.union(bag2);
        bag3.print();

        

        
    }
}