package crdt.counters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PNCounterTest {

    @Test
    public void testIncreases_whenIncremented() throws Exception {
        PNCounter counter = new PNCounter();

        counter.increment("a");

        assertEquals( 1, counter.get() );
    }

    @Test
    public void testDecreases_whenDecremented() throws Exception {
        PNCounter counter = new PNCounter();

        counter.increment("a");
        counter.decrement("b");

        assertEquals( 0, counter.get() );
    }

    @Test
    public void testCountCorrect_whenMerged() throws Exception {
        PNCounter counter = new PNCounter();
        counter.increment("a");
        counter.increment("b");

        PNCounter counter2 = new PNCounter();
        counter2.increment("a");
        counter2.decrement("c");

        counter.merge( counter2 );

        assertEquals( 1, counter.get() );
    }
}
