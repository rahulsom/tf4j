package com.github.rahulsom.tf4j

import org.tensorflow.Graph
import org.tensorflow.Output
import org.tensorflow.Session
import org.tensorflow.Tensor

/**
 * Created by rahul on 1/22/17.
 */
class Main {

  static void main(String[] args) {
    def graph = new Graph()
    constant(graph, 'hello', 'Hello World'.bytes)
    def session = new Session(graph)
    println new String(session.runner().fetch('hello').run()[0].bytesValue())
  }

  static Output constant(Graph g, String name, Object value) {
    Tensor t = Tensor.create(value)
    try {
      return g.opBuilder("Const", name)
          .setAttr("dtype", t.dataType())
          .setAttr("value", t)
          .build()
          .output(0)
    } finally {
      t.close()
    }
  }


}
