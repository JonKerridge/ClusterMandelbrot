package mandelbrot

import cluster_framework.records.EmitInterface

class MandelbrotData implements  EmitInterface<MandelbrotData>, Serializable{

  int []colour		  // array of colour values for this line
  double [][] line 	// array of [x,y] values for this line
  int escapeValue
  long totalIterations
  int lineY, heightPoints, widthPoints, maxIterations
  double delta
  double ly



  int WHITE = 1
  int BLACK = 0
  double minX = -2.5
  double minY = 1.0
  double rangeX = 3.5
  double rangeY = 2.0

  MandelbrotData ( List d){
    this.widthPoints = (int) d[0]
    this.maxIterations = (int) d[1]
    this.delta = rangeX / ((double) widthPoints)
    this.heightPoints = (int) (rangeY / delta )
    this.lineY = 0
    println "MD construct: width: $widthPoints, height: $heightPoints, delta: $delta, escape: $maxIterations"
  }

  MandelbrotData(int widthPoints, int maxIterations, int lineY, double delta){

    this.widthPoints = widthPoints
    this.maxIterations = maxIterations
    this.delta = delta
    colour = new int[widthPoints]
    line = new double[widthPoints][2]
    escapeValue = maxIterations
    totalIterations = 0
    ly = lineY * delta //y value for this line
    0.upto(widthPoints-1){ int w ->
      line[w][0] = minX + ( w * delta)
      line[w][1] = minY - ly
    }

  }

  @ Override
  MandelbrotData create (Object sd) {
    if (lineY == heightPoints) return null
    MandelbrotData md = new MandelbrotData( widthPoints, maxIterations, lineY, delta)
    lineY = lineY + 1
    return md
  }

// based on algorithm at
 void calculateColour (Object wd, List p) {
    int width = colour.size()
//    println "Calc : $ly"
    0.upto(width-1){ int w->
      double xl = 0.0, yl = 0.0, xtemp = 0.0
      int iterations = 0
      while (((xl * xl)+(yl * yl) < 4) && iterations < escapeValue) {
        xtemp = (xl * xl) - (yl * yl) + line[w][0]
        yl = (2 * xl * yl) + line[w][1]
        xl = xtemp
        iterations++
      }
      totalIterations = totalIterations + iterations
      colour[w] = (iterations < escapeValue) ? WHITE : BLACK
//      println " C: x = ${line[w][0]}, y = ${line[w][1]}, iter = $iterations, col = ${colour[w]}, w = $w"
    }
  } // calculate

}
