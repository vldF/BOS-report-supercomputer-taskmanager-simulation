package cluster

import receiverInitiatedMaxTasks
import receiverInitiatedCreateTaskChance
import receiverInitiatedMaxRequest
import kotlin.random.Random

open class ReceiverInitiatedComputerNode : AbstractComputerNode() {
    override val nodeLoad: Double
        get() = loadedTasks.size * 1.0 / receiverInitiatedMaxTasks * 100

    private val rnd = Random(42)

    override fun run() {
        while (true) {
            mainLoop()
        }
    }

    protected fun mainLoop() {
        var requestCount = 0
        while (loadedTasks.size < receiverInitiatedMaxTasks && requestCount != receiverInitiatedMaxRequest) {
            requestCount++
            val randomNode = cluster.nodes.random(rnd) as ReceiverInitiatedComputerNode
            if (randomNode.loadedTasks.size <= receiverInitiatedMaxTasks) continue
            val randomTask = randomNode.loadedTasks.poll() ?: continue
            loadedTasks.add(randomTask)
            requestCount = 0
        }

        if (loadedTasks.isEmpty()) sleep(30)

        val loadedTasksIter = loadedTasks.iterator()
        for (task in loadedTasksIter) {
            sleep(task.timeMillis)
            task.repeatsToDone--
            if (task.repeatsToDone <= 0) {
                loadedTasksIter.remove()
            }

            if (rnd.nextDouble() <= receiverInitiatedCreateTaskChance) {
                val randomTask = Task.generateTask()
                loadedTasks.add(randomTask)
            }
        }
    }
}