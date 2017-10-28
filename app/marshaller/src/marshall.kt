import java.io.StringWriter
import javax.xml.bind.JAXBContext
import javax.xml.bind.Marshaller
import javax.xml.bind.annotation.XmlElement
import javax.xml.bind.annotation.XmlRootElement
import java.io.FileOutputStream
import java.io.OutputStreamWriter
import javax.xml.bind.annotation.XmlAttribute
import javax.xml.bind.annotation.XmlType


@XmlRootElement
//@XmlType(name = "", propOrder = arrayOf("name","apellido"))
class Book(@XmlElement var name: String, @XmlElement var apellido: String) {
    constructor() : this("","")

    @XmlAttribute
    private var id = ""


    fun setId(id : String) {
        this.id = id
    }
}

@XmlRootElement
class Library {

    @XmlAttribute
    private var id = ""


    fun setId(id : String) {
        this.id = id
    }

    @XmlAttribute
    private var version = ""


    fun setVersion(version : String) {
        this.version = version
    }

    @XmlElement
    private var books: MutableCollection<Book> = mutableListOf()

    fun setBooks(book: Book){
        this.books.add(book)
    }
}

fun main(args: Array<String>) {
    val book = Book()

    book.setId("jl")
    book.name = "Jorge"
    book.apellido = "Quiguango"

    val library = Library()

    library.setId("2")
    library.setVersion("2.1")
    library.setBooks(book)


    val jaxbContext = JAXBContext.newInstance(Library::class.java)
    val marshaller = jaxbContext.createMarshaller()
    marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true)
    marshaller.setProperty("jaxb.encoding", "UTF-8")

    val stringWriter = StringWriter()
    stringWriter.use {
        marshaller.marshal(library, stringWriter)
    }

    val out = OutputStreamWriter(FileOutputStream("1234.xml"), "UTF-8")
    marshaller.marshal(library, out)

    println(stringWriter)
}