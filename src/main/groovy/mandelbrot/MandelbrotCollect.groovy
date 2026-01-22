package mandelbrot

import cluster_framework.records.CollectInterface

class MandelbrotCollect implements CollectInterface <MandelbrotData> {

  int blackCount, whiteCount,points
  long totalIterations

  MandelbrotCollect(List d){
    blackCount = 0
    whiteCount = 0
    points = 0
    totalIterations = 0
  }

  MandelbrotCollect () {}

  @Override
  void finalise( List d){
    println "Points = $points, whites = $whiteCount, blacks = $blackCount, iterations = $totalIterations "
  }

  @Override
  void collate(MandelbrotData ml, List params){
    int width = ml.colour.size()
    0.upto(width-1){ int w->
      points = points + 1
      if (ml.colour[w] == ml.WHITE) whiteCount = whiteCount + 1
      else blackCount = blackCount + 1
    }
    totalIterations = totalIterations + ml.totalIterations
  }

}
