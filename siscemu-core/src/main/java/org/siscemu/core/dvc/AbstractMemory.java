package org.siscemu.core.dvc;

import org.siscemu.core.Memory;
import org.siscemu.util.Range;

public abstract class AbstractMemory implements Memory {

	@Override
	public int[] read(int address, int size) {
		int[] ret = new int[size];
		for(int i = 0; i < size; i++)
			ret[i] = read(address + i);
		return ret;
	}

	@Override
	public int[] read(Range addressRange) {
		return read(addressRange.from(), addressRange.size());
	}

	@Override
	public void write(int address, int[] values) {
		for(int i = 0; i < values.length; i++)
			write(address + i, values[i]);
	}

}
