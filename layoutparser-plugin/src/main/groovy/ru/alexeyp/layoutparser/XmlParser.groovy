package ru.alexeyp.layoutparser

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

class XmlParser extends DefaultHandler {

  private final List<XmlElement> elements = new ArrayList<>()

  @Override
  void startDocument() {
    println "Start parse XML..."
  }

  @Override
  void startElement(String uri, String localName, String qName, Attributes attributes) {
    println "startElement-------"
    println "URI = " + uri
    println "LOCAL NAME = " + localName
    println "Q NAME = " + qName
    for (int i = 0; i < attributes.getLength(); i++) {
      println"Attributes = " + attributes.getQName(i)
    }

    println attributes.getValue("android:id")
  }

  @Override
  void endElement(String uri, String localName, String qName) {
    println "endElement-------"
    println "URI = " + uri
    println "LOCAL NAME = " + localName
    println "Q NAME = " + qName
  }

  @Override
  void endDocument() {
    println "Stop parse XML..."
  }

}
