package invoke

import cluster_framework.parse.Parser

class RunParserMultiple {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    for (n in 1..4)
      for (s in [512, 1024, 2048]){
        Parser parser = new Parser("$workingDirectory/src/main/groovy/mandelbrotDSLfiles/mandelbrot${n}n4w${s}")
        assert parser.parse() :"Parsing failed"
      }
  }

}
