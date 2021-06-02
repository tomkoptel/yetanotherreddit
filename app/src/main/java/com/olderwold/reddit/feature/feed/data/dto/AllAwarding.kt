package com.olderwold.reddit.feature.feed.data.dto


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AllAwarding(
    @Json(name = "award_sub_type")
    val awardSubType: String? = null,
    @Json(name = "award_type")
    val awardType: String? = null,
    @Json(name = "awardings_required_to_grant_benefits")
    val awardingsRequiredToGrantBenefits: Any? = null,
    @Json(name = "coin_price")
    val coinPrice: Int? = null,
    @Json(name = "coin_reward")
    val coinReward: Int? = null,
    @Json(name = "count")
    val count: Int? = null,
    @Json(name = "days_of_drip_extension")
    val daysOfDripExtension: Int? = null,
    @Json(name = "days_of_premium")
    val daysOfPremium: Int? = null,
    @Json(name = "description")
    val description: String? = null,
    @Json(name = "end_date")
    val endDate: Any? = null,
    @Json(name = "giver_coin_reward")
    val giverCoinReward: Any? = null,
    @Json(name = "icon_format")
    val iconFormat: Any? = null,
    @Json(name = "icon_height")
    val iconHeight: Int? = null,
    @Json(name = "icon_url")
    val iconUrl: String? = null,
    @Json(name = "icon_width")
    val iconWidth: Int? = null,
    @Json(name = "id")
    val id: String? = null,
    @Json(name = "is_enabled")
    val isEnabled: Boolean? = null,
    @Json(name = "is_new")
    val isNew: Boolean? = null,
    @Json(name = "name")
    val name: String? = null,
    @Json(name = "penny_donate")
    val pennyDonate: Any? = null,
    @Json(name = "penny_price")
    val pennyPrice: Any? = null,
    @Json(name = "resized_icons")
    val resizedIcons: List<ResizedIcon>? = null,
    @Json(name = "resized_static_icons")
    val resizedStaticIcons: List<ResizedStaticIcon>? = null,
    @Json(name = "start_date")
    val startDate: Any? = null,
    @Json(name = "static_icon_height")
    val staticIconHeight: Int? = null,
    @Json(name = "static_icon_url")
    val staticIconUrl: String? = null,
    @Json(name = "static_icon_width")
    val staticIconWidth: Int? = null,
    @Json(name = "subreddit_coin_reward")
    val subredditCoinReward: Int? = null,
    @Json(name = "subreddit_id")
    val subredditId: Any? = null,
    @Json(name = "tiers_by_required_awardings")
    val tiersByRequiredAwardings: Any? = null
)
