package models

data class Student( var studentNo: Int,
                    var firstName: String,
                    var lastName: String,
                    var dateOfBirth: String,
                    var isEnrolled: Boolean = false,
                    var courseHours: Double){
    override fun toString() =
        if (isEnrolled)
            "${studentNo}: ${firstName} ${lastName} (Is Enrolled)"
        else
            "${studentNo}: ${firstName} ${lastName} (Disenrolled)"
}