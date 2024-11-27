package models

data class Course(
    var id: Int,
    var courseName: String,
    var isCourseOpen: Boolean = false,
    var languageTaught: Char)
/*{

    private var finalStudentId = 0
    private fun getNextStudentId() = finalStudentId++

    fun addStudentToCourse(student: Student): Boolean {
        student.studentNo = getNextStudentId()
        return undergraduate.add(student)
    }

    fun numberOfUndergraduate() = undergraduate.size

    fun findStudent(id: Int): Student? {
        return undergraduate.find { student -> student.studentNo == id }
    }

    fun delete(id: Int): Boolean {
        return undergraduate.removeIf { student -> student.studentNo == id }
    }

    fun update(id: Int, newStudent: Student): Boolean {
        val foundStudent = findStudent(id)

        if (foundStudent != null) {
            foundStudent.studentNo = newStudent.studentNo
            foundStudent.firstName = newStudent.firstName
            foundStudent.lastName = newStudent.lastName
            foundStudent.dateOfBirth = newStudent.dateOfBirth
            foundStudent.isEnrolled = newStudent.isEnrolled
            foundStudent.courseHours = newStudent.courseHours

            return true
        }
        return false
    }

    fun listStudents() = if (undergraduate.isEmpty()) "No Students on the Course" else formatListString(undergraduate)

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
