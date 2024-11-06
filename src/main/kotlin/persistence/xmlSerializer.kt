package persistence

import java.io.File
import kotlin.Throws
import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.io.xml.DomDriver
import models.Student
import models.Course
import java.io.FileReader
import java.io.FileWriter
import java.lang.Exception

class XMLSerializer(private val file: File) : serializer {

    @Throws(Exception::class)
    override fun read(): Any {
        val xStream = XStream(DomDriver())
        xStream.allowTypes(arrayOf(Student::class.java))
        xStream.allowTypes(arrayOf(Course::class.java))
        val inputStream = xStream.createObjectInputStream(FileReader(file))
        val obj = inputStream.readObject() as Any
        inputStream.close()
        return obj
    }

    @Throws(Exception::class)
    override fun write(obj: Any?) {
        val xStream = XStream(DomDriver())
        val outputStream = xStream.createObjectOutputStream(FileWriter(file))
        outputStream.writeObject(obj)
        outputStream.close()
    }
}