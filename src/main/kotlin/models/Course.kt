package models

data class Course(
    var id: Int,
    var courseName: String,
    var isCourseOpen: String,
    var languageTaught: Char)
/*{


    override fun toString(): String {
        val open = if (isCourseOpen) 'y' else 'n'
        return "$courseId: Course Tiele: ($courseName), Course Status: ($open), Language Taught: ($languageTaught) \n${listStudents()}"
    }


    fun checkStudentEnrollStatus(): Boolean {
        if (undergraduate.isEmpty()) {
            for (student in undergraduate)
                if (!student.isEnrolled) {
                    return false
                }
        }
        return true
    }
}*/
