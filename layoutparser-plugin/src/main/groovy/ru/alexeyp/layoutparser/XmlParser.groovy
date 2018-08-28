package ru.alexeyp.layoutparser

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class XmlParser extends DefaultHandler {

  public final List<XmlElement> elements = new ArrayList<>()

  @Override
  void startDocument() {
    println "Start parse XML..."
  }

  @Override
  void startElement(String uri, String localName, String qName, Attributes attributes) {
    println "startElement-------"
    println "Q NAME = " + qName
    for (int i = 0; i < attributes.getLength(); i++) {
      println"Attributes = " + attributes.getQName(i)
    }
    String id = attributes.getValue("android:id")
    if (id != null) {
      id = id.substring(id.lastIndexOf("/") + 1)
    }

    elements.add(new XmlElement(id, "Name", qName, "HZ"))

    println attributes.getValue("android:id")
  }

  @Override
  void endElement(String uri, String localName, String qName) {
  }

  @Override
  void endDocument() {
  }

}
