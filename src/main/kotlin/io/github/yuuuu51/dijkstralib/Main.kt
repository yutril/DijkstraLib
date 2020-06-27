package io.github.yuuuu51.dijkstralib

import com.beust.klaxon.JsonObject
import com.beust.klaxon.Klaxon
import io.github.yuuuu51.dijkstralib.element.Node
import io.github.yuuuu51.dijkstralib.element.NodeManager
import java.nio.file.Files
import java.nio.file.Paths

const val START_NODE_ID = "1"
const val GOAL_NODE_ID = "8"

fun main(args: Array<String>) {
    val manager = NodeManager(loadJson("/nodes.json"))
    val startNode = manager.getNode(START_NODE_ID)
    startNode.cost = 0
    while (true) {
        val node = manager.getMinCostNode()
        node.confirmed= true
        if (node.id == GOAL_NODE_ID) break
        node.nextNodes.forEach {
            if (it.confirmed) return@forEach
            val distance = node.getDistance(it.id)
            if (distance !is Int) return@forEach
            if (it.cost > node.cost + distance) {
                it.cost = node.cost + distance
                it.parent = node
            }
        }
    }
    val goalNode = manager.getNode(GOAL_NODE_ID)
    val route = mutableListOf<Node>()
    route.add(goalNode)
    while (true) {
        val last = route.last()
        if (last.id == START_NODE_ID) break
        route.add(last.parent!!)
    }
    route.reverse()
    var output = ""
    route.forEach {
        output += "${it.name}\n"
    }
    println(output)
}

fun loadJson(path: String): JsonObject {
    val klaxon = Klaxon()
    val br = Files.newBufferedReader(Paths.get(klaxon::class.java.getResource(path).path))
    return klaxon.parseJsonObject(br)
}