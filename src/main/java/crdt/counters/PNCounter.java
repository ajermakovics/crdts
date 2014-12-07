package crdt.counters;

import java.io.Serializable;

public class PNCounter implements Serializable {

	private static final long serialVersionUID = 1L;

	GCounter inc = new GCounter();
    GCounter dec = new GCounter();

    public void increment(String key) {
        inc.increment(key);
    }

    public int get() {
        return inc.get() - dec.get();
    }

    public void decrement(String key) {
        dec.increment(key);
    }

	public void merge(PNCounter other) {
		inc.merge( other.inc );
		dec.merge( other.dec );
	}

}
