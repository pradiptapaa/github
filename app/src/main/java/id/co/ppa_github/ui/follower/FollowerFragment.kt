package id.co.ppa_github.ui.follower

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import id.co.ppa_github.R
import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.databinding.LayoutRvBinding
import id.co.ppa_github.infrastructure.loadPhotoFrom
import id.co.ppa_github.infrastructure.setTextFrom
import id.co.ppa_github.ui.viewholder.FollowerViewHolder
import me.ibrahimyilmaz.kiel.adapterOf

class FollowerFragment : Fragment() {
    companion object {
        private const val KEY_FOLLOWER_LIST = "key_follower_list"
        fun newInstance(list: List<Follower>?) =
            FollowerFragment().apply {
                arguments = Bundle().apply {
                    putParcelableArrayList(KEY_FOLLOWER_LIST, ArrayList(list))
                }
            }
    }

    val recyclerViewAdapter = adapterOf<Follower> {
        register(
            layoutResource = R.layout.item_user,
            viewHolder = ::FollowerViewHolder,
            onBindViewHolder = { vh, _, user ->
                with(vh.binding) {
                    itemUserIvPhoto loadPhotoFrom user.avatarUrl
                    itemUserTvName setTextFrom user.login
                }
            }
        )
    }

    private val binding: LayoutRvBinding by lazy {
        LayoutRvBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.hide()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
    }

    private fun setupRecyclerview() {
        val list = arguments?.getParcelableArrayList<Follower>(KEY_FOLLOWER_LIST)
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


}