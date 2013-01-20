package org.siscemu.core.dvc;

import java.util.SortedMap;
import java.util.TreeMap;

import org.siscemu.core.Device;
import org.siscemu.core.Memory;
import org.siscemu.util.Range;

public class Bus implements Memory {
	private TreeMap<Integer, Memory> devices = new TreeMap<Integer, Memory>();
	
	public void addDevice(Device device) {
		for(Range r : device.getAddresses()) {
			addDevice(r.from(), device);
		}
	}
	
	public void addDevice(int address, Memory device) {
		devices.put(address, device);
	}
	
	public TreeMap<Integer, Memory> getDevices() {
		return devices;
	}
	
	public void setDevices(TreeMap<Integer, Memory> devices) {
		this.devices = devices;
	}
	
	protected Memory deviceForAddress(int address) {
		return devices.floorEntry(address).getValue();
	}
	
	@Override
	public int read(int address) {
		return deviceForAddress(address).read(address);
	}

	@Override
	public int[] read(int address, int size) {
		return deviceForAddress(address).read(address, size);
	}

	@Override
	public int[] read(Range addressRange) {
		return deviceForAddress(addressRange.from()).read(addressRange);
	}

	@Override
	public void write(int address, int value) {
		deviceForAddress(address).write(address, value);
	}

	@Override
	public void write(int address, int[] values) {
		deviceForAddress(address).write(address, values);
	}

}
