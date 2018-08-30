package ru.alexeyp.layoutparser

import com.squareup.javapoet.JavaFile
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

/**
 * Incremental Gradle task for parse xml layout files,
 * which names starting with {@link Const#GENERATE_INFLATER_PREFIX} and generate Inflater classes,
 * that inflate correct layout files for easy access to all View elements with defined id attribute.
 */
class ParseLayoutTask extends DefaultTask {

  @InputFiles
  File[] layoutFiles

  @OutputDirectory
  File outDir

  @Input
  String projectPckg

  @TaskAction
  def generate() {
    layoutFiles
            .findAll { it.name.startsWith(Const.GENERATE_INFLATER_PREFIX) }
            .each {generate(it)}
  }

  void generate(File xmlFile) {
    final String nameNoPrefix = xmlFile.name.replace(Const.GENERATE_INFLATER_PREFIX, "")
    final String className = "${Utils.formClassNameFromXml(nameNoPrefix)}${Const.INFLATER_SUFFIX}"
    final String layoutName = xmlFile.name.replace(".xml", "")

    final String generatePckg = projectPckg + ".${Const.LAYOUTPARSER_NAME}"

    final XmlParser parser = new XmlParser()
    new XmlParserFacade(parser).parse(xmlFile)

    final JavaFile javaFile = JavaFile.builder(
        generatePckg, new InflaterGenerator(className, layoutName, projectPckg, parser.elements).generate()
    ).build()
    javaFile.writeTo(outDir)
  }
}
