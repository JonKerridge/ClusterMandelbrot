package invoke

import cluster_framework.run.HostRun
import mandelbrot.MandelbrotCollect
import mandelbrot.MandelbrotData

class RunLocalHost4_1024 {
  static void main(String[] args) {
    String structureFile = "D:\\IJGradle\\ClusterMandelbrot\\src\\main\\groovy\\mandelbrotDSLfiles\\mandelbrot1n4w1024"
    Class  emitClass = MandelbrotData
    Class collectClass = MandelbrotCollect
    new HostRun(structureFile, emitClass, collectClass, "Local").invoke()

  }

}
