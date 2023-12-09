package com.example.data.source.remote.response

abstract class BaseListData<T>(
    var page: Int? = 0,
    var results: List<T>?,
    var total_pages: Int? = 0,
    var total_results: Int? = 0
)