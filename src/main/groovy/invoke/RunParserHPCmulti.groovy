package invoke

import cluster_framework.parse.Parser

class RunParserHPCmulti {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    for (w in [4,6,8,10,12]) {
      Parser parser = new Parser("$workingDirectory/src/main/groovy/HPCmulti/hpc2n${w}w2048")
      assert parser.parse(): "Parsing failed"
    }
  }
}
