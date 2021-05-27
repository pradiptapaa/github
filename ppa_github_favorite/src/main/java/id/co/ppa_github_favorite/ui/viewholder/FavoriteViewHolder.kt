package id.co.ppa_github_favorite.ui.viewholder

import android.view.View
import id.co.ppa_github_favorite.core.domain.UserDetails
import id.co.ppa_github_favorite.databinding.ItemUserBinding
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class FavoriteViewHolder(view: View) : RecyclerViewHolder<UserDetails>(view) {
    val binding = ItemUserBinding.bind(view)
}