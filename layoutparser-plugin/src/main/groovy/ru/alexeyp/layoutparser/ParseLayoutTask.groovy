package ru.alexeyp.layoutparser

import com.squareup.javapoet.JavaFile
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction
import ru.alexeyp.layoutparser.parse.XmlParser
import ru.alexeyp.layoutparser.parse.XmlParserFacade
import ru.alexeyp.layoutparser.utils.Utils

import static ru.alexeyp.layoutparser.utils.Const.*

/**
 * Incremental Gradle task for parse xml layout files, which names starting with
 * {@link ru.alexeyp.layoutparser.utils.Const#GENERATE_INFLATER_PREFIX} or custom prefix and
 * generate Inflater classes, that inflate correct layout files for easy access to all
 * View elements with defined id attribute.
 * It support <include/> tag also.
 */
class ParseLayoutTask extends DefaultTask {

  @InputFiles
  File[] layoutFiles

  @OutputDirectory
  File outDir

  @Input
  String projectPckg

  @Input
  String variantName

  @Input
  String prefix = project.layoutparser.generatePrefix

  @TaskAction
  def generate() {
    layoutFiles
      .findAll { it.name.startsWith(prefix) }
      .each { generate(it) }
  }

  void generate(File xmlFile) {
    final String layoutPath = "${project.projectDir}$LAYOUT_DIR"
    final XmlParser parser = new XmlParser(layoutPath, layoutPath.replace(MAIN_DIR, variantName))
    new XmlParserFacade(parser).parse(xmlFile)

    final String nameNoPrefix = xmlFile.name.replace(GENERATE_INFLATER_PREFIX, "")
    final String className = "${Utils.formClassNameFromXml(nameNoPrefix)}${INFLATER_SUFFIX}"
    final String layoutName = xmlFile.name.replace(".xml", "")
    final String generatePckg = projectPckg + ".${LAYOUTPARSER_NAME}"

    final JavaFile javaFile = JavaFile.builder(
      generatePckg, new InflaterGenerator(className, layoutName, projectPckg, parser.elements).generate()
    ).build()
    javaFile.writeTo(outDir)
  }
}
