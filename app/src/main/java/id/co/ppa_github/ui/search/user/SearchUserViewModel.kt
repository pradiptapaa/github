package id.co.ppa_github.ui.search.user

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import id.co.ppa_github.core.domain.`object`.User
import id.co.ppa_github.core.domain.parameter.SearchParameter
import id.co.ppa_github.core.usecase.GetUsers
import id.co.ppa_github.framework.datasource.paging.SearchPagingDataSource
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow

@FlowPreview
class SearchUserViewModel
@ViewModelInject constructor(
    private val getUsers: GetUsers,
) : ViewModel() {

    fun fetchUserList(parameter: SearchParameter): Flow<PagingData<User>> {
        return Pager(
            PagingConfig(
                pageSize = 1,
                prefetchDistance = 2
            )
        ) {
            SearchPagingDataSource(getUsers, parameter)
        }.flow.cachedIn(viewModelScope)
    }

}