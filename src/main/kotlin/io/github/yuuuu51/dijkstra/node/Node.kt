package io.github.yuuuu51.dijkstra.node

import java.lang.Exception

class Node(
    val id: String,
    var name: String
) {

    var cost: Int = Int.MAX_VALUE

    var confirmed: Boolean = false

    var parent: Node? = null

    val nextNodes = mutableListOf<Node>()

    private val nextNodeDistances = mutableMapOf<String, Int>()

    fun addNextNode(node: Node, distance: Int) {
        if (nextNodes.contains(node)) throw Exception("Node {${node.name}} is already added")
        nextNodes.add(node)
        nextNodeDistances[node.id] = distance
    }

    fun getDistance(id: String): Int?{
        return nextNodeDistances[id]
    }

    fun getNearestNode(): Node {
        var min: Int = Int.MAX_VALUE
        var minNode: Node? = null
        nextNodes.forEach {
            val distance = nextNodeDistances[it.id]!!
            if (min > distance) {
                min = distance
                minNode = it
            }
        }
        return minNode!!
    }
}