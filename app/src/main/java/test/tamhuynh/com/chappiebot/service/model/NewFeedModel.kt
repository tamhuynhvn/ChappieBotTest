package test.tamhuynh.com.chappiebot.service.model

data class NewFeedModel(
    val items: List<Item>
)
data class Item(
    val avatar: Any,
    val content: Any,
    val content_type: String,
    val description: String,
    val document_id: String,
    val images: List<Image>,
    val origin_url: String,
    val published_date: String,
    val publisher: Publisher,
    val title: String
)
data class Image(
    val height: Int,
    val href: String,
    val main_color: String,
    val width: Int
)

data class Markup(
    val end: Int,
    val markup_type: Int,
    val start: Int
)