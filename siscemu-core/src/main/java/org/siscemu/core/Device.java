package org.siscemu.core;

import org.siscemu.util.Range;

public interface Device extends Memory {
	public Range[] getAddresses();
	public void setAddresses(Range[] addresses);
}
