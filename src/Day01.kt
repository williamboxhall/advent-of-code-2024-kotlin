fun main() {
    fun part1(input: List<String>): Int {
        val split = input.map { it.split("   ").map { it.trim().toInt() } }
        val first = split.map { it.first() }.sorted()
        val second = split.map { it.last() }.sorted()
        val zipped = first.zip(second)
        val diffed = zipped.map { Math.abs(it.second - it.first) }
        val summed = diffed.sum()
        return summed
    }

    fun part2(input: List<String>): Int {
        val split = input.map { it.split("   ").map { it.trim().toInt() } }
        val first = split.map { it.first() }.sorted()
        val second = split.map { it.last() }.sorted()

        val secondIncidence = second.groupingBy { it }.eachCount()
        val scores = first.map { it * (secondIncidence[it]?:0) }
        val summed = scores.sum()

        return summed
    }

    // Or read a large test input from the `src/Day01_test.txt` file:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 11)
    check(part2(testInput) == 31)

    // Read the input from the `src/Day01.txt` file.
    val input = readInput("Day01")
    part1(input).println()
    part2(input).println()
}
