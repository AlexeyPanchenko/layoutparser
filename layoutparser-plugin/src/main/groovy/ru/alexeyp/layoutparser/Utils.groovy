package ru.alexeyp.layoutparser

/**
 * Form file name to CamelCase style.
 */
static String formClassNameFromXml(String xmlName) {
  StringBuilder nameBuilder = new StringBuilder()
  xmlName
          .replace('.xml', '')
          .split('_')
          .each { nameBuilder.append(it.capitalize()) }
  return nameBuilder.toString()
}

