package crdt.sets;

import static com.google.common.collect.Sets.newHashSet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TwoPSetTest {

    TwoPSet<String> set = new TwoPSet<String>();

	@Test
	public void testElementExists_whenAdded() throws Exception {

		set.add("a");

		assertEquals( newHashSet("a"), set.get() );
	}

	@Test
	public void testElementDoesNotExists_whenRemoved() throws Exception {

		set.add("a");
		set.add("b");
		set.remove("b");

		assertEquals( newHashSet("a"), set.get() );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionThrown_whenAddedAfterRemove() throws Exception {

		set.add("b");
		set.remove("b");
		set.add("b");
	}

	@Test
	public void testAllAddedPresent_whenMerged() throws Exception {
		TwoPSet<String> set2 = new TwoPSet<String>();

		set.add("a");
		set2.add("b");

		set.merge(set2);

		assertEquals(newHashSet("a", "b"), set.get());
		assertEquals(newHashSet("a", "b"), set.copy().get());

	}

	@Test
	public void testItemNotPresent_whenRemovedInOtherSetAndMerged() throws Exception {

		set.add("b");
		TwoPSet<String> replica2 = set.copy();

		set.add("a");
		replica2.remove("b");
		replica2.add("c");

		set.merge(replica2);

		assertEquals(newHashSet("a", "c"), set.get());
	}
}
