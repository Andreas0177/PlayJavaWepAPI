
package views.html

import _root_.play.twirl.api.TwirlFeatureImports._
import _root_.play.twirl.api.TwirlHelperImports._
import _root_.play.twirl.api.Html
import _root_.play.twirl.api.JavaScript
import _root_.play.twirl.api.Txt
import _root_.play.twirl.api.Xml
import models._
import controllers._
import play.api.i18n._
import views.html._
import play.api.templates.PlayMagic._
import java.lang._
import java.util._
import play.core.j.PlayMagicForJava._
import play.mvc._
import play.api.data.Field
import play.data._
import play.core.j.PlayFormsMagicForJava._
import scala.jdk.CollectionConverters._

object queryform extends _root_.play.twirl.api.BaseScalaTemplate[play.twirl.api.HtmlFormat.Appendable,_root_.play.twirl.api.Format[play.twirl.api.HtmlFormat.Appendable]](play.twirl.api.HtmlFormat) with _root_.play.twirl.api.Template0[play.twirl.api.HtmlFormat.Appendable] {

  /**/
  def apply/*1.2*/():play.twirl.api.HtmlFormat.Appendable = {
    _display_ {
      {


Seq[Any](format.raw/*2.1*/("""
"""),_display_(/*3.2*/main("Hello World")/*3.21*/ {_display_(Seq[Any](format.raw/*3.23*/("""
  """),_display_(/*4.4*/defining(play.core.PlayVersion.current)/*4.43*/ { version =>_display_(Seq[Any](format.raw/*4.56*/("""
  """),format.raw/*5.3*/("""<form method="post" action=""""),_display_(/*5.32*/routes/*5.38*/.HomeController.postRes),format.raw/*5.61*/("""">
    <h1>Search Airports by Country Code</h1>
    <input type="text" name="query" /><br/>
    <input type="submit" value="Search" />
  </form>

  """)))}),format.raw/*11.4*/("""
""")))}),format.raw/*12.2*/("""
"""))
      }
    }
  }

  def render(): play.twirl.api.HtmlFormat.Appendable = apply()

  def f:(() => play.twirl.api.HtmlFormat.Appendable) = () => apply()

  def ref: this.type = this

}


              /*
                  -- GENERATED --
                  SOURCE: app/views/queryform.scala.html
                  HASH: 54183bd0d46ba59ce1aad440679bf0a6f96151a6
                  MATRIX: 904->1|1000->4|1027->6|1054->25|1093->27|1122->31|1169->70|1219->83|1248->86|1303->115|1317->121|1360->144|1539->293|1571->295
                  LINES: 27->1|32->2|33->3|33->3|33->3|34->4|34->4|34->4|35->5|35->5|35->5|35->5|41->11|42->12
                  -- GENERATED --
              */
          