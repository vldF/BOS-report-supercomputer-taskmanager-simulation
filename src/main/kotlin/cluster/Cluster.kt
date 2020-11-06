package cluster

class Cluster {
    val nodes = mutableListOf<AbstractComputerNode>()
    fun addNode(node: AbstractComputerNode) {
        nodes.add(node)
        node.cluster = this
    }
}