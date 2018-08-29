package ru.alexeyp.layoutparser;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.TypeSpec;

import javax.lang.model.element.Modifier;
import java.util.List;

public class InflaterGenerator {

  private final String className;
  private final List<XmlElement> elements;

  public InflaterGenerator(String className, String pckgProject, List<XmlElement> elements) {
    this.className = className;
    this.elements = elements;
  }

  public TypeSpec generate() {
    return null;
  }

  private TypeSpec generateClass() {
    TypeSpec.Builder builder =  TypeSpec
            .classBuilder(className)
            .addModifiers(Modifier.PUBLIC);

    elements.forEach(element -> builder.addField(getViewField(element)));

    return builder.build();
  }

  private FieldSpec getViewField(XmlElement element) {
    return FieldSpec
        .builder(ClassName.get(element.pckgName, element.type), element.id, Modifier.PUBLIC, Modifier.FINAL)
        .build();
  }

}
