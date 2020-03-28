package galstyan.hayk.typing.app

import galstyan.hayk.typing.model.TextMatcher


class TextMatcherImpl(text: CharSequence) : TextMatcher(text) {

	override fun match(input: CharSequence): Int {
		var m = 0
		if (input.isNotEmpty())
			while (m < input.length && text[m] == input[m]) m++

		return m
	}
}