package cluster

import kotlin.random.Random

data class Task(
    val timeMillis: Long,
    var repeatsToDone: Int
) {
    companion object {
        private val rnd = Random(42)

        fun generateTask(): Task {
            return Task(rnd.nextLong(100, 500), rnd.nextInt(0, 10))
        }
    }
}
