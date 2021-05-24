package id.co.ppa_github.ui.viewholder

import android.view.View
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.databinding.ItemUserBinding
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class FavoriteViewHolder(view: View) : RecyclerViewHolder<UserDetails>(view) {
    val binding = ItemUserBinding.bind(view)
}