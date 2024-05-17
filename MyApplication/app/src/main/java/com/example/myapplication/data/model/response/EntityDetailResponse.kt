package com.example.myapplication.data.model.response

import com.squareup.moshi.Json

data class EntityDetailResponse(
    @Json(name = "entity") val entity: Entity? = null,
    @Json(name = "setting") val setting: Setting? = null,
)
data class Entity(
    @Json(name = "firstName") val firstName: String? = null,
    @Json(name = "lastName") val lastName: String? = null,
    @Json(name = "email") val email: String? = null,
    @Json(name = "countryPhoneCode") val countryPhoneCode: String? = null,
    @Json(name = "phone") val phone: String? = null,
    @Json(name = "countryId") val countryId: String? = null,
    @Json(name = "countryCode") val countryCode: String? = null,
    @Json(name = "credit") val credit: Int? = null,
    @Json(name = "status") val status: Int? = null,
    @Json(name = "rate") val rate: Int? = null,
    @Json(name = "rateCount") val rateCount: Int? = null,
    @Json(name = "reward") val reward: Int? = null,
    @Json(name = "uniqueId") val uniqueId: Int? = null,
    @Json(name = "documentStatus") val documentStatus: Int? = null,
    @Json(name = "referralDetail") val referralDetail: ReferralDetail? = null,
    @Json(name = "imageUrl") val imageUrl: String? = null,
    @Json(name = "preferedLanguage") val preferredLanguage: String? = null,
)

data class ReferralDetail(
    @Json(name = "referralCode") val referralCode: String? = null,
    @Json(name = "referralUse") val referralUse: Int? = null,
)