package ru.alexeyp.layoutparser

import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler

import static ru.alexeyp.layoutparser.Const.*

class XmlParser extends DefaultHandler {

  public final List<XmlElement> elements = new ArrayList<>()

  @Override
  void startElement(String uri, String localName, String qName, Attributes attributes) {
    String id = attributes.getValue("android:id")
    if (id != null) {
      id = id.substring(id.lastIndexOf("/") + 1)
    }
    elements.add(formElement(id, qName))
  }

  private XmlElement formElement(String id, String name) {
    String type
    String pckgName
    String[] packages = name.split("\\.")
    if (packages.length > 1) {
      type = packages.last()
      pckgName = name - ".$type"
    } else if (name == VIEW || name == VIEW_GROUP) {
      pckgName = VIEW_PCKG
      type = name
    } else if (name == WEB_VIEW) {
      pckgName = WEBKIT_PCKG
      type = name
    } else {
      pckgName = WIDGET_PCKG
      type = name
    }
    return new XmlElement(id, type, pckgName)
  }
}
