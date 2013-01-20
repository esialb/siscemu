package org.siscemu.config.xml;

import java.util.Map;
import java.util.TreeMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class AliasesXml implements Converter {
	
	private XStream xstream;
	
	public AliasesXml(XStream xstream) {
		this.xstream = xstream;
	}

	@Override
	public boolean canConvert(Class type) {
		return AliasesXml.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		while(reader.hasMoreChildren()) {
			reader.moveDown();
			if("alias".equals(reader.getNodeName())) {
				String name = reader.getAttribute("name");
				String value = reader.getAttribute("value");
				xstream.alias(name, xstream.getMapper().realClass(value));
			} else {
				throw new ConversionException("Expected <alias...> but found <" + reader.getNodeName() + ">");
			}
			reader.moveUp();
		}
		return null;
	}
}
