package ru.alexeyp.layoutparser

import com.squareup.javapoet.JavaFile
import org.gradle.api.DefaultTask
import org.gradle.api.tasks.Input
import org.gradle.api.tasks.InputFiles
import org.gradle.api.tasks.OutputDirectory
import org.gradle.api.tasks.TaskAction

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
            .findAll { it.name.startsWith('r_') }
            .each {generate(it)}
  }

  void generate(File xmlFile) {
    XmlParser parser = new XmlParser()
    new XmlParserFacade(parser).parse(xmlFile)
    println "PARSE: ${parser.elements}"
    println "PARSE2: ${xmlFile.name}"
    println "PARSE3: ${projectPckg}"
    println "PARSE4: ${outDir}"
    String className = "${Utils.formClassNameFromXml(xmlFile.name)}${Const.INFLATER_SUFFIX}"
    String layoutName = xmlFile.name.replace(".xml", "")

    String generatePckg = projectPckg + '.layoutparser'
    String generatePath = generatePckg.replace('.', '/')


    File file = new File("${outDir.path}/$generatePath/${className}.java")
    if (file.exists()) return

    JavaFile javaFile = JavaFile.builder(
        generatePckg, new InflaterGenerator(className, layoutName, projectPckg, parser.elements).generate()
    ).build()
    javaFile.writeTo(outDir)
  }
}
