package io.github.yuuuu51.dijkstralib

import io.github.yuuuu51.dijkstralib.element.Edge
import io.github.yuuuu51.dijkstralib.element.Node
import io.github.yuuuu51.dijkstralib.element.ElementManager

class DijkstraLib(
    nodes: Map<String, Node>,
    edges: Map<String, Edge>
) {

    private val manager = ElementManager(nodes, edges)

    fun execute(startId: String, goalId: String): List<Node> {
        manager.reset()
        val startNode = manager.getNode(startId)
        startNode.cost = 0.0
        while (true) {
            val node = manager.getMinCostNode()
            node.confirm()
            if (node.id == goalId) break
            node.nextEdges.forEach { edge ->
                edge.nextNodes.forEach loop@ {
                    if (it.id == node.id || it.isConfirmed()) return@loop
                    val distance = edge.length
                    if (it.cost > node.cost + distance) {
                        it.cost = node.cost + distance
                        it.parent = node
                    }
                }
            }
        }
        val goalNode = manager.getNode(goalId)
        val route = mutableListOf<Node>()
        route.add(goalNode)
        while (true) {
            val last = route.last()
            if (last.id == startId) break
            route.add(last.parent!!)
        }
        route.reverse()
        return route
    }
}