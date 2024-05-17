package com.example.myapplication.data.model.response

import com.squareup.moshi.Json

data class Setting(
    @Json(name = "appVersionDetail") val appVersionDetail: AppVersionDetail? = null,
    @Json(name = "appForceUpdate") val appForceUpdate: AppForceUpdate? = null,
    @Json(name = "contactDetail") val contactDetail: ContactDetail? = null,
    @Json(name = "passwordRule") val passwordRule: PasswordRule? = null,
    @Json(name = "referralConfig") val referralConfig: ReferralConfig? = null,
    @Json(name = "phoneNumberValidation") val phoneNumberValidation: PhoneNumberValidation? = null,
    @Json(name = "termsAndConditionsURL") val termsAndConditionsURL: String? = null,
    @Json(name = "privacyPolicyURL") val privacyPolicyURL: String? = null,
    @Json(name = "entitySetting") val entitySetting: EntitySetting? = null,
    @Json(name = "themeSetting") val themeSetting: ThemeSetting? = null,
    @Json(name = "mapType") val mapType: Int? = null,
    @Json(name = "mapThemeSetting") val mapThemeSetting: MapThemeSetting? = null,
)
data class PasswordRule(
    @Json(name = "minLength") val minLength: Int? = null,
    @Json(name = "requireNumbers") val requireNumbers: Boolean? = null,
    @Json(name = "requireSpecial") val requireSpecial: Boolean? = null,
    @Json(name = "requireUppercase") val requireUppercase: Boolean? = null,
    @Json(name = "requireLowercase") val requireLowercase: Boolean? = null,
    @Json(name = "regEx") val regEx: String? = null
)
data class ReferralConfig(
    @Json(name = "isActive") val isActive: Boolean? = null,
    @Json(name = "referrerReward") val referrerReward: Double? = null,
    @Json(name = "signupReward") val signupReward: Double? = null,
    @Json(name = "maxReferralLimit") val maxReferralLimit: Int? = null,
)
data class ThemeSetting(
    @Json(name = "lightMode") val lightMode: ModeColors? = null,
    @Json(name = "darkMode") val darkMode: ModeColors? = null,
)
data class AppVersionDetail(
    @Json(name = "androidCustomer") val androidCustomer: String? = null,
    @Json(name = "androidDriver") val androidDriver: String? = null,
    @Json(name = "iosCustomer") val iosCustomer: String? = null,
    @Json(name = "iosDriver") val iosDriver: String? = null,
)
data class AppForceUpdate(
    @Json(name = "androidCustomer") val androidCustomer: Boolean? = null,
    @Json(name = "androidDriver") val androidDriver: Boolean? = null,
    @Json(name = "iosCustomer") val iosCustomer: Boolean? = null,
    @Json(name = "iosDriver") val iosDriver: Boolean? = null,
)

data class PhoneNumberValidation(
    @Json(name = "minLength") val minLength: Int? = null,
    @Json(name = "maxLength") val maxLength: Int? = null,
)

data class ModeColors(
    @Json(name = "colorBackground") val colorBackground: String? = null,
    @Json(name = "colorPrimary") val colorPrimary: String? = null,
    @Json(name = "colorSecondary") val colorSecondary: String? = null,
    @Json(name = "colorTertiary") val colorTertiary: String? = null,
    @Json(name = "colorWarning") val colorWarning: String? = null,
    @Json(name = "colorText") val colorText: String? = null,
    @Json(name = "colorSelectedText") val colorSelectedText: String? = null,
)

data class MapThemeSetting(
    @Json(name = "lightMode") val lightMode: String? = null,
    @Json(name = "darkMode") val darkMode: String? = null,
)

data class EntitySetting(
    @Json(name = "isSkipLogin") val isSkipLogin: Boolean? = null,
    @Json(name = "isAllowDeleteAccount") val isAllowDeleteAccount: Boolean? = null,
    @Json(name = "isMandatory") val isMandatory: List<Int>? = null,
    @Json(name = "isVerification") val isVerification: List<Int>? = null,
    @Json(name = "loginBy") val loginBy: LoginBy? = null,
    @Json(name = "secOtpResendInterval") val secOtpResendInterval: Long? = null,
)

data class ContactDetail(
    @Json(name = "email") val email: String? = null,
    @Json(name = "phone") val phone: String? = null,
    @Json(name = "address") val address: String? = null,
)

data class LoginBy(
    @Json(name = "phone") val phone: List<Int>? = null,
    @Json(name = "email") val email: List<Int>? = null,
    @Json(name = "social") val social: List<Int>? = null,
)