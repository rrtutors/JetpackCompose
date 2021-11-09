package com.example.jetpack.datamodels

import androidx.paging.PagingSource
import androidx.paging.PagingState
import retrofit2.HttpException
import java.io.IOException


class UserPagingSource : PagingSource<Int, UserInfo>() {

    override fun getRefreshKey(state: PagingState<Int, UserInfo>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UserInfo> {

        return try {
            val page = params.key ?: 1
            val userListResponse = RestClient().getService().getUserList(page)
            LoadResult.Page(
                data = userListResponse.data,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (userListResponse.data.isEmpty()) null else page + 1
            )
        } catch (exception: IOException) {
            return LoadResult.Error(exception)
        } catch (exception: HttpException) {
            return LoadResult.Error(exception)
        }
    }
}