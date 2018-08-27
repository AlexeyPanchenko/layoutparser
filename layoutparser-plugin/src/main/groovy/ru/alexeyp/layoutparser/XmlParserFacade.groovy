package ru.alexeyp.layoutparser

import org.xml.sax.helpers.DefaultHandler

import javax.xml.parsers.SAXParser
import javax.xml.parsers.SAXParserFactory

class XmlParserFacade {

  private final DefaultHandler handler
  private final SAXParser saxParser

  XmlParserFacade(DefaultHandler handler) {
    this.handler = handler
    SAXParserFactory factory = SAXParserFactory.newInstance()
    saxParser = factory.newSAXParser()
  }

  void parse(File file) {
    saxParser.parse(file, handler)
  }

}
