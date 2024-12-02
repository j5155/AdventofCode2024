import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("1.1.txt").readLines()
    val left = ArrayList<Int>()
    val right = ArrayList<Int>()

    for (line in input) {
        val lineparts = line.split("   ")
        left.add(lineparts[0].toInt())
        right.add(lineparts[1].toInt())
    }

    left.sort()
    right.sort()

    var totalDistance = 0

    left.zip(right).forEach {
        totalDistance += abs(it.first - it.second)
    }
    println(totalDistance)

    var similarityScore = 0
    left.forEach { a ->
        similarityScore += a * right.stream().filter { it == a }.count().toInt()
    }
    println(similarityScore)
}