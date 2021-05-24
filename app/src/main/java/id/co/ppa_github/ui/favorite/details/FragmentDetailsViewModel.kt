package id.co.ppa_github.ui.favorite.details

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.core.usecase.RemoveFavorite
import id.co.ppa_github.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.launch

class FragmentDetailsViewModel
@ViewModelInject constructor(
    private val removeFavorite: RemoveFavorite,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : ViewModel() {

    fun remove(userDetails: UserDetails?) {
        viewModelScope.launch(dispatcher) { removeFavorite(userDetails) }
    }
}