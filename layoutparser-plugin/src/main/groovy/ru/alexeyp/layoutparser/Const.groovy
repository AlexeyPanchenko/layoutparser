package ru.alexeyp.layoutparser

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName

class Const {

  public static final String VIEW_PCKG = "android.view"
  public static final String WIDGET_PCKG = "android.widget"
  public static final String WEBKIT_PCKG = "android.webkit"
  public static final String CONTEXT_PCKG = "android.content"

  public static final String LAYOUT_INFLATER = "LayoutInflater"
  public static final String VIEW = "View"
  public static final String VIEW_GROUP = "ViewGroup"
  public static final String WEB_VIEW = "WebView"
  public static final String CONTEXT = "Context"
  public static final String R = "R"

  public static final String ROOT_VIEW_NAME = "rootInflaterView"
  public static final String CONTEXT_NAME = "context"
  public static final String LAYOUTPARSER_NAME = "layoutparser"

  public static final String INFLATER_SUFFIX = "Inflater"
  public static final String GENERATE_INFLATER_PREFIX = "i_"

  public static final TypeName inflater = ClassName.get(VIEW_PCKG, LAYOUT_INFLATER)
  public static final TypeName view = ClassName.get(VIEW_PCKG, VIEW)

}
