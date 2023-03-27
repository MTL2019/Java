package DataStructureAndAlgorithms;

import java.util.*;

/**
 * Set: storing and processing nonduplicate elements
 *   HashSet- duplicate for a HashSet if e1.equals(e2) is true and e1.hashCode() == e2.hashCode()
 *          - if two elements are equal, their hashCode must be same.
 *   LinkedHashSet: extends HashSet with a linked-list implementation; set with order inserting
 *   TreeSet: implements the SortedSet interface
 *          * 1. With no-arg constructor, use compareTo method  to compare the elements in the set, assuming the class of
 *          * the elements implements the Comparable interface
 *          * 2. With constructor TreeSet(Comparator comparator) , use compare method to order the elements
 *
 * Map: save key/value pairs
 *   HashMap,efficient for locating / inserting / deleting a value/entry
 *   LinkedHashMap: order with insertion order / access order
 *   TreeMap: efficient for traversing the keys in a sorted order
 *
 * List: accessing elements through the index.
 *
 * ***Conclusion: for contains query and remove operations, sets are more efficient than lists.
 *
 * Singleton & Unmodifiable Collections & Maps
 * '
 *
 *
 */
public class Chapter21_SetsAndMaps {
    static final int N = 50000;
    public static void main(String[] args) {

        //testHash();

        //testLinkedHashSet();

        //testTreeSet();

        //testListPerformance();

    }

    private static void testListPerformance() {

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        Collections.shuffle(list);

        Collection<Integer> set1 = new HashSet<>(list);
        System.out.println("Hash set: Member test time is "  + getTestTime(set1) + " milliseconds") ;
        System.out.println("Hash set: Member remove time is " + getRemoveTime(set1) + " milliseconds");

        Collection<Integer> set2 = new LinkedHashSet<>(list);
        System.out.println("Linked hash set: Member test time is "  + getTestTime(set2) + " milliseconds") ;
        System.out.println("Linked hash set: Member remove time is " + getRemoveTime(set2) + " milliseconds");

        Collection<Integer> set3 = new TreeSet<>(list);
        System.out.println("Tree set: Member test time is "  + getTestTime(set3) + " milliseconds") ;
        System.out.println("Tree set: Member remove time is " + getRemoveTime(set3) + " milliseconds");

        Collection<Integer> list1 = new ArrayList<>(list);
        System.out.println("Array List: Member test time is "  + getTestTime(list1) + " milliseconds") ;
        System.out.println("Array List: Member remove time is " + getRemoveTime(list1) + " milliseconds");

        Collection<Integer> list2 = new ArrayList<>(list);
        System.out.println("Linked List: Member test time is "  + getTestTime(list2) + " milliseconds") ;
        System.out.println("Linked List: Member remove time is " + getRemoveTime(list2) + " milliseconds");
    }

    public static long getTestTime(Collection<Integer> c){
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            c.contains((int)(Math.random() * 2 * N));
        }

        return System.currentTimeMillis() - startTime;
    }

    public static long getRemoveTime(Collection<Integer> c){
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < N; i++) {
            c.remove(i);
        }

        return System.currentTimeMillis() - startTime;
    }
    private static void testTreeSet() {
        Set<String> set = new HashSet<>();

        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("Beijing");
        set.add("New York");

        TreeSet<String> treeSet = new TreeSet<>(set);
        System.out.println(treeSet);

        // Use the methods in SortedSet interface
        System.out.println(treeSet.first());
        System.out.println(treeSet.last());
        System.out.println(treeSet.headSet("New York"));
        System.out.println(treeSet.tailSet("New York"));

        System.out.println("===========================");

        // Use the methods in NavigableSet interface
        System.out.println(treeSet.lower("P"));
        System.out.println(treeSet.higher("P"));
        System.out.println(treeSet.floor("P"));
        System.out.println(treeSet.ceiling("P"));
        System.out.println(treeSet.pollFirst());
        System.out.println(treeSet.pollLast());
        System.out.println(treeSet);
    }

    private static void testLinkedHashSet() {
        Set<String> set = new LinkedHashSet<>();

        set.add("London");
        set.add("Paris");
        set.add("New York");
        set.add("San Francisco");
        set.add("Beijing");
        set.add("New York");

        System.out.println(set);

        for (String element: set) {
            System.out.print(element.toLowerCase() + "  ");
        }
    }

    private static void testHash() {
        String s = new String("123");
        String s1 = "123";
        System.out.println(s.equals(s1));
        System.out.println(s==s1);
        System.out.println(s.hashCode() == s1.hashCode());
    }

}
