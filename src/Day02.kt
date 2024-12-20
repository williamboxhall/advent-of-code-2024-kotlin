fun main() {
    fun part1(input: List<String>): Int {
        val split = input.map { it.split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() } }
        val isSafe: List<Boolean> = split.map { numbers ->
            val differences = numbers.windowed(size = 2, step = 1).map { it[0] - it[1] }
            if (differences.count { it == 0 } > 0) {
                false
            } else {
                val containsIncrease = differences.any { it > 0 }
                val containsDecrease = differences.any { it < 0 }
                val containsIncAndDec = containsIncrease && containsDecrease
                if (containsIncAndDec) {
                    false
                } else {
                    val tooBigIncrease = differences.any { Math.abs(it) > 3 }
                    !tooBigIncrease
                }
            }
        }
        return isSafe.count { it }
    }

    fun isSafe(numbers: List<Int>, oneRemoved: Boolean = false): Boolean {
        val differences = numbers.windowed(size = 2, step = 1).map { it[0] - it[1] }
        val safe = if (differences.count { it == 0 } > 0) {
            false
        } else {
            val containsIncrease = differences.any { it > 0 }
            val containsDecrease = differences.any { it < 0 }
            val containsIncAndDec = containsIncrease && containsDecrease
            if (containsIncAndDec) {
                false
            } else {
                val tooBigIncrease = differences.any { Math.abs(it) > 3 }
                !tooBigIncrease
            }
        }
        return if (!safe) {
            if (!oneRemoved) {
                val variations = (0..numbers.size - 1).map { toRemoveIndex -> numbers.filterIndexed { index, _ -> index != toRemoveIndex } }
                val variationsSafe = variations.map { isSafe(it, oneRemoved = true) }
                variationsSafe.any { it }
            } else {
                false
            }
        } else {
            true
        }
    }

    fun part2(input: List<String>): Int {
        val split = input.map { it.split(" ").filter { it.isNotBlank() }.map { it.trim().toInt() } }
        val isSafe: List<Boolean> = split.map { numbers -> isSafe(numbers) }
        return isSafe.count { it }
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 2)
    check(part2(testInput) == 4)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day02")
    part1(input).println()
    part2(input).println()
}
