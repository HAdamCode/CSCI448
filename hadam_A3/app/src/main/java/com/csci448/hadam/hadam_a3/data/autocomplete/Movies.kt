package com.csci448.hadam.hadam_a3.data.autocomplete

data class Movies(
    val link: Link,
    val id: String,
    val movie: String,
    val type: String,
    val rank: Int,
    val starts: String,
    val clips: List<Clip>,
    val vt: Int,
    val year: Int,
    val yearRange: String
)
