package models

import play.api.libs.json._

/**
 * Conversion utility class for converting one Json to another. Transformation rules:
 *
 * JsObject :
        JsString  - reverse it
        JsNumber  - square it
        JsBoolean - flip it
        JsNull    - do nothing to it
        JsArray   - recursively apply the transformation to elements in this JsArray
        JSObject  - recursively apply the transformation to elements in this JsObject
 */


class JsonTransformer {

  def transform(json: JsValue): JsValue = {
    json match {
      case JsNumber(number) => JsNumber(number * number)
      case JsString(str) => JsString(str.reverse)
      case JsBoolean(bool) => JsBoolean(!bool)
      case n@JsNull => n
      case JsArray(jsList) => JsArray(jsList map (x=> transform(x)))//or
      //case JsArray(jsList) => JsArray(jsList map (transform))
      case JsObject(jsPairs) => JsObject(jsPairs.map {
        x=>(x._1, transform(x._2))
      })//or
      //case JsObject(jsPairs) => JsObject(jsPairs.map {
       // case (k,v) =>(k, transform(v))
      //})//or mapvalues function of jspair
      //case JsObject(jsPairs) => JsObject(jsPairs.mapValues(transform))
    }
  }
}

