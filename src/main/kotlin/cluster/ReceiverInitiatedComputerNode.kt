package cluster

import maxRequestCount
import maxTasks

open class ReceiverInitiatedComputerNode : AbstractComputerNode() {
    override val nodeLoad: Double
        get() = loadedTasks.size * 1.0 / maxTasks * 100

    override fun run() {
        while (true) {
            mainLoop()
        }
    }

    protected fun mainLoop() {
        var requestCount = 0
        while (loadedTasks.size < maxTasks && requestCount != maxRequestCount) {
            requestCount++
            val randomNode = cluster.nodes.random(rnd) as ReceiverInitiatedComputerNode
            if (randomNode.loadedTasks.size <= maxTasks) continue
            val randomTask = randomNode.loadedTasks.poll() ?: continue
            loadedTasks.add(randomTask)
            requestCount = 0
        }

        if (loadedTasks.isEmpty()) sleep(30)
        doTasks()
    }
}