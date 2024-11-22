package models

data class Course(var courseId: Int,
             var isCourseOpen: Boolean,
             var languageTaught: Char,
             var undergraduate: MutableSet<Student> = mutableSetOf()){

    private var finalStudentId = 0
    private fun getNextStudentId() = finalStudentId++

    fun addStudent(student: Student): Boolean {
        student.studentNo = getNextStudentId()
        return undergraduate.add(student)
    }

    fun numberOfUndergraduate() = undergraduate.size

    fun findStudent(id: Int): Student? {
        return undergraduate.find{student -> student.studentNo == id}
    }

    fun delete(id: Int): Boolean {
        return undergraduate.removeIf{student -> student.studentNo == id}
    }

    fun update(id: Int, newStudent: Student) : Boolean {
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

fun listItems

   // override fun toString
}
//list of students

