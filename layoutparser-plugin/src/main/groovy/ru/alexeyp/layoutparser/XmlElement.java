package ru.alexeyp.layoutparser;

public class XmlElement {

  public final String id;
  public final String type;
  public final String pckgName;

  public XmlElement(String id, String type, String pckgName) {
    this.id = id;
    this.type = type;
    this.pckgName = pckgName;
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
