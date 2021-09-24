public class LinkedTest{
    public static void main(String[] args){
        LinkedBag<Integer> bag1 = new LinkedBag<>();
        LinkedBag<Integer> bag2 = new LinkedBag<>();

        bag1.add(1);
        bag1.add(2);
        bag1.remove(2);
        bag1.print();
    
        
    }
}
