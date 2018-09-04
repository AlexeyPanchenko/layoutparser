package ru.alexeyp.layoutparser.utils

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

static String getLastAfter(String text, String afterChar = "/") {
  return text.substring(text.lastIndexOf(afterChar) + 1)
}

