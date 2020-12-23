package com.deazzle.modules.apirepository.repositories.model.user.request

data class Info(
    val page: Int,
    val results: Int,
    val seed: String,
    val version: String
)