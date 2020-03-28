package galstyan.hayk.typing.model


interface TextMatcherFactory {

	fun create(text: String): TextMatcher
}