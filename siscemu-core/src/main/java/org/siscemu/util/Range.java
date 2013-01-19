package org.siscemu.util;

/**
 * A range of longs, including the {@link #from()} value and excluding the {@link #to()} value.
 * @author robin
 *
 */
public class Range implements Comparable<Range> {
	private long from;
	private long to;
	
	public Range() {
	}
	
	public Range(long from, long to) {
		this.from(from).to(to);
	}
	
	public long from() {
		return from;
	}
	public Range from(long from) {
		this.from = from;
		return this;
	}
	
	public long to() {
		return to;
	}
	public Range to(long to) {
		this.to = to;
		return this;
	}
	
	public Range forward(long size) {
		return to(from() + size);
	}
	
	public Range backward(long size) {
		return from(to() - size);
	}
	
	public long size() {
		return to() - from();
	}

	public boolean contains(long v) {
		return from() <= v && v < to();
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj == this)
			return true;
		if(obj instanceof Range) {
			return from() == ((Range) obj).from() && to() == ((Range) obj).to();
		}
		return false;
	}
	
	@Override
	public int hashCode() {
		return ((int) from()) + Integer.reverseBytes((int) to());
	}
	
	@Override
	public int compareTo(Range o) {
		if(from() >= o.from() && to() <= o.to() || o.from() >= from() && o.to() <= to())
			return 0;
		return (from() < o.from()) ? -1 : 1;
	}
}
