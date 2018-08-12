
/**
 * A node/vertex in the graph.
 */
data class Node(val name: String, var minDistance: Int = 0, var prev: Node? = null, var edges: List<Edge> = emptyList()) {
    override fun toString() : String {
        return "Node(name: $name, minDistance: $minDistance, prev: ${prev?.name})\n"
    }
}

/**
 * An edge to a node in the graph
 */
data class Edge(val dest: Node, val distance: Int) {
    override fun toString() : String {
        return "Edge(dest: ${dest.name}, distance: $distance)\n"
    }
}

/**
 * Using Dijkstra's algorithm, finds the shortest path to all nodes in the graph from the given start node.
 */
fun findShortestPath(start: Node) {

    // let distance to all other nodes be Infinity
    val allNodes = getAllNodes(start)
    allNodes.forEach{ n -> n.minDistance = Int.MAX_VALUE}

    // let distance from start vertex be 0
    start.minDistance = 0

    val unvisited = allNodes.toMutableList()

    // repeat while there are unvisited nodes
    while (!unvisited.isEmpty()) {

        // visit node with smallest known min distance from start vertex
        val current = unvisited.minBy { n -> n.minDistance }!!
        println("Current node: $current")

        // for each unvisited neighbour of the current node
        val unvisitedNeighbours = current?.edges?.filter { n -> unvisited.contains(n.dest) }

        println("Edges to unvisited neighbours: $unvisitedNeighbours")

        for (e in unvisitedNeighbours) {
            // distance from start is the distance to current node plus the distance to the neighbour
            val dist = e.distance + current.minDistance

            // if the calculated distance is smaller than the existing one, update the min distance on the neighbour,
            // and update the previous node with the current
            if (dist < e.dest.minDistance) {
                println("Updating distance to node ${e.dest.name}: ${e.dest.minDistance} -> $dist ")
                e.dest.minDistance = dist
                e.dest.prev = current
            }
        }

        // remove the current from the list of unvisited
        unvisited.remove(current)
    }
}


/**
 * Does BFS to find all nodes in the graph.
 */
fun getAllNodes(start: Node): List<Node> {

    var visited = mutableListOf<Node>()
    var allNodes = mutableListOf<Node>()

    var queue = mutableListOf<Node>()

    queue.add(start)
    visited.add(start)

    while (!queue.isEmpty()) {

        val current = queue.removeAt(0)

        allNodes.add(current)

        for (e in current.edges) {
            if (!visited.contains(e.dest)) {
                queue.add(e.dest)
                visited.add(e.dest)
            }
        }
    }

    return allNodes
}


fun main(args : Array<String>) {

    // test graph
    val one = Node("1")
    val two = Node("2")
    val three = Node("3")
    val four = Node("4")
    val five = Node("5")
    val six = Node("6")

    one.edges = listOf(Edge(three, 9), Edge(two, 7), Edge(six, 14))
    two.edges = listOf(Edge(one, 7), Edge(three, 10), Edge(four, 15))
    three.edges = listOf(Edge(six, 2), Edge(four, 11), Edge(two, 10), Edge(one, 9))
    four.edges = listOf(Edge(five, 6), Edge(three, 11), Edge(two, 15))
    five.edges = listOf(Edge(six, 9), Edge(four, 6))
    six.edges = listOf(Edge(one, 14), Edge(three, 2), Edge(five, 9))

    // find minimum distance of every one from node 1
    findShortestPath(one)

    var allNodes = getAllNodes(one)
    println("Minimum distances:\n $allNodes")

}

