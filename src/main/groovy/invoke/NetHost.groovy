package invoke

import cluster_framework.run.HostRun
import jcsp.userIO.Ask
import mandelbrot.MandelbrotCollect
import mandelbrot.MandelbrotData

class   NetHost{
  static void main(String[] args) {
    String structureFile
    if (args.size() == 0)
      structureFile = Ask.string("Full pathname of the structure file? : ")
    else
      structureFile = args[0]
    new HostRun(structureFile, MandelbrotData, MandelbrotCollect).invoke()
  }
}
