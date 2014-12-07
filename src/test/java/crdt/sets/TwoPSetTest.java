package crdt.sets;

import static com.google.common.collect.Sets.newHashSet;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class TwoPSetTest {

	@Test
	public void testElementExists_whenAdded() throws Exception {
		TwoPSet<String> set = new TwoPSet<String>();

		set.add("a");

		assertEquals( newHashSet("a"), set.get() );
	}

	@Test
	public void testElementDoesNotExists_whenRemoved() throws Exception {
		TwoPSet<String> set = new TwoPSet<String>();

		set.add("a");
		set.add("b");
		set.remove("b");

		assertEquals( newHashSet("a"), set.get() );
	}

	@Test(expected = IllegalArgumentException.class)
	public void testExceptionThrown_whenAddedAfterRemove() throws Exception {
		TwoPSet<String> set = new TwoPSet<String>();

		set.add("b");
		set.remove("b");
		set.add("b");
	}

	@Test
	public void testAllAddedPresent_whenMerged() throws Exception {
		TwoPSet<String> set1 = new TwoPSet<String>();
		TwoPSet<String> set2 = new TwoPSet<String>();

		set1.add("a");
		set2.add("b");

		set1.merge(set2);

		assertEquals(newHashSet("a", "b"), set1.get());
	}

	@Test
	public void testItemNotPresent_whenRemovedInOtherSetAndMerged() throws Exception {
		TwoPSet<String> set1 = new TwoPSet<String>();
		TwoPSet<String> set2 = new TwoPSet<String>();

		set1.add("a");
		set1.add("b");

		set2.add("b");
		set2.remove("b");
		set2.add("c");

		set1.merge(set2);

		assertEquals(newHashSet("a", "c"), set1.get());
	}
}
