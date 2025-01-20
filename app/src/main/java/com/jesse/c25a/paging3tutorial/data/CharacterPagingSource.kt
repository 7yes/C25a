package com.jesse.c25a.paging3tutorial.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.jesse.c25a.paging3tutorial.presentation.model.CharacterModel
import java.io.IOException
import javax.inject.Inject

class CharacterPagingSource @Inject constructor(private val api: RickMortyApiService) :
    PagingSource<Int, CharacterModel>() {

    override fun getRefreshKey(state: PagingState<Int, CharacterModel>): Int? {
        return state.anchorPosition // calculate actual position
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CharacterModel> {

        return try {
            val page = params.key ?: 1  // new page
            val response = api.getCharacters(page)
            val characters = response.results

            val prevKey = if (page > 0) page - 1 else null // prev page
            val nextKey = if (response.information.next != null) page + 1 else null // next page

            LoadResult.Page(
                data = characters.map { it.toPresentation() },
                prevKey = prevKey,
                nextKey = nextKey
            )
        } catch (exception: IOException) {
            LoadResult.Error(exception)
        }
    }
}