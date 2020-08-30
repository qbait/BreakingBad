package io.digitalheart.breakingbad.models

import com.squareup.moshi.Json

data class Character(
    val appearance: List<Int> = emptyList(),
    @Json(name = "better_call_saul_appearance") val betterCallSaulAppearance: List<Int> = emptyList(),
    val birthday: String = "",
    val category: String = "",
    @Json(name = "char_id") val id: Int = 0,
    @Json(name = "img") val imageUrl: String = "",
    val name: String = "",
    val nickname: String = "",
    val occupation: List<String> = emptyList(),
    val portrayed: String = "",
    val status: String = ""
)