package io.github.yuuuu51.dijkstralib.element

class Edge(
    val id: String,
    val length: Double
) : Element {

    val nextNodes = mutableListOf<Node>()

    fun addNode(node: Node) {
        nextNodes.add(node)
    }
}