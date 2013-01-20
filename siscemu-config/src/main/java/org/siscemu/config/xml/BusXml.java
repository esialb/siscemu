package org.siscemu.config.xml;

import org.siscemu.core.Device;
import org.siscemu.core.dvc.Bus;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class BusXml implements Converter {

	private XStream xstream;
	
	public BusXml(XStream xstream) {
		this.xstream = xstream;
	}
	
	public boolean canConvert(Class type) {
		return Bus.class.isAssignableFrom(type);
	}

	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		// TODO Auto-generated method stub
		
	}

	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Bus bus = new Bus();
		while(reader.hasMoreChildren()) {
			reader.moveDown();
			String tag = reader.getNodeName();
			if("aliases".equals(tag)) {
				context.convertAnother(bus, AliasesXml.class);
			} else if("devices".equals(tag)) {
				while(reader.hasMoreChildren()) {
					reader.moveDown();
					tag = reader.getNodeName();
					
					Class<?> clazz = (Class<?>) xstream.getMapper().realClass(tag);
					bus.addDevice((Device) context.convertAnother(bus, clazz));
					
					reader.moveUp();
				}
			} else {
				throw new ConversionException("Unexpected tag <" + tag + ">");
			}
			reader.moveUp();
		}
		return bus;
	}
	
}
