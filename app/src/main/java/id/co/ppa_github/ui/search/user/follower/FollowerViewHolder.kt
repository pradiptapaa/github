package id.co.ppa_github.ui.search.user.follower

import android.view.View
import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.databinding.ItemUserBinding
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class FollowerViewHolder(view: View) : RecyclerViewHolder<Follower>(view) {
    val binding = ItemUserBinding.bind(view)
}