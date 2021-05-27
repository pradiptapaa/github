package id.co.ppa_github_favorite.ui

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import id.co.ppa_github_favorite.R
import id.co.ppa_github_favorite.core.domain.UserDetails
import id.co.ppa_github_favorite.databinding.LayoutRvBinding
import id.co.ppa_github_favorite.infrastructure.loadPhotoFrom
import id.co.ppa_github_favorite.infrastructure.setTextFrom
import id.co.ppa_github_favorite.ui.favorite.FavoriteViewModel
import id.co.ppa_github_favorite.ui.viewholder.FavoriteViewHolder
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import me.ibrahimyilmaz.kiel.adapterOf

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val binding: LayoutRvBinding by lazy {
        LayoutRvBinding.inflate(layoutInflater)
    }

    private val viewModel by viewModels<FavoriteViewModel>()

    private val recyclerViewAdapter = adapterOf<UserDetails> {
        register(
            layoutResource = R.layout.item_user,
            viewHolder = ::FavoriteViewHolder,
            onBindViewHolder = { vh, _, user ->
                with(vh.binding) {
                    itemUserIvPhoto loadPhotoFrom user.userDetailsResponse?.avatarUrl
                    itemUserTvName setTextFrom user.userDetailsResponse?.login
                }
            }
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        setupRecyclerview()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch {
            viewModel.getFavoriteList()
        }
    }


    private fun setupRecyclerview() {
        lifecycleScope.launch {
            viewModel.favoriteListState.collect {
                binding.searchUserIncludeNoData.root.isVisible = it.isNullOrEmpty()
                binding.rvList.adapter = recyclerViewAdapter.apply {
                    submitList(it)
                }
            }
        }

    }


}