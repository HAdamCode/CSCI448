package com.csci448.hadam.hadam_a3.data.titlevideo

import com.csci448.hadam.hadam_a3.data.autocomplete.Link

data class Resource(
    val type: String,
    val id: String,
    val image: Link,
    val title: String,
    val titleType: String,
    val year: Int,
    val size: Int,
    val videoCounts: List<VideoCount>,
    val videos: List<Video>
    )
