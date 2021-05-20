package id.co.ppa_github.framework.datasource.paging

import androidx.paging.PagingSource
import id.co.ppa_github.module.github.domain.`object`.User
import id.co.ppa_github.module.github.domain.parameter.SearchParameter
import id.co.ppa_github.module.github.usecase.GetUsers
import retrofit2.HttpException
import java.io.IOException
import java.io.InterruptedIOException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class SearchPagingDataSource(
    private val search: GetUsers,
    private val searchParameter: SearchParameter
) : PagingSource<Int, User>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, User> {
        return try {
            val page = params.key ?: 1
            val parameter = SearchParameter(searchParameter.username, page = page)
            val data = search(parameter)?.users
            LoadResult.Page(
                data = data!!,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (data.isEmpty()) null else page + 1
            )
        } catch (e: UnknownHostException) {
            LoadResult.Error(e)
        } catch (e: SocketTimeoutException) {
            LoadResult.Error(e)
        } catch (e: HttpException) {
            LoadResult.Error(e)
        } catch (e: InterruptedIOException) {
            LoadResult.Error(e)
        } catch (e: IOException) {
            LoadResult.Error(e)
        } catch (e: NullPointerException) {
            LoadResult.Error(e)
        }
    }
}