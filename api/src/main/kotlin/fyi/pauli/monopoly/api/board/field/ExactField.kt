package fyi.pauli.monopoly.api.board.field

import fyi.pauli.monopoly.api.board.Position

interface ExactField : Field {

	val exactPosition: Position

}