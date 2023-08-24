package com.zs.mangareader.core.domain.models.mangaResponse

data class Attributes(

    val title: LocalizedString,
    val altTitles: List<LocalizedString>,
    val description: Any,
    val isLocked: Boolean,
    val links: Links,
    val originalLanguage: String,
    val lastVolume: String,
    val lastChapter: String,
    val publicationDemographic: String,
    val status: String,
    val year: Int,
    val contentRating: String,
    val tags: List<Data>,
    val state: String,
    val chapterNumbersResetOnNewVolume: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val version: Int,
    val availableTranslatedLanguages: List<String>,
    val latestUploadedChapter: String,
    val group: String,
    val name: Any,
    val biography: LocalizedString,
    val booth: Any,
    val fanBox: Any,
    val fantia: Any,
    val fileName: String,
    val imageUrl: Any,
    val locale: String,
    val melonBook: Any,
    val naver: Any,
    val nicoVideo: Any,
    val pixiv: Any,
    val skeb: Any,
    val tumblr: String,
    val twitter: String,
    val volume: String,
    val website: String,
    val weibo: Any,
    val youtube: String

)