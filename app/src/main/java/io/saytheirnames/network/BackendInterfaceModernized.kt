package io.saytheirnames.network

import io.saytheirnames.models.DonationData
import io.saytheirnames.models.DonationTypesData
import io.saytheirnames.models.DonationsData
import io.saytheirnames.models.PeopleData
import io.saytheirnames.models.PersonData
import io.saytheirnames.models.PetitionData
import io.saytheirnames.models.PetitionsData
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BackendInterface {
    
    @GET("/api/people")
    suspend fun getPeople(@Query("page") page: Int): PeopleData

    @GET("/api/people/{id}")
    suspend fun getPeopleById(@Path("id") id: String): PersonData

    @GET("/api/petitions")
    suspend fun getPetitions(@Query("page") page: Int): PetitionsData

    @GET("/api/petitions/{id}")
    suspend fun getPetitionsById(@Path("id") identifier: String): PetitionData

    @GET("/api/donations")
    suspend fun getDonations(@Query("page") page: Int): DonationsData

    @GET("/api/donations/{identifier}")
    suspend fun getDonationsById(@Path("identifier") identifier: String): DonationData

    @GET("/api/donation-types")
    suspend fun getDonationsTypes(): DonationTypesData

    @GET("/api/donations")
    suspend fun getFilteredDonations(@Query("type") type: String): DonationsData
} 