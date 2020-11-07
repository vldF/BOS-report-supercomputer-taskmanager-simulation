package cluster

import maxTasks
import maxRequestCount

open class SenderInitiatedComputerNode : AbstractComputerNode() {
    override val nodeLoad: Double
        get() = TODO("Not yet implemented")

    override fun run() {
        while (true) {
            mainLoop()
        }
    }

    protected fun mainLoop() {
        doTasks()
        var requestCount = 0
        while (loadedTasks.size > maxTasks && requestCount < maxRequestCount) {
            requestCount++
            val randomNode = cluster.nodes.random(rnd) as SenderInitiatedComputerNode
            if (randomNode.loadedTasks.size >= maxTasks) continue
            randomNode.loadedTasks.add(loadedTasks.poll())
            requestCount = 0
        }
    }

}