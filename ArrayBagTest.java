public class ArrayBagTest 
{
    public static void main(String[] args) 
    {
        //Creating an empty resizeable array bag, bag1.
        BagInterface<String> bag1 = new ResizeableArrayBag<String>();
        // adding to bag1.
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");

        //Creating another empty resizeable array bag, bag2.
        BagInterface<String> bag2 = new ResizeableArrayBag<String>();
        //adding to bag2.
        bag2.add("b");
        bag2.add("b");
        bag2.add("d");
        bag2.add("e");
        
        //to find the union of two bags
        BagInterface<String> everything = bag1.union(bag2);
        System.out.println("Union of the two bags is: ");
        while(!everything.isEmpty()){
            System.out.print(everything.remove());
        }

        //to find the intersection of two bags
        BagInterface<String> commonItems = bag1.intersection(bag2);
        System.out.println("\nIntersection of the two bags is: ");
        while(!commonItems.isEmpty()){
            System.out.print(commonItems.remove());
        }

        //to find the difference of two bags.
        
        BagInterface<String> leftOver1 = bag1.difference(bag2);
        System.out.println("\nThe left over of bag1 is: ");
        while(!leftOver1.isEmpty()){
            System.out.print(leftOver1.remove());
        }

        BagInterface<String> leftOver2 = bag2.difference(bag1);
        System.out.println("\nThe left over of bag2 is: ");
        while(!leftOver2.isEmpty()){
            System.out.print(leftOver2.remove());
        }

        
    }
}
