package org.ocbkc.swift.OCBKC
{  
import org.ocbkc.swift.model._
import System._
import org.ocbkc.swift.cores.{TraitGameCore, NotUna}
import org.ocbkc.swift.cores.gameCoreHelperTypes._
import org.ocbkc.swift.global._
import net.liftweb.json._
import java.io._
import net.liftweb.common.{Box,Empty,Failure,Full}
//import scala.util.parsing.combinator.Parsers._
import org.ocbkc.swift.parser._

/* Conventions:
Abbreviation for constitution: consti (const is to much similar to constant).


*/

object ConstitutionTypes
{  type ConstiId = Int
}

import ConstitutionTypes._

class Constitution(  val id:ConstiId, // unique identifier for this constitution
                     val creationTime:Int,
                     val creatorUserID:Int
                  )
{  var averageScore = 0;
   //var text:String = "" // complete constitution in html
   var shortDescription = ""
   var predecessorId:ConstiId = 0 // constitution from which this constitution was branched
   var htmlFileName = "constitution" + id + ".html"

   // create html file which holds the constitution
   val outFile = new File(GlobalConstant.CONSTITUTIONDIR + htmlFileName)

   err.println("  creating file: " + outFile.getAbsolutePath)
   val out:PrintWriter = new PrintWriter(new BufferedWriter(new FileWriter(outFile)))
   out.print(Constitution.templateNewConstitution(id))
   out.flush()
   out.close()
   /* <? &y2012.05.28.16:36:27& what would be a good way to store constitutions? In a database? As files? In memory in a string field (of course not, but just to make my list complete)?> 
   */
}

object Constitution
{  var constis:List[Constitution] = Nil
   var highestId:Int = 0 // <&y2012.03.18.17:31:11& deserialise in future when starting session>
   /*
   def create():Constitution =
   {  // if no user is given assume 'master' user, TODO <&y2012.05.24.20:44:12& determine fixed number for this, for now I used ID 0>
      create(0)
   }
   */
   def templateNewConstitution(constitutionId:Int):String =
   """<h1>Constitution """ + constitutionId + """</h1>

<h2>Article 1</h2>
   """
   def create(creatorUserID:Int):Constitution = 
   {  highestId += 1
      val c = new Constitution( highestId, currentTimeMillis().toInt, creatorUserID )
      constis = c::constis
      c
   }

   /* <&y2012.05.24.11:03:48& for next subincrement>
   def remove(c:Constitution) = ...
   */
}

}