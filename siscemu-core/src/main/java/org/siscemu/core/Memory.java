package org.siscemu.core;

import org.siscemu.util.Range;

public interface Memory {
	public int read(int address);
	public int[] read(int address, int size);
	public int[] read(Range addressRange);
	public void write(int address, int value);
	public void write(int address, int[] values);
}
