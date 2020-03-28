package galstyan.hayk.typing.app

import galstyan.hayk.typing.model.TextMatcher
import galstyan.hayk.typing.model.TextMatcherFactory


class TextMatcherFactoryImpl : TextMatcherFactory {

	override fun create(text: String): TextMatcher {
		return TextMatcherImpl(text)
	}
}