package fyi.pauli.monopoly.api.board.field.registry

import fyi.pauli.monopoly.api.board.field.ClassifiedField
import fyi.pauli.monopoly.api.board.field.ExactField
import fyi.pauli.monopoly.api.board.field.Field
import org.bukkit.Bukkit
import org.jetbrains.annotations.ApiStatus.Internal

@Internal
internal abstract class SortedFieldList : MutableMap<Int, Field.Container> {

	val boardCirclePosition: MutableMap<Field.Container, Int> = mutableMapOf()

	operator fun plusAssign(container: Field.Container) {
		when (val field = container.field) {
			is ExactField -> {
				val position = field.exactPosition
				boardCirclePosition[container] = position.circle

				if (this[position.number] != null) {
					Bukkit.getLogger().warning {
						"Field [${field.fieldName} : ${field.exactPosition.number}] is already registered."
					}
					return
				}

				this[position.number] = container
			}

			is ClassifiedField -> {
				if (!((field.fieldBefore != null && field.fieldAfter == null) || (field.fieldBefore == null) && field.fieldAfter != null)) {
					Bukkit.getLogger().warning {
						"Tried to register a classified field [${field.fieldName} | ${field.pluginInstance.name}] but the fields before and after are not correctly set. Consider change the type to a normal or exact field or load the addon later."
					}
					return
				}

				if (field.fieldAfter == field.fieldBefore) {
					Bukkit.getLogger().warning {
						"Tried to register a classified field [${field.fieldName} | ${field.pluginInstance.name}] but the field before and after are the same."
					}
					return
				}

				if (!containsFieldType(field.fieldAfter) || !containsFieldType(field.fieldBefore)) {
					Bukkit.getLogger().warning {
						"Tried to register a classified field [${field.fieldName} | ${field.pluginInstance.name}] but neither the field before nor the field after is already registered."
					}
					return
				}


			}

			else -> this[nextPosition] = container

		}
	}

	fun containsFieldType(field: Field?) = values.find { it.field == field } != null

	val nextPosition: Int
		get() = if (this.isEmpty()) 0 else keys.maxOf { it } + 1
}