package kotlinInDepth.specialCaseClasses.enumClass

// We can avoid explicit qualification of enum constants by importing them
// at the beginning of the containing file:

import kotlinInDepth.specialCaseClasses.enumClass.DirectionShortened.*

enum class DirectionShortened {
    NORTH, SOUTH, WEST, EAST
}

fun rotateClockWiseShortened(direction: DirectionShortened) = when (direction) {
    NORTH -> EAST
    EAST -> SOUTH
    SOUTH -> WEST
    WEST -> NORTH
}