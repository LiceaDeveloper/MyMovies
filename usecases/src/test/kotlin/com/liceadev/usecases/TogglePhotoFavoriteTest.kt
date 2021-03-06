package com.liceadev.usecases

import com.liceadev.data.PhotosRepository
import com.liceadev.domain.Photo
import com.liceadev.domain.User
import com.liceadev.testshared.mockedPhoto
import com.nhaarman.mockitokotlin2.verify
import junit.framework.Assert.assertFalse
import junit.framework.Assert.assertTrue
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class TogglePhotoFavoriteTest {

    @Mock
    lateinit var photosRepository: PhotosRepository

    lateinit var togglePhotoFavorite: TogglePhotoFavorite

    @Before
    fun setUp() {
        togglePhotoFavorite = TogglePhotoFavorite(photosRepository)
    }

    @Test
    fun `invoke call photosRepository`() {
        runBlocking {
            val photo = mockedPhoto.copy(id = 1)

            val result = togglePhotoFavorite.invoke(photo)

            verify(photosRepository).update(result)
        }
    }

    @Test
    fun `add to favorite`() {
        runBlocking {
            val photo = mockedPhoto.copy(favorite = false)

            val result = togglePhotoFavorite.invoke(photo)

            assertTrue(result.favorite)
        }
    }

    @Test
    fun `remove from favorite`() {
        runBlocking {
            val photo = mockedPhoto.copy(favorite = true)

            val result = togglePhotoFavorite.invoke(photo)

            assertFalse(result.favorite)
        }
    }
}
