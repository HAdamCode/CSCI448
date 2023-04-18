package com.csci448.hadam.hadam_a3.data.titlevideo

import com.csci448.hadam.hadam_a3.data.autocomplete.Link

data class Video(
    val audioLanguage: String,
    val contentType: String,
    val description: String?,
    val durationInSeconds: Int,
    val id: String,
    val image: Link,
    val parentTitle: ParentTitle,
    val primaryTitle: PrimaryTitle,
    val title: String
)
