package invoke

import cluster_framework.run.NodeRun

class Node4 {
  static void main(String[] args) {
    new NodeRun("127.0.0.1", "127.0.0.4").invoke()
  }
}
