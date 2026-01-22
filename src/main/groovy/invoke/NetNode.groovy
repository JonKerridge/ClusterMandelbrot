package invoke

import cluster_framework.run.NodeRun
import jcsp.userIO.Ask

class NetNode {
  static void main(String[] args) {
    String hostIP
    if (args.size() == 0)
      hostIP = Ask.string("Host IP address ? : ")
    else
      hostIP = args[0]
    new NodeRun(hostIP).invoke()
  }
}
