package crdt.counters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GCounterTest {

    @Test
    public void testCounterIncreases_whenIncremented() {
        GCounter counter = new GCounter();

        counter.increment("a");

        assertEquals(1, counter.get());
    }

    @Test
    public void testCounterIncreases_whenIncrementedTwice() {
        GCounter counter = newCounter("a", "a");

        assertEquals(2, counter.get());
    }

    @Test
    public void testCounterIncreases_whenIncrementedByDifferentMembers() {
        GCounter counter = newCounter("a", "b");

        assertEquals(2, counter.get());
    }

    private static GCounter newCounter(String key1, String key2) {
        GCounter counter = new GCounter();

        counter.increment(key1);
        counter.increment(key2);

        return counter;
    }

    @Test
    public void testCountersSummed_whenTwoCountersMerged() {
        GCounter counter = newCounter("a", "b");
        GCounter counter2 = newCounter("b", "c");

        counter.merge( counter2 );

        assertEquals(3, counter.get());
    }
}
