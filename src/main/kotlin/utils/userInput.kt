package utils

fun readIntNotNull() = readlnOrNull()?.toIntOrNull() ?: -1

fun readNextInt(prompt: String?): Int {
    do {
        try {
            print(prompt)
            return readln().toInt()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a number please.")
        }
    } while (true)
}

fun readNextDouble(prompt: String?): Double {
    do {
        try {
            print(prompt)
            return readln().toDouble()
        } catch (e: NumberFormatException) {
            System.err.println("\tEnter a number please.")
        }
    } while (true)
}

fun readNextFloat(prompt: String?): Float {
    do{
        try{
            print(prompt)
            return readln().toFloat()
        } catch (e: NumberFormatException){
            System.err.println("\tEnter a decimal  please.")
        }
    }while (true)
}

fun readNextLine(prompt: String): String {
    print(prompt)
    try {
        print(prompt)
        return readln().toString()
    } catch (e: NumberFormatException) {
        System.err.println("\tEnter a string please.")
    }
    return readln()
}

fun readNextChar(prompt: String?): Char {
    do{
        try{
            print(prompt)
            return readln().first()
        }catch (e: NumberFormatException) {
            System.err.println("\tEnter a character please.")
        }
    }while (true)
}