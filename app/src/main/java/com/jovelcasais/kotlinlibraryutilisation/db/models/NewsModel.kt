package com.jovelcasais.kotlinlibraryutilisation.db.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Entity(tableName = "NewsModel")
@Serializable
data class NewsModel(
    @PrimaryKey val uuid: String = "",
    val categories: List<String> = emptyList(),
    val description: String = "",
    @SerializedName("image_url") val imageUrl: String? = "",
    val keywords: String = "",
    val language: String = "",
    val locale: String = "",
    @SerializedName("published_at") val publishedAt: String? = "",
    @SerializedName("relevance_score") val relevanceScore: String? = "",
    val snippet: String = "",
    val source: String = "",
    val title: String = "",
    val url: String = ""
)