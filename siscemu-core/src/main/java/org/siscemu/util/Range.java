package org.siscemu.util;

/**
 * A range of longs, including the {@link #from()} value and excluding the {@link #to()} value.
 * @author robin
 *
 */
public class Range implements Comparable<Range> {
	private int from;
	private int to;
	
	public Range() {
	}
	
	public Range(int from, int to) {
		this.from(from).to(to);
	}
	
	public int from() {
		return from;
	}
	public Range from(int from) {
		this.from = from;
		return this;
	}
	
	public int to() {
		return to;
	}
	public Range to(int to) {
		this.to = to;
		return this;
	}
	
	public Range forward(int size) {
		return to(from() + size);
	}
	
	public Range backward(int size) {
		return from(to() - size);
	}
	
	public int size() {
		return to() - from();
	}

	public boolean contains(int v) {
		return from() <= v && v < to();
	}
	
	public int offsetOf(int v) {
		return v - from();
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
		return from() + Integer.reverseBytes(to());
	}
	
	@Override
	public int compareTo(Range o) {
		if(from() >= o.from() && to() <= o.to() || o.from() >= from() && o.to() <= to())
			return 0;
		return (from() < o.from()) ? -1 : 1;
	}
}
