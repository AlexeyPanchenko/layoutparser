package ru.alexeyp.layoutparser

class XmlElement {

    final String id
    final String type
    final String name
    final String importName

    XmlElement(String id, String name, String type, String importName) {
        this.id = id
        this.name = name
        this.type = type
        this.importName = importName
    }


    @Override
    String toString() {
        return "XmlElement{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", importName='" + importName + '\'' +
                '}';
    }
}
