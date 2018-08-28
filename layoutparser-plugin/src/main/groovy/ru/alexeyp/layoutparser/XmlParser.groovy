package ru.alexeyp.layoutparser

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class XmlParser extends DefaultHandler {

  public final List<XmlElement> elements = new ArrayList<>()

  @Override
  void startElement(String uri, String localName, String qName, Attributes attributes) {
    String id = attributes.getValue("android:id")
    if (id != null) {
      id = id.substring(id.lastIndexOf("/") + 1)
    }
    elements.add(new XmlElement(id, qName))
  }
}
