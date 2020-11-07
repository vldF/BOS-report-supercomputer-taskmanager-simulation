import cluster.*

fun main() {
    val cluster = Cluster()
    val flooder = TaskFlooderSenderInitiatedNode()
    cluster.addNode(flooder)

    for (i in 0 until nodesCount-1) {
        val node = SenderInitiatedComputerNode()
        cluster.addNode(node)

        node.start()
    }

    flooder.start()

    while (true) {
        println(cluster.nodes.withIndex().joinToString(separator = ", ") { "${it.index}: ${it.value.loadedTasks.size}" })
        Thread.sleep(10)
    }
}