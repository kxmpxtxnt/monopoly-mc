package fyi.pauli.monopoly.util

import net.kyori.adventure.text.minimessage.MiniMessage
import java.util.*

fun capitalizeString(str: String): String {
	return str.trim().split("\\s+".toRegex()).joinToString(" ") { string ->
		string.replaceFirstChar {
			if (it.isLowerCase()) it.titlecase(
				Locale.getDefault()
			) else it.toString()
		}
	}
}

fun String.minimessage() = MiniMessage.miniMessage().deserialize(this)