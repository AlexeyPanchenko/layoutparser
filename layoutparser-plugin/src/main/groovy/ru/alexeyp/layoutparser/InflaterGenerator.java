package ru.alexeyp.layoutparser;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import java.util.List;

import javax.lang.model.element.Modifier;

import ru.alexeyp.layoutparser.parse.XmlElement;

import static ru.alexeyp.layoutparser.utils.Const.CONTEXT;
import static ru.alexeyp.layoutparser.utils.Const.CONTEXT_NAME;
import static ru.alexeyp.layoutparser.utils.Const.CONTEXT_PCKG;
import static ru.alexeyp.layoutparser.utils.Const.R;
import static ru.alexeyp.layoutparser.utils.Const.ROOT_VIEW_NAME;
import static ru.alexeyp.layoutparser.utils.Const.inflater;
import static ru.alexeyp.layoutparser.utils.Const.view;

public class InflaterGenerator {

  private final String className;
  private final String layoutName;
  private final List<XmlElement> elements;

  private final TypeName r;

  public InflaterGenerator(String className, String layoutName, String pckgProject, List<XmlElement> elements) {
    this.className = className;
    this.layoutName = layoutName;
    this.elements = elements;
    this.r = ClassName.get(pckgProject, R);
  }

  public TypeSpec generate() {
    return generateClass();
  }

  private TypeSpec generateClass() {
    TypeSpec.Builder builder =  TypeSpec
        .classBuilder(className)
        .addModifiers(Modifier.PUBLIC)
        .addField(view, ROOT_VIEW_NAME, Modifier.PUBLIC, Modifier.FINAL)
        .addMethod(formConstructor());

    for (XmlElement element : elements) {
      if (element.isValid()) {
        builder.addField(formViewField(element));
      }
    }

    return builder.build();
  }

  private FieldSpec formViewField(XmlElement element) {
    return FieldSpec
        .builder(ClassName.get(element.pckgName, element.type), element.id, Modifier.PUBLIC, Modifier.FINAL)
        .build();
  }

  private MethodSpec formConstructor() {
    MethodSpec.Builder builder = MethodSpec.constructorBuilder()
        .addModifiers(Modifier.PUBLIC)
        .addParameter(ClassName.get(CONTEXT_PCKG, CONTEXT), CONTEXT_NAME)
        .addStatement(ROOT_VIEW_NAME + " = $T.from(" + CONTEXT_NAME + ").inflate($T.layout." + layoutName + ", null)", inflater, r);

    for (XmlElement element : elements) {
      if (element.isValid()) {
        builder.addStatement(element.id + " = " + ROOT_VIEW_NAME + ".findViewById($T.id." + element.id + ")", r);
      }
    }
    return builder.build();
  }

}
