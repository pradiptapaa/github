package id.co.ppa_github_favorite.ui.favorite

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.ppa_github_favorite.core.domain.UserDetails
import id.co.ppa_github_favorite.core.usecase.GetFavorites
import id.co.ppa_github_favorite.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.launch

class FavoriteViewModel
@ViewModelInject constructor(
    private val getFavorites: GetFavorites,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    private val _favoriteListState = MutableSharedFlow<List<UserDetails>?>()
    val favoriteListState: MutableSharedFlow<List<UserDetails>?>
        get() = _favoriteListState

    suspend fun getFavoriteList() = viewModelScope.launch(dispatcher) {
        _favoriteListState.emit(getFavorites())
    }
}