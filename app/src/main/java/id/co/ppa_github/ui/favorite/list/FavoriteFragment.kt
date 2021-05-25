package id.co.ppa_github.ui.favorite.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import id.co.base.ResultOf
import id.co.ppa_github.R
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.databinding.LayoutRvBinding
import id.co.ppa_github.infrastructure.loadPhotoFrom
import id.co.ppa_github.infrastructure.setTextFrom
import id.co.ppa_github.ui.MainActivity
import id.co.ppa_github.ui.favorite.details.FavoriteDetailsFragment
import id.co.ppa_github.ui.viewholder.FavoriteViewHolder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ibrahimyilmaz.kiel.adapterOf

class FavoriteFragment : Fragment() {

    private val binding: LayoutRvBinding by lazy {
        LayoutRvBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<FavoriteViewModel>()

    val recyclerViewAdapter = adapterOf<UserDetails> {
        register(
            layoutResource = R.layout.item_user,
            viewHolder = ::FavoriteViewHolder,
            onBindViewHolder = { vh, _, user ->
                with(vh.binding) {
                    itemUserIvPhoto loadPhotoFrom user.userDetailsResponse?.avatarUrl
                    itemUserTvName setTextFrom user.userDetailsResponse?.login
                    itemUserMcvContainer.setOnClickListener {
                        FavoriteDetailsFragment.navigateFromSearchToUserDetails(it, user)
                    }
                }
            }
        )
    }

    private fun setupObserver() {
        Log.d("FavoriteFragnebt", "setupObserver: Init")
        lifecycleScope.launch {
            viewModel.favoriteListState.collect {
                when (it) {
                    is ResultOf.Loading -> Log.d("FavoriteFragment", "setupObserver: loading")
                    is ResultOf.Failure -> Log.d(
                        "FavoriteFragment",
                        "setupObserver: failure ${it.exception}"
                    )
                    is ResultOf.Success -> render(it.data)
                }
            }
        }
    }

    private fun render(list: List<UserDetails>?) {
        setupRecyclerview(list)
    }

    private fun setupRecyclerview(list: List<UserDetails>?) {
        isEmpty(list.isNullOrEmpty())
        binding.rvList.adapter = recyclerViewAdapter.apply {
            submitList(list)
        }
    }

    private fun isEmpty(isEmpty: Boolean) {
        with(binding) {
            searchUserIncludeNoData.root.isVisible = isEmpty
            rvList.isVisible = !isEmpty
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity)
            .supportActionBar?.apply {
                this.title = getString(R.string.label_favorite_list)
                this.show()
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupObserver()
        viewModel.getFavoriteList()
    }

}