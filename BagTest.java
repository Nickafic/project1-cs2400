public class BagTest{

    public static void main(String[] args){
        ResizeableArrayBag<Integer> bag1 = new ResizeableArrayBag<>(9);
        ResizeableArrayBag<Integer> bag2 = new ResizeableArrayBag<>(9);
        for(int i=0; i<6; i++){
            bag1.add(2);
        }
        for(int i=0; i<2; i++){
            bag2.add(2);
        }
        ResizeableArrayBag<Integer> bag3 = bag1.Difference(bag2);
        bag3.print();

        

        
    }
}