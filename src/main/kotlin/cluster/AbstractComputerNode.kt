package cluster

import createTaskChance
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.random.Random

abstract class AbstractComputerNode() : Thread() {
    val loadedTasks = ConcurrentLinkedQueue<Task>()
    lateinit var cluster: Cluster
    abstract val nodeLoad: Double

    protected val rnd = Random(42)

    protected fun doTasks() {
        val loadedTasksIter = loadedTasks.iterator()
        for (task in loadedTasksIter) {
            sleep(task.timeMillis)
            task.repeatsToDone--
            if (task.repeatsToDone <= 0) {
                loadedTasksIter.remove()
            }

            if (rnd.nextDouble() <= createTaskChance) {
                val randomTask = Task.generateTask()
                loadedTasks.add(randomTask)
            }
        }
    }
}