package ru.alexeyp.layoutparser;

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
    return TypeSpec
            .classBuilder(className)
            .addModifiers(Modifier.PUBLIC)
            .build();
  }

  private FieldSpec getViewField() {
  }

}
