/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.quijoteluiclisri.util.xml;

/**
 *
 * @author jorgequiguango
 */

import com.quijoteluiclisri.modelo.ComprobanteXml;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class ComprobanteXmlConverter
  implements Converter
{
  public boolean canConvert(Class clazz)
  {
    return clazz.equals(ComprobanteXml.class);
  }
  
  public void marshal(Object o, HierarchicalStreamWriter writer, MarshallingContext mc)
  {
    ComprobanteXml i = (ComprobanteXml)o;
    
    writer.setValue(i.getFileXML());
  }
  
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext uc)
  {
    ComprobanteXml item = new ComprobanteXml();
    item.setTipo(reader.getAttribute("tipo"));
    item.setVersion(reader.getAttribute("version"));
    item.setFileXML(reader.getValue());
    
    return item;
  }
}
