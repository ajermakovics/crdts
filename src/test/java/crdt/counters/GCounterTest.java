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
        assertEquals(2, counter.copy().get());
    }

    @Test
    public void testCounterIncreases_whenIncrementedByDifferentMembers() {
        GCounter<String> counter = newCounter("a", "b");

        assertEquals(2, counter.get());
    }

    private static GCounter<String> newCounter(String... keys) {
        GCounter<String> counter = new GCounter<String>();

        for(String key: keys)
        	counter.increment(key);

        return counter;
    }

    @Test
    public void testCountersSummed_whenTwoCountersMerged() {
        GCounter<String> replica1 = newCounter("b");
        GCounter<String> replica2 = replica1.copy();

        replica1.increment("a");
        replica2.increment("c");

        replica1.merge( replica2 );

        assertEquals(3, replica1.get());
    }
}
