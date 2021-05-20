package id.co.ppa_github.ui.search.user.follower

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import id.co.ppa_github.core.domain.`object`.Follower

class FollowerPagerAdapter(
    fragment: Fragment,
    private val follower: List<Follower>?,
    private val following: List<Follower>?
) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return 2
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> FollowerFragment.newInstance(follower)
            else -> FollowerFragment.newInstance(following)
        }
    }

}