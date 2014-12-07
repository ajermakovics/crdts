package crdt.counters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GCounterTest {

    @Test
    public void testCounterIncreases_whenIncremented() {
        GCounter<String> counter = new GCounter<String>();

        counter.increment("a");

        assertEquals(1, counter.get());
    }

    @Test
    public void testCounterIncreases_whenIncrementedTwice() {
        GCounter<String> counter = newCounter("a", "a");

        assertEquals(2, counter.get());
    }

    @Test
    public void testCounterIncreases_whenIncrementedByDifferentMembers() {
        GCounter<String> counter = newCounter("a", "b");

        assertEquals(2, counter.get());
    }

    private static GCounter<String> newCounter(String key1, String key2) {
        GCounter<String> counter = new GCounter<String>();

        counter.increment(key1);
        counter.increment(key2);

        return counter;
    }

    @Test
    public void testCountersSummed_whenTwoCountersMerged() {
        GCounter<String> counter = newCounter("a", "b");
        GCounter<String> counter2 = newCounter("b", "c");

        counter.merge( counter2 );

        assertEquals(3, counter.get());
    }
}
