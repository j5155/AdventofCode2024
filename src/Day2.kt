import java.io.File
import kotlin.math.abs

fun main() {
    val input = File("2.txt").readLines()

    val sequences: MutableList<MutableList<Int>> = mutableListOf()

    input.forEach { line ->
        val charList = line.split(" ")
        val intList: MutableList<Int> = mutableListOf()
        charList.forEach {
            intList.add(it.toInt())
        }
        sequences.add(intList)
    }

    var safeReports = 0L
    sequences.forEach {
        if (isSafe(it)) {
            safeReports += 1
        }
    }

    println(safeReports) // right

    var safeReportsDampened = 0L
    sequences.forEach {
        if (isSafeDampened(it)) {
            safeReportsDampened += 1
        }
    }

    println(safeReportsDampened) // WRONG


    /*
    println(isSafeDampened(listOf(1,1,2,3,4)))
    println(isSafeDampened(listOf(1,2,2,3,4)))
    println(isSafeDampened(listOf(4,3,4,2,1)))
    println(isSafeDampened(listOf(4,3,5,6,7)))
    println(isSafeDampened(listOf(1,1,1,1,1,1,1,1,1)))

     */


    println(isSafeDampened(listOf(7,6,4,2,1))) // true
    println(isSafeDampened(listOf(1,2,7,8,9))) // false
    println(isSafeDampened(listOf(9,7,6,2,1))) // false
    println(isSafeDampened(listOf(1,3,2,4,5))) // true
    println(isSafeDampened(listOf(8,6,4,4,1))) // true
    println(isSafeDampened(listOf(1,3,6,7,9))) // true
}

fun isSafeDampened(sequence: List<Int>) = isSafe(sequence, true)

fun isSafe(sequence: List<Int>, problemDampener: Boolean = false): Boolean {
    var problemDampened = !problemDampener

    var shouldBeIncreasing = sequence[0] < sequence[1]
    for ((index, num) in sequence.withIndex()) {
        if (index + 1 == sequence.size) {
            return true
        }
        val next = sequence[index + 1]

       if (!check(num, next, shouldBeIncreasing)) {
           if (!problemDampener) {
               return false
           }
           if (isSafe(sequence.slice(0..<index) + sequence.slice(index+1..<sequence.size))) {
               return true
           } else if (
               index+2 == sequence.size || // next is last one, so remove it and done
               isSafe(sequence.slice(0..index) + sequence.slice(index+2..<sequence.size))
           ) {
               return true
           } else {
               return false
           }
       }
        /*
           if (problemDampened) {
               return false
           }
           problemDampened = true
           if (index == 0) { // skip the first one, so continue normally
               shouldBeIncreasing = sequence[1] < sequence[2]
               continue
           }
           if (check(sequence[index - 1], next, shouldBeIncreasing)) { // try skipping the current one
               continue
           }
           if (check(sequence[index - 1], next, !shouldBeIncreasing)) { // try skipping the current one but other way
               shouldBeIncreasing = !shouldBeIncreasing
               continue
           }
           // if that doesn't work see if we can skip next one
           if (index + 2 == sequence.size) { // nothing to check after skipping this one, so the sequence is good
               return true
           }

           if (check(num, sequence[index + 2],shouldBeIncreasing)) { // try skipping next one
               problemJustDampened = true
               continue
           }
           if (check(num, sequence[index + 2],!shouldBeIncreasing)) { // try skipping next one but other way
               problemJustDampened = true
               shouldBeIncreasing = !shouldBeIncreasing
               continue
           }

           // nothing worked so
           return false

         */
    }
    return TODO()
}

// true is good, false is bad
fun check(num1: Int, num2: Int, shouldBeIncreasing: Boolean): Boolean {
    val increasing = num1 < num2
    if (increasing != shouldBeIncreasing) {
        return false
    }

    val difference = abs(num1 - num2)
    if (difference < 1 || difference > 3) {
        return false
    }

    // else
    return true
}