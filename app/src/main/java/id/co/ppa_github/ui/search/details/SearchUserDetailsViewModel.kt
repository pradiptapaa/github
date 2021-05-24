package id.co.ppa_github.ui.search.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.base.ResultOf
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.core.domain.parameter.UserDetailsParameter
import id.co.ppa_github.core.usecase.GetUser
import id.co.ppa_github.core.usecase.SetFavorite
import id.co.ppa_github.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class SearchUserDetailsViewModel
@ViewModelInject constructor(
    private val getUser: GetUser,
    private val setFavorite: SetFavorite,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) :
    ViewModel() {

    val username = Channel<UserDetailsParameter>(Channel.UNLIMITED)
    private val _searchDetailsState = MutableSharedFlow<ResultOf<UserDetails?>>()
    val searchDetailsState: MutableSharedFlow<ResultOf<UserDetails?>>
        get() = _searchDetailsState

    init {
        handleUsername()
    }

    private fun handleUsername() {
        viewModelScope.launch(dispatcher) {
            username.consumeAsFlow().collect { parameter ->
                getUser(parameter).collect { result ->
                    _searchDetailsState.emit(result)
                }
            }
        }
    }

    fun setUserFavorite(userDetails: UserDetails?) = viewModelScope.launch(dispatcher) {
        setFavorite(userDetails)
    }

}