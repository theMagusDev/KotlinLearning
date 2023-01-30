package kotlinInDepth.specialCaseClasses.enumClass

// Just like values of any other type, enum variables may be compared against
// particular values using a when expression. There is, however, an additional
// benefit when using enums; you can omit an else branch if the 'when'
// expression is exhaustive, i.e., it contains branches for all possible values
// of an enum type.

enum class Direction {
    NORTH, SOUTH, WEST, EAST
}
fun rotateClockWise(direction: Direction) = when (direction) {
    Direction.NORTH -> Direction.EAST
    Direction.EAST -> Direction.SOUTH
    Direction.SOUTH -> Direction.WEST
    Direction.WEST -> Direction.NORTH
}

// The exhaustive form of the 'when' expression decreases the chance of writing
// a code which may break on a context change like adding a new enum value.
// Suppose that we’ve added an else branch instead:
fun rotateClockWiseNew(direction: Direction) = when (direction) {
    Direction.NORTH -> Direction.EAST
    Direction.EAST -> Direction.SOUTH
    Direction.SOUTH -> Direction.WEST
    Direction.WEST -> Direction.NORTH
    else -> throw IllegalArgumentException("Invalid direction: $direction")
}

// This code works fine until we add new values for the DirectionNew enum:
enum class DirectionNew {
    NORTH, SOUTH, WEST, EAST,
    NORTH_EAST, NORTH_WEST, SOUTH_EAST, SOUTH_WEST
}
// Now, a call like rotateClockWise(Direction.NORTH_EAST) will throw an
// exception. If we, however, use an else free form, an error can be captured
// at compile-time as the compiler can complain about the non-exhaustive
// when expression in the rotateClockWise() body.
/*
fun rotateClockWiseNew2(direction: DirectionNew) = when (direction) {

// Error: 'when' expression must be exhaustive, add necessary 'NORTH_EAST',
'NORTH_WEST', 'SOUTH_EAST', 'SOUTH_WEST' branches or 'else' branch instead.

    DirectionNew.NORTH -> DirectionNew.EAST
    DirectionNew.EAST -> DirectionNew.SOUTH
    DirectionNew.SOUTH -> DirectionNew.WEST
    DirectionNew.WEST -> DirectionNew.NORTH
}
 */