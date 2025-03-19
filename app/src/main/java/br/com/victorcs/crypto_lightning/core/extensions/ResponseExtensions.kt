package br.com.victorcs.crypto_lightning.core.extensions

import br.com.victorcs.crypto_lightning.core.constants.UNKNOWN_ERROR
import br.com.victorcs.crypto_lightning.domain.model.Response

fun <T> List<T>.asSuccessResponse(): Response<List<T>> = Response.Success(this)

fun <T> Throwable.asFailureResponse(): Response<T> =
    Response.Failure(this.message ?: UNKNOWN_ERROR)

inline fun <T> Response<T>.onSuccess(action: (T) -> Unit): Response<T> {
    if (this is Response.Success) {
        action(this.data)
    }
    return this
}

inline fun <T> Response<T>.onFailure(action: (String) -> Unit): Response<T> {
    if (this is Response.Failure) {
        action(this.errorMessage)
    }
    return this
}