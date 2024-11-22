package models

data class Student( var studentNo: Int,
                    var firstName: String,
                    var lastName: String,
                    var dateOfBirth: String,
                    var isEnrolled: Boolean = false,
                    var courseHours: Double){
    //override fun toString() =
    //    if (isItemComplete)
      //      "${itemId}: ${itemContents} (Complete)"
        //else
          //  "${itemId}: ${itemContents} (TODO)"
}