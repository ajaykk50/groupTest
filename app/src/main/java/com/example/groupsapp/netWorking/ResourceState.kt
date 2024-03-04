package com.example.groupsapp.netWorking

/**
 * Data and UI State management
 */
sealed class ResourceState<T> {


  class Loading<T> : ResourceState<T>()

  class Idle<T> : ResourceState<T>()

  data class Success<T>(val data: T) : ResourceState<T>()

  data class Error<T>(val message: String, val data: String, val error: String?, val code: Int?,
  val throwable:Throwable?) :
    ResourceState<T>()

  companion object {

    /**
     * Returns [ResourceState.Loading] instance.
     */
    fun <T> loading() =
      Loading<T>()

    /**
     * Returns [ResourceState.Success] instance.
     * @param data Data to emit with status.
     */
    fun <T> success(data: T) =
      Success(data)

    /**
     * Returns [ResourceState.Error] instance.
     * @param message Description of failure.
     */
    fun <T> error(message: String, data: String, error: String?,code:Int?,throwable:Throwable?) =
      Error<T>(
        message,
        data,
        error,
        code,
        throwable
      )
  }

}