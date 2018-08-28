package ru.alexeyp.layoutparser

class XmlElement {

  final String id
  final String type
  private String importName

  XmlElement(String id, String type) {
    this.id = id
    this.type = type
  }

  @Override
  String toString() {
    return "XmlElement{" +
            "id='" + id + '\'' +
            ", type='" + type + '\'' +
            ", importName='" + importName + '\'' +
            '}'
  }
}
