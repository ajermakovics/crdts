package crdt.sets;

import static com.google.common.collect.Sets.newHashSet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class ORSetTest {

    ORSet<String> set = new ORSet<String>();

    @Test
    public void testContainsItem_whenAdded() throws Exception {
        set.add("a");
        set.add("a");
        set.add("b");

        assertEquals( newHashSet("a", "b"), set.get() );
    }

    @Test
    public void testDoesntContainItem_whenRemoved() throws Exception {
        set.add("a");
        set.add("b");
        set.remove("a");
        set.add("a");
        set.remove("a");

        assertEquals( newHashSet("b"), set.get() );
    }

    @Test
    public void testContainsAllItems_whenMerged() throws Exception {

        ORSet<String> set2 = new ORSet<String>();

        set.add("a");
        set2.add("b");

        set.merge(set2);

        assertEquals( newHashSet("a", "b"), set.get() );
    }

    @Test
    public void testDoesntContainsItems_whenRemovedAndMerged() throws Exception {

        set.add("a");
        set.add("b");

        ORSet<String> set2 = set.copy();

        set2.remove("b");
        set.merge(set2);

        assertEquals( newHashSet("a", "b"), set.get() );
    }

    @Test
    public void testAddWins_whenRemovedAndMerged() throws Exception {

        ORSet<String> set2 = new ORSet<String>();

        set.add("a");
        set.add("b");

        set2.add("a");
        set2.remove("a");

        set.merge(set2);

        assertEquals( newHashSet("a", "b"), set.get() );
    }
}
