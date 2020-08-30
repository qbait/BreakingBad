package io.digitalheart.breakingbad.network

import io.digitalheart.breakingbad.models.Character
import io.reactivex.Single
import retrofit2.http.GET

interface BreakingBadService {
    @GET("api/characters")
    fun characters(): Single<List<Character>>
}