package com.example.domain.usecase.base

interface BaseUseCase<R> {
    suspend operator fun invoke(): R
}

interface BaseUseCaseWithRequest<T, R> {
    suspend operator fun invoke(param: T): R
}

interface BaseUseCaseWithoutResponse<T> {
    suspend operator fun invoke(param: T)
}