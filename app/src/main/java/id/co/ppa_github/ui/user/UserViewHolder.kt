package id.co.ppa_github.ui.user

import android.view.View
import id.co.ppa_github.core.domain.`object`.User
import id.co.ppa_github.databinding.ItemUserBinding
import me.ibrahimyilmaz.kiel.core.RecyclerViewHolder

class UserViewHolder(view: View) : RecyclerViewHolder<User>(view) {
    val binding = ItemUserBinding.bind(view)
}