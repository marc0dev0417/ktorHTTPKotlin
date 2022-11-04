import com.google.gson.Gson
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.server.plugins.contentnegotiation.*


suspend fun main(){
    val client = HttpClient(CIO){
        install(ContentNegotiation.toString()){
            Gson()
        }
    }
    val gson = Gson()
    val response: HttpResponse = client.get("https://jsonplaceholder.typicode.com/users")
    val personList: Array<Person> = gson.fromJson(response.bodyAsText(), Array<Person>::class.java)

    personList.forEach { person -> println("The id ${person.id} of ${person.name}")}
}