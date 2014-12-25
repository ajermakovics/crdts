package crdt.counters;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GStateTest {

    enum State {
        Joined,
        Left
    }

    // track state of a cluster. nodes can join and then leave, but cannot join back
    GState<String, State> cluster = new GState<String, State>(State.class);

    @Test
    public void testAdvancesState_whenIncremented() throws Exception {

        cluster.increment("host1");
        assertEquals(State.Joined, cluster.get().get("host1"));

        cluster.increment("host2");
        cluster.increment("host2");

        assertEquals(State.Left, cluster.get().get("host2"));
    }

    @Test(expected = IllegalStateException.class)
    public void testThrowsException_whenIncrementedPastStates() throws Exception {
        cluster.increment("host2");
        cluster.increment("host2");
        cluster.increment("host2");
    }

    @Test
    public void testHighestStatePicked_whenMerged() throws Exception {

        cluster.increment("host1");

        GState<String, State> copy = cluster.copy();
        copy.increment("host1");

        cluster.merge(copy);

        assertEquals(State.Left, cluster.get().get("host1"));
    }
}
