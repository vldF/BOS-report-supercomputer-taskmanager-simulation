package cluster

class TaskFlooderSenderInitiatedNode : SenderInitiatedComputerNode() {
    private var tasksCount = 100

    override fun run() {
        while (true) {
            if (tasksCount >= 10) {
                for (i in 0 until 10) {
                    loadedTasks.add(Task.generateTask())
                }
                tasksCount -= 10
            }
            mainLoop()
        }
    }
}