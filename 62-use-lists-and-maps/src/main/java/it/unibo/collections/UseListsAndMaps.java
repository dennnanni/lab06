package it.unibo.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Example class using {@link List} and {@link Map}.
 *
 */
public final class UseListsAndMaps {
    private static final int START = 1000;
    private static final int END = 2000;
    private static final int ELEMS = 100_000;

    private UseListsAndMaps() {
    }

    /**
     * @param s
     *            unused
     */
    public static void main(final String... s) {
        /*
         * 1) Create a new ArrayList<Integer>, and populate it with the numbers
         * from 1000 (included) to 2000 (excluded).
         */

        final List<Integer> arrayList = new ArrayList<>();

        for (int i = START; i < END; i++) {
            arrayList.add(i);
        }

        /*
         * 2) Create a new LinkedList<Integer> and, in a single line of code
         * without using any looping construct (for, while), populate it with
         * the same contents of the list of point 1.
         */

        final List<Integer> linkedList = new LinkedList<>(arrayList);
        
        /*
         * 3) Using "set" and "get" and "size" methods, swap the first and last
         * element of the first list. You can not use any "magic number".
         * (Suggestion: use a temporary variable)
         */

        final int temp = arrayList.get(arrayList.size() - 1);
        arrayList.set(arrayList.size() - 1, arrayList.get(0));
        arrayList.set(0, temp);

        /*
         * 4) Using a single for-each, print the contents of the arraylist.
         */
        for (Integer val : arrayList) {
            System.out.println(val);
        }
        /*
         * 5) Measure the performance of inserting new elements in the head of
         * the collection: measure the time required to add 100.000 elements as
         * first element of the collection for both ArrayList and LinkedList,
         * using the previous lists. In order to measure times, use as example
         * TestPerformance.java.
         */
        
        System.out.println("ArrayList:");
        insertionBenchmark(arrayList);

        System.out.println("LinkedList:");
        insertionBenchmark(linkedList);

        /*
         * 6) Measure the performance of reading 1000 times an element whose
         * position is in the middle of the collection for both ArrayList and
         * LinkedList, using the collections of point 5. In order to measure
         * times, use as example PerfTest.java.
         */

        System.out.println("ArrayList:");
        readingBenchmark(arrayList);

        System.out.println("LinkedList:");
        readingBenchmark(linkedList); 


        /*
         * 7) Build a new Map that associates to each continent's name its
         * population:
         *
         * Africa -> 1,110,635,000
         *
         * Americas -> 972,005,000
         *
         * Antarctica -> 0
         *
         * Asia -> 4,298,723,000
         *
         * Europe -> 742,452,000
         *
         * Oceania -> 38,304,000
         */

        Map<String, Long> continents = new HashMap<>();
        continents.put("Africa", 1_110_635_000L);
        continents.put("Americas", 972_005_000L);
        continents.put("Antarctica", 0L);
        continents.put("Asia", 4_298_723_000L);
        continents.put("Europe", 742_452_000L);
        continents.put("Oceania", 38_304_000L);
        /*
         * 8) Compute the population of the world
         */
        long worldPopulation = 0;
        final Collection<Long> values = continents.values();
        for (Long value : values) {
            worldPopulation += value;
        }

        System.out.println("World population is aproximately " + worldPopulation);
    }

    private static void insertionBenchmark(List<Integer> ls) {
        long time = System.nanoTime();

        for (int i = 1; i <= ELEMS; i++) {
            ls.set(0, i);
        }

        time = System.nanoTime() - time;
        var millis = TimeUnit.NANOSECONDS.toMillis(time);

        System.out.println("Inserting " + ELEMS + " elements took " + time + "ns (" + millis + "ms)" );
    }

    private static void readingBenchmark(List<Integer> ls) {
        long time = System.nanoTime();
        int position = ls.size() / 2;

        for (int i = 1; i <= 1000; i++) {
            ls.get(position);
        }
        
        time = System.nanoTime() - time;
        long millis = TimeUnit.NANOSECONDS.toMillis(time);
    
        System.out.println("Reading 1000 elements took " + time + "ns (" + millis + "ms)" );
    }
}
