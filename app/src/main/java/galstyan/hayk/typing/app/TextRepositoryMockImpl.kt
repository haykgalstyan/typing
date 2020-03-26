package galstyan.hayk.typing.app

import galstyan.hayk.typing.repository.TextRepository


class TextRepositoryMockImpl : TextRepository() {

	override suspend fun getText() =
		"""
            This picture is a colorized scanning electron micrograph of Ebola virus particles (green),
            visible both as extracellular particles and budding particles from a chronically infected
            African green monkey kidney cell (blue), at 20,000× magnification.
            This image was taken in a biosafety level 4 facility, the highest level of biosafety precautions,
            which is used for easily transmissible agents that cause severe to fatal disease in
            humans for which there are no available vaccines or treatments.
        """.trimIndent()
}