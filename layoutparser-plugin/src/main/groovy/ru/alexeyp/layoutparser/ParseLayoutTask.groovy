package ru.alexeyp.layoutparser

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
    layoutFiles.findAll { it.name.startsWith('r_') }
  }
}