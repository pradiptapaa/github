package id.co.ppa_github.ui.user.details

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.tabs.TabLayoutMediator
import id.co.base.ResultOf
import id.co.ppa_github.R
import id.co.ppa_github.core.domain.`object`.Follower
import id.co.ppa_github.core.domain.`object`.UserDetails
import id.co.ppa_github.core.domain.parameter.UserDetailsParameter
import id.co.ppa_github.databinding.LayoutUserDetailsBinding
import id.co.ppa_github.infrastructure.loadPhotoFrom
import id.co.ppa_github.infrastructure.openLink
import id.co.ppa_github.infrastructure.setTextFrom
import id.co.ppa_github.infrastructure.showAlertSnackbar
import id.co.ppa_github.ui.user.SearchUserFragmentDirections
import id.co.ppa_github.ui.user.follower.FollowerPagerAdapter
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SearchUserDetailsFragment : Fragment() {

    companion object {
        const val TAG = "SearchUserDetails"

        fun navigateFromSearchToUserDetails(v: View, username: String) {
            val action =
                SearchUserFragmentDirections.actionSearchUserFragmentToSearchUserDetailsFragment(
                    username
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

    private val viewModel by activityViewModels<SearchUserDetailsViewModel>()

    private val args: SearchUserDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            viewModel.searchDetailsState.collect {
                Log.d(TAG, "onViewCreated: $it")
                when (it) {
                    is ResultOf.Failure -> onError(it.exception)
                    is ResultOf.Loading -> isLoading(true)
                    is ResultOf.Success -> render(it.data)
                    else -> Log.d(TAG, "onViewCreated: else branch")
                }
            }
        }
        viewModel.username.offer(UserDetailsParameter(args.username))
    }

    private fun render(data: UserDetails?) {
        isLoading(false)
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

    private fun isLoading(isLoading: Boolean) {
        Log.d(TAG, "isLoading: $isLoading")
        with(binding) {
            userDetailsIncludeLoading.root.isVisible = isLoading
            userDetailsNsvContainer.isVisible = !isLoading
        }
    }

    private fun onError(error: Throwable) {
        showAlertSnackbar(
            binding.root,
            error.localizedMessage!!,
            resources.getString(R.string.label_close)
        ) { requireActivity().onBackPressed() }
    }

}