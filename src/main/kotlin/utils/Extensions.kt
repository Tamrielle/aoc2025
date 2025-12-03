package utils

/** Stringbuilder **/
fun StringBuilder.removeLast(): StringBuilder {
    if (isNotEmpty()) {
        deleteCharAt(length - 1)
    }
    return this
}