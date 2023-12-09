package com.example.data.mapper.base

interface DataMapperInterface<DomainModel, DataModel> {
    fun mapToEntity(model: DataModel): DomainModel
}