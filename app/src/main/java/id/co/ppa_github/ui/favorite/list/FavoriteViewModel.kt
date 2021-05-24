package id.co.ppa_github.ui.favorite.list

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.base.ResultOf
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.core.usecase.GetFavorites
import id.co.ppa_github.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class FavoriteViewModel
@ViewModelInject constructor(
    private val getFavorites: GetFavorites,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _favoriteListState = MutableSharedFlow<ResultOf<List<UserDetails>?>>()
    val favoriteListState: MutableSharedFlow<ResultOf<List<UserDetails>?>>
        get() = _favoriteListState

    fun getFavoriteList() {
        viewModelScope.launch(dispatcher) {
            getFavorites().collect { _favoriteListState.emit(it) }
        }
    }
}