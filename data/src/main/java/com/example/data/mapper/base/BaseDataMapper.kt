package com.example.data.mapper.base

abstract class BaseDataMapper<DomainModel, DataModel> :
    DataMapperInterface<DomainModel, DataModel> {
    fun mapListToEntity(data: List<DataModel>?): List<DomainModel> {
        return data?.map { mapToEntity(it) }?.toList() ?: listOf()
    }
}