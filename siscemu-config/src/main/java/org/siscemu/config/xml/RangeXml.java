package org.siscemu.config.xml;

import org.siscemu.util.Range;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class RangeXml implements Converter {

	@Override
	public boolean canConvert(Class type) {
		return Range.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		Range r = (Range) source;
		writer.addAttribute("from", "" + r.from());
		writer.addAttribute("to", "" + r.to());
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		Range r = new Range();
		if(reader.getAttribute("from") != null)
			r.from(Integer.parseInt(reader.getAttribute("from")));
		if(reader.getAttribute("to") != null)
			r.to(Integer.parseInt(reader.getAttribute("to")));
		return r;
	}

}
