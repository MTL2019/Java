package DataStructureAndAlgorithms;


import java.util.*;

/**
 * Java Collections Framework supports two types of containers
 *  1. collection:  storing a collection of elements
 *      Sets:  nonduplicate elements
 *      Lists: an ordered collection of elements
 *          - ArrayList : random access through an index without inserting or removing elements at the beginning of the list
 *          - LinkedList :  the insertion or deletion of elements at the beginning of the list
 *      Stacks: Last in, first out
 *      Queues: first in, first out  ( Queue interface)
 *      PriorityQueues: store objects that are processed in the order of their priorities
 *  2. map: storing key/value pairs
 *
 *  3. Comparator Interface : overwrite compare method
 *
 *  4. Vector: same as ArrayList, except that it contains synchronized methods
 *     Stack: as an extension of Vector
 *
 *  5. Priority Queues: element with the highest priority is removed first
 *     queue: LinkedList is ideal for queue operations because it is efficient for inserting and removing elements from both ends of a list.
 *     deque: double-ended queue, pronounced "deck"; with additional methods for inserting and removing elements from both ends of the queue
 */
public class Chapter20_ListsStacksQueues {

    public static void main(String[] args) {

        //collections methods: add/addAll/retainAll/removeAll/remove/clone
        //testCollection();

        //Iterators
        //testIterator();

        //Lists:  ArrayList or LinkedList : allow duplicate elements
        //testArrayList();

        //testArrayAndLinkedList();

        //testComparator();

        //testQueue();

    }

    private static void testQueue() {
        Queue<String> queue = new LinkedList<>();
        queue.offer("Oklahoma");
        queue.offer("Indiana");
        queue.offer("Georgia");
        queue.offer("Texas");

        while(queue.size() > 0)
            System.out.println(queue.remove() + " ");
    }

    private static void testComparator() {
        List<String> cities = Arrays.asList("Atlanta", "savannah", "NewYork", "Dallas");
        // cities.sort((s1,s2)->s1.compareToIgnoreCase(s2));

        //method reference, can replace Lambda expression in this kind of simple situation
        cities.sort(String::compareToIgnoreCase);

        for (String s :cities) {
            System.out.println(s + "  ");
        }
    }

    private static void testArrayAndLinkedList() {
        List<Integer> arrayList = new ArrayList<>();
        arrayList.add(1);
        arrayList.add(2);
        arrayList.add(3);
        arrayList.add(1);
        arrayList.add(4);
        arrayList.add(0);
        arrayList.add(0,10);
        arrayList.add(3,30);
        System.out.println(arrayList);

        LinkedList<Object> linkedList = new LinkedList<>(arrayList);
        linkedList.add(1,"red");
        linkedList.removeLast();
        linkedList.addFirst("green");
        Iterator<Object> iterator = linkedList.listIterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next() + "  ");
        }

        System.out.println("\n=============");
        ListIterator<Object> listIterator = linkedList.listIterator(linkedList.size());
        while (listIterator.hasPrevious()){
            System.out.print(listIterator.previous() + "  ");
        }
    }

    private static void testArrayList() {
        ArrayList<String> collection = new ArrayList<>();

        collection.add("New York");
        collection.add("Atlanta");
        collection.add("Dallas");
        collection.add("Madison");
        collection.add("Atlanta");

        System.out.println(collection.get(0));
        System.out.println(collection.indexOf("Atlanta"));
        System.out.println(collection.lastIndexOf("Atlanta"));

        System.out.println("\n=========Iterator=========");
        ListIterator<String> iterator = collection.listIterator(2);//from index 2

        System.out.println(iterator.hasPrevious());
        System.out.println(iterator.nextIndex());
        System.out.println(iterator.previous());

        while (iterator.hasNext()){
            System.out.print(iterator.next().toUpperCase() + "  ");
        }

        System.out.println("\n=========Iterator=========");

        System.out.println(collection.subList(2,5));
        collection.set(4,"Orlando");
        System.out.println(collection);
    }

    private static void testIterator() {
        Collection<String> collection1 = new ArrayList<>();

        collection1.add("New York");
        collection1.add("Atlanta");
        collection1.add("Dallas");
        collection1.add("Madison");

        Iterator<String> iterator = collection1.iterator();// get iterator
        while (iterator.hasNext()){
            System.out.print(iterator.next().toUpperCase() + "  ");
        }
        System.out.println("\n==================");
        //or simplify
        collection1.forEach(e -> System.out.print(e.toUpperCase() + "  "));
        System.out.println("\n==================");
    }

    //collections methods: add/addAll/retainAll/removeAll/remove/clone
    private static void testCollection() {
        ArrayList<String> collection1 = new ArrayList<>();

        collection1.add("New York");
        collection1.add("Atlanta");
        collection1.add("Dallas");
        collection1.add("Madison");

        System.out.println(collection1);
        System.out.println(collection1.contains("Dallas"));
        collection1.remove("Dallas");
        System.out.println("\n"+ collection1.size());

        ArrayList<String> collection2 = new ArrayList<>();

        collection2.add("Seattle");
        collection2.add("Portland");
        collection2.add("Los Angeles");
        collection2.add("Atlanta");
        System.out.println(collection2);

        System.out.println("=============");
        ArrayList<String> clone = (ArrayList<String>) (collection1.clone());
        clone.addAll(collection2);
        System.out.print("\nCities in collection1 or collection2: ");
        System.out.println(clone);

        System.out.println("=============");
        clone = (ArrayList<String>)(collection1.clone());
        clone.retainAll(collection2);
        System.out.print("\nCities in collection1 and collection2: ");
        System.out.println(clone);

        System.out.println("=============");
        clone = (ArrayList<String>)(collection1.clone());
        clone.removeAll(collection2);
        System.out.print("\nCities in collection1 ,but not in collection2: ");
        System.out.println(clone);
    }
}

