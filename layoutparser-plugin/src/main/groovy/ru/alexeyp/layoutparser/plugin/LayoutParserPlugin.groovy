package ru.alexeyp.layoutparser.plugin

import org.gradle.api.GradleException
import org.gradle.api.Plugin
import org.gradle.api.Project
import ru.alexeyp.layoutparser.ParseLayoutTask
import ru.alexeyp.layoutparser.utils.Const

/**
 * Gradle plugin for register task that can inflate xml layout files.
 * @see ParseLayoutTask.
 */
class LayoutParserPlugin implements Plugin<Project> {

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
    File[] xmlFiles = new File("${project.projectDir}$Const.LAYOUT_DIR").listFiles()
    File outputDir = new File(project.buildDir, "$Const.GENERATED_DIR${variant.dirName}")
    def task = project.tasks.create(
      "parseLayout${variant.name.capitalize()}", ParseLayoutTask
    ) {
      outDir = outputDir
      layoutFiles = xmlFiles
      projectPckg = variant.applicationId
      variantName = variant.name
    }
    variant.registerJavaGeneratingTask task, outputDir
  }

}
