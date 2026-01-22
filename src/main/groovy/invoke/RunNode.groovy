package invoke

import cluster_framework.run.NodeRun

class RunNode {
  static void main(String[] args) {
    new NodeRun(args[0], args[1]).invoke()
  }
}
