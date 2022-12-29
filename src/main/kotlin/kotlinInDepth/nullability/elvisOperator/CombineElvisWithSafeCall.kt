package kotlinInDepth.nullability.elvisOperator

// The Elvis operator is useful in combination with safe calls
// to substitute a default value when the receiver is null.
// In the following code, we substitute
// a zero when the program’s standard input is empty:
val n = readLine()?.toInt() ?: 0