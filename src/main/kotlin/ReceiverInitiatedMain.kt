import cluster.Cluster
import cluster.ReceiverInitiatedComputerNode
import cluster.TaskFlooderReceiverInitiatedNode

fun main() {
    val cluster = Cluster()
    val flooder = TaskFlooderReceiverInitiatedNode()
    cluster.addNode(flooder)

    for (i in 0 until nodesCount-1) {
        val node = ReceiverInitiatedComputerNode()
        cluster.addNode(node)

        node.start()
    }

    flooder.start()

    while (true) {
        println(cluster.nodes.withIndex().joinToString(separator = ", ") { "${it.index}: ${it.value.nodeLoad}" })
        Thread.sleep(10)
    }

}