package ru.alexeyp.layoutparser.plugin

import ru.alexeyp.layoutparser.utils.Const

/**
 * Plugin extension for settings {@link ru.alexeyp.layoutparser.ParseLayoutTask}.
 */
class LayoutParserExtension {
  boolean enable = true
  String generatePrefix = Const.GENERATE_INFLATER_PREFIX
}
