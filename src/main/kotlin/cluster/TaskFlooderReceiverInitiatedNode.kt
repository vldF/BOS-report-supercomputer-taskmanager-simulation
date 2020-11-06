package cluster

class TaskFlooderReceiverInitiatedNode : ReceiverInitiatedComputerNode() {
    private var remainingTasksCount = 1000
    override fun run() {
        while (true) {
            if (remainingTasksCount >= 10) {
                for (i in 0..10) {
                    val newTask = Task.generateTask()
                    loadedTasks.add(newTask)
                }
                remainingTasksCount -= 10
            }
            //sleep(1000000)
            mainLoop()
        }
    }
}