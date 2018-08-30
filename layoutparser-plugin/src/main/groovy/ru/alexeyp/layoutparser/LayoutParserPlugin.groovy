package ru.alexeyp.layoutparser

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project

class LayoutParserPlugin implements Plugin<Project> {

  private final String LAYOUT_DIR = "/src/main/res/layout"
  private final String GENERATED = "generated/source/${Const.LAYOUTPARSER_NAME}/"

  @Override
  void apply(Project project) {
    def isAndroidApp = project.plugins.findPlugin('com.android.application') != null
    def isAndroidLib = project.plugins.findPlugin('com.android.library') != null
    if (!isAndroidApp && !isAndroidLib) {
      throw new GradleException("'com.android.application' or 'com.android.library' plugin required.")
    }
    project.extensions.create('layoutparser', LayoutParserExtension)

    def variants = isAndroidApp ? project.android.applicationVariants : project.android.libraryVariants
    variants.all { variant ->
      if (project.layoutparser.enable) generateInflater(project, variant)
    }
  }

  private void generateInflater(Project project, variant) {
    File[] xmlFiles = new File("${project.projectDir}$LAYOUT_DIR").listFiles()
    File outputDir = new File(project.buildDir, "$GENERATED${variant.dirName}")
    def task = project.tasks.create(
            "parseLayout${variant.name.capitalize()}", ParseLayoutTask
    ) {
      outDir = outputDir
      layoutFiles = xmlFiles
      projectPckg = variant.applicationId
    }
    variant.registerJavaGeneratingTask task, outputDir
  }

}
