package id.co.ppa_github.ui.search.user

import android.view.View
import id.co.ppa_github.databinding.ItemUserBinding
import id.co.ppa_github.module.github.domain.`object`.User
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class UserViewHolder(view: View) : RecyclerViewHolder<User>(view) {
    val binding = ItemUserBinding.bind(view)
}