package id.co.ppa_github.ui.favorite.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import id.co.ppa_github.R
import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.databinding.LayoutUserDetailsBinding
import id.co.ppa_github.infrastructure.loadPhotoFrom
import id.co.ppa_github.infrastructure.openLink
import id.co.ppa_github.infrastructure.setTextFrom
import id.co.ppa_github.infrastructure.showConfirmationSnackbar
import id.co.ppa_github.ui.favorite.list.FavoriteFragmentDirections
import id.co.ppa_github.ui.follower.FollowerPagerAdapter
import id.co.ppa_github.ui.search.details.setFollower
import id.co.ppa_github.ui.search.details.setFollowing
import id.co.ppa_github.ui.search.details.setGists
import id.co.ppa_github.ui.search.details.setRepo

class FavoriteDetailsFragment : Fragment() {

    companion object {

        fun navigateFromSearchToUserDetails(v: View, userDetails: UserDetails?) {
            val action =
                FavoriteFragmentDirections.actionFavoriteFragmentToFavoriteDetailsFragment(
                    userDetails
                )
            v.findNavController().navigate(action)
        }

        @StringRes
        private val TAB_TITLES = intArrayOf(
            R.string.label_follower,
            R.string.label_following
        )
    }

    private val binding: LayoutUserDetailsBinding by lazy {
        LayoutUserDetailsBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<FragmentDetailsViewModel>()

    private val args: FavoriteDetailsFragmentArgs by navArgs()

    private fun render(data: UserDetails?) {
        with(binding.userDetailsFabFavorite) {
            setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_heart_outline
                )
            )
            isVisible = true
            setOnClickListener {
                viewModel.remove(data)
                showConfirmationSnackbar(
                    binding.root,
                    "Removed rom Favorite",
                    resources.getString(R.string.label_close)
                ) { requireActivity().onBackPressed() }
            }
        }
        with(binding.searchDetailsIncludeUserDetails) {
            data?.userDetailsResponse?.let {
                userDetailsTvUsername setTextFrom it.login
                userDetailsTvName setTextFrom it.login
                userDetailsTvLocation setTextFrom it.location
                userDetailsTvCompany setTextFrom it.company
                userDetailsIvPhoto loadPhotoFrom it.avatarUrl
                binding setRepo it.publicRepos.toString()
                binding setGists it.publicGists.toString()
                binding setFollowing it.following.toString()
                binding setFollower it.followers.toString()
                userDetailsBtnOpenGithub openLink it.blog
            }
        }
        setupViewPager(data?.userFollowerResponse, data?.userFollowingResponse)
    }

    private fun setupViewPager(follower: List<Follower>?, following: List<Follower>?) {
        val followerPagerAdapter = FollowerPagerAdapter(this, follower, following)
        binding.userDetailsVpViewpager.adapter = followerPagerAdapter
        TabLayoutMediator(
            binding.userDetailsTlTab,
            binding.userDetailsVpViewpager
        ) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render(args.userDetails)
    }
}