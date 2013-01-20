package org.siscemu.core.dvc;

import java.util.Arrays;

import org.siscemu.core.Device;
import org.siscemu.util.Range;

public abstract class AbstractDevice extends AbstractMemory implements Device {

	protected Range[] addresses;
	
	protected Range rangeFor(int address) {
		for(Range r : addresses) {
			if(r.contains(address))
				return r;
		}
		return null;
	}
	
	@Override
	public Range[] getAddresses() {
		return Arrays.copyOf(addresses, addresses.length);
	}

	@Override
	public void setAddresses(Range[] addresses) {
		this.addresses = addresses;
	}

}
