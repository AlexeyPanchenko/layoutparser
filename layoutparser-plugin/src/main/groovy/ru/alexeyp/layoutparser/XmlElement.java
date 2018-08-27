package ru.alexeyp.layoutparser;

public class XmlElement {
  private final String id;
  private final String name;
  private final String type;
  private final String importName;

  public XmlElement(String id, String name, String type, String importName) {
    this.id = id;
    this.name = name;
    this.type = type;
    this.importName = importName;
  }

  public String getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getType() {
    return type;
  }

  public String getImportName() {
    return importName;
  }
}
