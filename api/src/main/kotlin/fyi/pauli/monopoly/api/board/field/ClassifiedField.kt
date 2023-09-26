package fyi.pauli.monopoly.api.board.field

import fyi.pauli.monopoly.api.board.field.classified.ClassifiedPositionModifier

interface ClassifiedField : Field {

	val modifier: ClassifiedPositionModifier

}

