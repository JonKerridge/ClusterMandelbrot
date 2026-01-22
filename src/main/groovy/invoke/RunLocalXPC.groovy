package invoke

import cluster_framework.run.HostRun
import mandelbrot.MandelbrotCollect
import mandelbrot.MandelbrotData

class RunLocalXPC {
  static void main(String[] args) {
    String structureFile = "D:\\IJGradle\\ClusterMandelbrot\\src\\main\\groovy\\XPCextras\\xpc24w2048"
    Class  emitClass = MandelbrotData
    Class collectClass = MandelbrotCollect
    new HostRun(structureFile, emitClass, collectClass, "Local").invoke()
  }

}
