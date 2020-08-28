package io.github.yuuuu51.dijkstralib.element

import kotlin.Cloneable

class ElementManager(
    private var nodes: Map<String, Node>,
    private var edges: Map<String, Edge>
) {

    val nodesCache = nodes.toMap()

    val edgesCache = edges.toMap()

    fun existsNode(id: String): Boolean {
        return nodes.containsKey(id)
    }

    fun getNode(id: String): Node {
        require(existsNode(id))
        return nodes[id] ?: error("Node $id not found")
    }

    fun existsEdge(id: String): Boolean {
        return edges.containsKey(id)
    }

    fun getEdge(id: String): Edge {
        require(existsEdge(id))
        return edges[id] ?: error("Edge $id not found")
    }

    fun getMinCostNode(): Node {
        var min = Double.MAX_VALUE
        var minNode: Node? = null
        nodes.forEach {(_, node) ->
            if (node.isConfirmed()) return@forEach
            if (min > node.cost) {
                min = node.cost
                minNode = node
            }
        }
        return minNode!!
    }

    fun reset() {
        nodes = nodesCache.toMap()
        edges = edgesCache.toMap()
    }
}