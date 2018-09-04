package ru.alexeyp.layoutparser.parse;

public class XmlElement {

  public final String id;
  public final String type;
  public final String pckgName;

  public XmlElement(String id, String type, String pckgName) {
    this.id = id;
    this.type = type;
    this.pckgName = pckgName;
  }

  public boolean isValid() {
    return id != null && type != null && pckgName != null;
  }

  @Override
  public String toString() {
    return "XmlElement{" +
        "id='" + id + '\'' +
        ", type='" + type + '\'' +
        ", importName='" + pckgName + '\'' +
        '}';
  }
}
