package cluster

import java.util.concurrent.ConcurrentLinkedQueue

abstract class AbstractComputerNode() : Thread() {
    val loadedTasks = ConcurrentLinkedQueue<Task>()
    lateinit var cluster: Cluster
    abstract val nodeLoad: Double

    override fun run() {
        super.run()
    }
}