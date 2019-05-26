package test.tamhuynh.com.chappiebot.service.model

data class DetailModel(
    val description: String,
    val document_id: String,
    val origin_url: String,
    val published_date: String,
    val publisher: Publisher,
    val sections: List<Section>,
    val template_type: String,
    val title: String
)
data class Section(
    val content: Content,
    val section_type: Int
)
