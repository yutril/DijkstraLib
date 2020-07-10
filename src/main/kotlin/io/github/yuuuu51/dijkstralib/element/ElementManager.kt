package io.github.yuuuu51.dijkstralib.element

class ElementManager(
    private var nodes: Map<String, Node>,
    private var edges: Map<String, Edge>
) {

    fun existsNode(id: String): Boolean {
        return nodes.containsKey(id)
    }

    fun getNode(id: String): Node {
        require(existsNode(id))
        return nodes[id]!!
    }

    fun existsEdge(id: String): Boolean {
        return edges.containsKey(id)
    }

    fun getEdge(id: String): Edge {
        require(existsEdge(id))
        return edges[id]!!
    }

    fun getMinCostNode(): Node {
        var min: Float = Float.MAX_VALUE
        var minNode: Node? = null
        nodes.forEach {(_, node) ->
            if (node.confirmed) return@forEach
            if (min > node.cost) {
                min = node.cost
                minNode = node
            }
        }
        return minNode!!
    }
}