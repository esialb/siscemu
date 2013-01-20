package org.siscemu.config.xml;

import java.util.ArrayList;
import java.util.List;

import org.siscemu.core.Device;
import org.siscemu.util.Range;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class DeviceXml implements Converter {

	private XStream xstream;
	
	public DeviceXml(XStream xstream) {
		this.xstream = xstream;
	}
	
	@Override
	public boolean canConvert(Class type) {
		return Device.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Range[] addresses = ((Device) source).getAddresses();
		for(Range r : addresses) {
			writer.startNode("address");
			context.convertAnother(r);
			writer.endNode();
		}
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Device device;
		try {
			device = ((Class<? extends Device>) context.getRequiredType()).newInstance();
		} catch(Exception ex) {
			throw new ConversionException("Unable to instantiate device " + context.getRequiredType(), ex);
		}
		List<Range> addresses = new ArrayList<Range>();
		while(reader.hasMoreChildren()) {
			reader.moveDown();
			String tag = reader.getNodeName();
			if("address".equals(tag)) {
				addresses.add((Range) context.convertAnother(device, Range.class));
			} else
				throw new ConversionException("Unexpected tag <" + tag + ">");
			reader.moveUp();
		}
		device.setAddresses(addresses.toArray(new Range[addresses.size()]));
		return device;
	}

}
