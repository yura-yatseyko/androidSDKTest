package ai.purple.network.model

import kotlinx.serialization.Serializable

/**
 * Network representation of [Location]
 */
@Serializable
data class Location(
    val id: String,
    val name: String = "",
    val shortDescription: String = "",
    val longDescription: String = "",
    val url: String = "",
    val imageUrl: String = "",
    val followed: Boolean = false,
)
