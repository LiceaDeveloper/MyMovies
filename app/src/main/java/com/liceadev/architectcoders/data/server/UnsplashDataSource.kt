package com.liceadev.architectcoders.data.server

import com.liceadev.data.source.RemoteDataSource
import com.liceadev.domain.Photo as DomainPhoto

class UnsplashDataSource : RemoteDataSource {

    override suspend fun getPhotos(apiKey: String, country: String): List<DomainPhoto> =
        PhotoClient
            .service
            .searchPhotos(apiKey, country, 30)
            .results
            .map { it.toDomainPhoto() }
}

