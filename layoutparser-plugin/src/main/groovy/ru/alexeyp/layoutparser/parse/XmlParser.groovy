package ru.alexeyp.layoutparser.parse

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.xml.sax.Attributes
import org.xml.sax.helpers.DefaultHandler
import ru.alexeyp.layoutparser.utils.Utils

import static ru.alexeyp.layoutparser.utils.Const.*

class XmlParser extends DefaultHandler {

  public final List<XmlElement> elements = new ArrayList<>()
  private final Logger logger = LoggerFactory.getLogger("XML Parser")
  private final String layoutsPath
  private final String layoutsAlternativePath

  XmlParser(String layoutsPath, String layoutsAlternativePath) {
    this.layoutsPath = layoutsPath
    this.layoutsAlternativePath = layoutsAlternativePath
  }

  @Override
  void startElement(String uri, String localName, String qName, Attributes attributes) {
    if (qName == "layout" || qName == "merge" || qName == "tag") {
      return
    }
    if (qName == INCLUDE_NAME) {
      String layoutName = attributes.getValue("layout")
      if (layoutName != null) {
        layoutName = "${Utils.getLastAfter(layoutName)}${XML_SUFFIX}"
        parseIncludeLayout(layoutName)
      }
      return
    }

    String id = attributes.getValue("android:id")
    if (id != null) {
      id = Utils.getLastAfter(id)
    }
    elements.add(formElement(id, qName))
  }

  private void parseIncludeLayout(String layoutName) {
    final XmlParser parser = new XmlParser(layoutsPath, layoutsAlternativePath)
    final XmlParserFacade parserFacade = new XmlParserFacade(parser)
    try {
      parserFacade.parse(new File("${layoutsPath}/$layoutName"))
      elements.addAll(parser.elements)
    } catch (FileNotFoundException ignored) {
      logger.error(
        "WARNING: XML Parser can`t find file with path: " + "${layoutsPath}/${layoutName}. "
          + "It will try to search in ${layoutsAlternativePath}/$layoutName."
      )
      parserFacade.parse(new File("${layoutsAlternativePath}/$layoutName"))
      elements.addAll(parser.elements)
    } catch (Exception e) {
      logger.error(
        "WARNING: XML Parser could not handle <include layout='$layoutName'/>. " + "Stacktrace: $e"
      )
    }
  }

  private XmlElement formElement(String id, String name) {
    final String type
    final String pckgName
    final String[] packages = name.split("\\.")
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
