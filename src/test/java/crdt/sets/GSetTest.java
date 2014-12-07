package crdt.sets;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class GSetTest {

	@Test
	public void testSetGrows_whenItemsAdded() throws Exception {
		GSet<String> set = new GSet<String>();

		set.add("a");
		set.add("b");
		set.add("a");

		assertEquals( newHashSet("a", "b"), set.get() );
	}

	@Test
	public void testAllItemsPresent_whenGsetsMerged() throws Exception {
		GSet<String> set1 = new GSet<String>();
		GSet<String> set2 = new GSet<String>();

		set1.add("a");
		set2.add("b");
		set2.add("c");

		set1.merge(set2);

		assertEquals( newHashSet("a", "b", "c"), set1.get() );
	}

	@Test
	public void testSetGrows_whenCollectionAdded() throws Exception {
		GSet<String> set = new GSet<String>();

		set.addAll(asList("a", "b", "a"));

		assertEquals( newHashSet("a", "b"), set.get() );
	}
}
