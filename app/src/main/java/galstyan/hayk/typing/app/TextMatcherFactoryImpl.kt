package galstyan.hayk.typing.app

import galstyan.hayk.typing.model.TextMatcher
import galstyan.hayk.typing.model.TextMatcherFactory


class TextMatcherFactoryImpl : TextMatcherFactory {

	override fun create(text: CharSequence): TextMatcher {
		return TextMatcherImpl(text)
	}
}