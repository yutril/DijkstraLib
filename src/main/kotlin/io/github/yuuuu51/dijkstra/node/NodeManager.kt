package io.github.yuuuu51.dijkstra.node

import com.beust.klaxon.JsonObject

class NodeManager(json: JsonObject) {

    private val nodes = mutableMapOf<String, Node>()

    init {
        json.forEach {(id, data) ->
            check(data is Map<*, *>)
            check(data.containsKey("name") && data["name"] is String)
            nodes[id] = Node(id, data["name"].toString())
        }
        nodes.forEach {(id, node) ->
            val data = json[id]
            check(data is Map<*, *>)
            check(data.containsKey("next"))
            val nextNodes = data["next"]
            check(nextNodes is Map<*, *>)
            nextNodes.forEach {(id, distance) ->
                check(id is String && distance is Int)
                node.addNextNode(getNode(id), distance)
            }
        }
    }

    fun exists(id: String): Boolean {
        return nodes.containsKey(id)
    }

    fun getNode(id: String): Node {
        require(exists(id))
        return nodes[id]!!
    }

    fun getMinCostNode(): Node {
        var min: Int = Int.MAX_VALUE
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