package galstyan.hayk.typing.model


abstract class TextMatcher(protected val text: String) {

	/**
	 * @return index of the matching [input] end in [text]
	 */
	abstract fun match(input: String): Int

}