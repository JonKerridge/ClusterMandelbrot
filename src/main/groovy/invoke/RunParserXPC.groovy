package invoke

import cluster_framework.parse.Parser

class RunParserXPC {
  static void main(String[] args) {
    String workingDirectory = System.getProperty("user.dir")
    for (w in [4, 8,12,16,20,24])
      for (s in [512, 1024, 2048]){
        Parser parser = new Parser("$workingDirectory/src/main/groovy/XPCextras/xpc${w}w${s}")
        assert parser.parse() :"Parsing failed"
      }
  }

}
