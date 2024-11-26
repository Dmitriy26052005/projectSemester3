package utils

import models.Student

fun formatListString(courseToFormat: MutableList<Student>): String =
    courseToFormat
        .joinToString("\n") {course -> "$course"}

fun formatSetString(studentsToFormat: Set<Student>): String =
    studentsToFormat
        .joinToString("\n") {student -> "$student"}