package galstyan.hayk.typing.repository


abstract class TextRepository {

    abstract suspend fun getText(): String
}