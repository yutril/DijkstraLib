package io.github.yuuuu51.dijkstralib.element

class Node(
    val id: String
) : Element {

    var cost = Double.MAX_VALUE

    var parent: Node? = null

    val nextEdges = mutableListOf<Edge>()

    private var confirmed: Boolean = false

    fun addEdge(edge: Edge) {
        nextEdges.add(edge)
        edge.addNode(this)
    }

    fun isConfirmed(): Boolean {
        return confirmed
    }

    fun confirm(confirmed: Boolean = true) {
        this.confirmed = confirmed
    }
}