package fyi.pauli.monopoly.board.generate

enum class GenerationSide(
	val fields: IntRange
) {

	FIRST(0..9),
	SECOND(10..19),
	THIRD(20..29),
	FORTH(30..39)
	;

	val side: Lazy<Int> = lazy {
		when (fields) {
			0..9 -> 0
			10..19 -> 1
			20..29 -> 2
			else -> 3
		}
	}

	companion object {
		fun ofPosition(position: Int) = entries.find { it.fields.contains(position) }
	}
}