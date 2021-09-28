public class LinkedTest{
    public static void main(String[] args){
        BagInterface<String> bag1 = new LinkedBag<>();
        BagInterface<String> bag2 = new LinkedBag<>();

        bag1.add("d");
        bag1.add("b");
        bag1.add("b");
        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");
        BagInterface<String> bag3 = bag2.intersection(bag1);
        while(!bag3.isEmpty()){
            System.out.print(bag3.remove());
        }

    
        
    }
}
