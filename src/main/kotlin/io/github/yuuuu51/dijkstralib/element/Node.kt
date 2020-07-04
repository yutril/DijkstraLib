package io.github.yuuuu51.dijkstralib.element

class Node(
    val id: String
) : Element {

    var cost: Int = Int.MAX_VALUE

    var confirmed: Boolean = false

    var parent: Node? = null

    val nextEdges = mutableListOf<Edge>()

    fun addEdge(edge: Edge) {
        nextEdges.add(edge)
        edge.addNode(this)
    }
}