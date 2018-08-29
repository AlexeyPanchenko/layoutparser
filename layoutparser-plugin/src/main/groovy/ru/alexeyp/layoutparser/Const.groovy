package ru.alexeyp.layoutparser

import com.squareup.javapoet.ClassName
import com.squareup.javapoet.TypeName

class Const {

  public static final String VIEW_PCKG = "android.view"
  public static final String WIDGET_PCKG = "android.widget"
  public static final String WEBKIT_PCKG = "android.webkit"

  public static final String LAYOUT_INFLATER = "LayoutInflater"
  public static final String VIEW = "View"
  public static final String VIEW_GROUP = "ViewGroup"
  public static final String WEB_VIEW = "WebView"

  public static final TypeName inflater = ClassName.get(VIEW_PCKG, LAYOUT_INFLATER)

}
