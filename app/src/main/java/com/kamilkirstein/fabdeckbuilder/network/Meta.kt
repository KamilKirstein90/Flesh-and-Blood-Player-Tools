package com.kamilkirstein.fabdeckbuilder.network

data class Meta(
    val current_page: Integer?,
    val from: Integer?,
    val last_page: Integer?,
    val links: List<Link>?,
    val path: String?,
    val per_page: Integer?,
    val to: Integer?,
    val total: Integer?
)