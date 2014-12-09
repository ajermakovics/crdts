CRDTs 
================

Simple Conflict-free Replicated Data Types (CRDTs) for distributed systems. CRDTs on different replicas can diverge from one another but at the end they can be safely merged providing an eventually consistent value.

The following CRDTs are implemented:
 - G-Counter - Grow only counter
 - PN-Counter - Increment/decrement counter 
 - G-Set - Grow only set
 - 2P-Set - Set that allows adds and removes but an item can be removed only once

Examples
===========
PN-Counter:
```java
        PNCounter<String> replica1 = new PNCounter<String>();
        replica1.increment("hostname1");
        PNCounter<String> replica2 = replica1.copy();

        replica1.increment("hostname2");
        replica2.decrement("hostname2");
        replica1.merge( replica2 ); 
        replica1.get(); // counter is 1
```
2-P Set:
```java
		TwoPSet<String> replica1 = new TwoPSet<String>();

		replica1.add("a");
		replica1.add("b");
        
        TwoPSet<String> replica2 = replica1.copy();
		replica2.remove("b");
		replica2.add("c");

		replica1.merge(replica2); 
		replica1.get(); // set is {"a", "c"}
```

Serialization
===========

All CRDT classes implement Java Serializable but you should be able to use any other library.

Requirements
============

Java 1.6

More info
=========
 - [A comprehensive study of
Convergent and Commutative Replicated Data Types](http://hal.upmc.fr/file/index/docid/555588/filename/techreport.pdf)

License
=======

MIT
