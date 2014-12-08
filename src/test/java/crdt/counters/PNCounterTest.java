package crdt.counters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PNCounterTest {

    @Test
    public void testIncreases_whenIncremented() throws Exception {
        PNCounter<String> counter = new PNCounter<String>();

        counter.increment("a");

        assertEquals( 1, counter.get() );
        assertEquals( 1, counter.copy().get() );
    }

    @Test
    public void testDecreases_whenDecremented() throws Exception {
        PNCounter<String> counter = new PNCounter<String>();

        counter.increment("a");
        counter.decrement("b");

        assertEquals( 0, counter.get() );
    }

    @Test
    public void testCountCorrect_whenMerged() throws Exception {
        PNCounter<String> replica1 = new PNCounter<String>();
        replica1.increment("hostname1");
        PNCounter<String> replica2 = replica1.copy();

        replica1.increment("hostname2");
        replica2.decrement("hostname2");
        replica1.merge( replica2 );

        assertEquals( 1, replica1.get() );
    }
}
