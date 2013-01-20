package org.siscemu.core.dvc;

import java.util.Arrays;

import org.siscemu.util.Range;

public class ArrayRAM extends AbstractDevice {

	private int[] buffer;
	
	public ArrayRAM(int size) {
		this.buffer = new int[size];
	}
	
	@Override
	public int read(int address) {
		return buffer[rangeFor(address).offsetOf(address)];
	}
	
	@Override
	public int[] read(int address, int size) {
		int offset = rangeFor(address).offsetOf(address);
		return Arrays.copyOfRange(buffer, offset, offset + size);
	}

	@Override
	public void write(int address, int value) {
		buffer[rangeFor(address).offsetOf(address)] = value;
	}

	@Override
	public void write(int address, int[] values) {
		System.arraycopy(values, 0, buffer, rangeFor(address).offsetOf(address), values.length);
	}
}
