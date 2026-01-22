package mandelbrot

import cluster_framework.records.CollectInterface
import jcsp.awt.DisplayList
import jcsp.awt.GraphicsCommand
import java.awt.*
import java.util.List

class Mgui implements CollectInterface <MandelbrotData> {
  int width, height
  int blackCount, whiteCount,points
  long totalIterations

  Mgui ( List d){
    width = d[0]
    height = d[1]
    blackCount = 0
    whiteCount = 0
    points = 0
    totalIterations = 0
  }

  int initClass ( List d){
    // could change size of canvas
    width = d[0]
    height = d[1]
    int pixels = width * height
    Color colour = d[2]
    DisplayList dList = d[3]
    GraphicsCommand[] graphics = new GraphicsCommand[(pixels * 2) + 1]
    graphics[0] = new GraphicsCommand.ClearRect(0,0, width, height)
    // populate the rest of the graphics with initial colour
    for ( y in 0 ..< height){
      for ( x in 0 ..< width){
        int p = ((x * height + y) * 2) + 1
        graphics[p] = new GraphicsCommand.SetColor(colour)
        graphics[p+1] = new GraphicsCommand.FillRect( x,y,1,1)
      }
    }
    // now set the graphics
    dList.set(graphics)
  }

//  void collate (MandelbrotData md, DisplayList dList){
  void collate (MandelbrotData md, List params){
    DisplayList dList = params[0] as DisplayList
    GraphicsCommand [] modifier= new GraphicsCommand [width * 2]
    int lineY = md.ly * 100
    for ( x in 0 ..< width){
      points += 1
      if ( md.colour[x] == md.WHITE){
        modifier[x*2] = new GraphicsCommand.SetColor(Color.WHITE)
        whiteCount += 1
      }
      else {
        modifier[x*2] = new GraphicsCommand.SetColor(Color.BLACK)
        blackCount += 1
      }
      modifier[x*2 + 1]= new GraphicsCommand.FillRect( x,lineY,1,1)
    }
    int offset = 1 + lineY * width * 2
    dList.change(modifier, offset)
    totalIterations = totalIterations + md.totalIterations
  }

  void finalise( List d){
    println "$points processed with $whiteCount white and $blackCount black with $totalIterations iterations"
  }

}
