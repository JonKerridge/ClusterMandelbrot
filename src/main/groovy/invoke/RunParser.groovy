package invoke

import cluster_cli.parse.Parser

class RunParser {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    Parser parser = new Parser("$workingDirectory/src/main/groovy/mandelbrotDSLfiles/mandelbrot1n8w1024")
    assert parser.parse() :"Parsing failed"
  }

}
