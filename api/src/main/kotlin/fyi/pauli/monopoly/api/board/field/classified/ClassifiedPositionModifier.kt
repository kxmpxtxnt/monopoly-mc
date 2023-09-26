package fyi.pauli.monopoly.api.board.field.classified

import fyi.pauli.monopoly.api.board.field.Field

interface ClassifiedPositionModifier {

	fun withBefore(): WithBefore
	fun withAfter(): WithAfter

	interface WithBefore : ClassifiedPositionModifier {
		val before: Field
	}

	interface WithAfter : ClassifiedPositionModifier {
		val after: Field
	}

	companion object {
		fun builder(): ClassifiedPositionModifier = object : ClassifiedPositionModifier {
			override fun withBefore(): WithBefore = object : WithBefore {

			}

			override fun withAfter(): WithAfter {

			}

		}
	}
}



fun main() {

}