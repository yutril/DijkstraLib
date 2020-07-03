package io.github.yuuuu51.dijkstralib.element

class ElementFactory {

    companion object {
        fun createNode(id: String, name: String): Node {
            return Node(id, name)
        }

        fun createEdge(id: String, length: Int): Edge {
            return Edge(id, length)
        }
    }
}