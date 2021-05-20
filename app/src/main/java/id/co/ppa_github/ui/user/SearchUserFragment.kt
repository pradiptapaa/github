package id.co.ppa_github.ui.user

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import id.co.ppa_github.R
import id.co.ppa_github.core.domain.`object`.User
import id.co.ppa_github.core.domain.parameter.SearchParameter
import id.co.ppa_github.databinding.LayoutRvBinding
import id.co.ppa_github.infrastructure.loadPhotoFrom
import id.co.ppa_github.infrastructure.onTextChanged
import id.co.ppa_github.infrastructure.setTextFrom
import id.co.ppa_github.infrastructure.showAlertSnackbar
import id.co.ppa_github.ui.MainActivity
import id.co.ppa_github.ui.user.details.SearchUserDetailsFragment.Companion.navigateFromSearchToUserDetails
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import me.ibrahimyilmaz.kiel.pagingDataAdapterOf

@ExperimentalCoroutinesApi
@FlowPreview
class SearchUserFragment : Fragment() {

    private val binding: LayoutRvBinding by lazy {
        LayoutRvBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<SearchUserViewModel>()

    private val rvAdapter = pagingDataAdapterOf<User> {
        register(
            layoutResource = R.layout.item_user,
            viewHolder = ::UserViewHolder,
            onBindViewHolder = { vh, _, user ->
                with(vh.binding) {
                    itemUserIvPhoto loadPhotoFrom user.avatarUrl
                    itemUserTvName setTextFrom user.login
                    itemUserMcvContainer.setOnClickListener {
                        (requireActivity() as MainActivity)
                            .supportActionBar?.hide()
                        navigateFromSearchToUserDetails(it, user.login!!)
                    }
                }
            }
        )
    }

    private fun setupRecyclerview() {
        binding.rvList.adapter = rvAdapter.also { adapter ->
            lifecycleScope.launch {
                adapter.loadStateFlow.collect { loadStates ->
                    onResultLoading(loadStates.refresh is LoadState.Loading)
                    onResultEmpty(loadStates.refresh is LoadState.NotLoading)
                    if (loadStates.refresh is LoadState.Error)
                        onResultError((loadStates.refresh as LoadState.Error).error)
                }
            }
        }
    }

    private fun search(text: String?) {
        if (!onResultEmpty(text.isNullOrEmpty()))
            lifecycleScope.launch {
                viewModel.fetchUserList(SearchParameter(text)).collect(rvAdapter::submitData)
            }
    }

    private fun onResultLoading(isLoading: Boolean) {
        binding.searchUserIncludeLoading.root.isVisible = isLoading
    }

    private fun onResultError(error: Throwable) {
        showAlertSnackbar(
            binding.root,
            error.localizedMessage!!,
            resources.getString(R.string.label_close), null
        )
    }

    private fun onResultEmpty(isEmpty: Boolean): Boolean {
        val rvIsEmpty = rvAdapter.itemCount == 0
        with(binding) {
            searchUserIncludeNoData.root.isVisible = isEmpty && rvIsEmpty
            rvList.isVisible = !isEmpty || !rvIsEmpty
        }
        return isEmpty
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity)
            .supportActionBar?.apply {
                title = resources.getString(R.string.label_find_github_user)
                show()
            }
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerview()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_option, menu)
        SearchView(
            (context as MainActivity).supportActionBar?.themedContext ?: requireContext()
        ).also {
            menu.findItem(R.id.action_search).actionView = it
        }.apply {
            this.queryHint = resources.getString(R.string.hint_search_username)
            this.onTextChanged().debounce(500).onEach { text ->
                search(text)
            }.launchIn(viewLifecycleOwner.lifecycleScope)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setOnMenuItemClicked(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setOnMenuItemClicked(id: Int) {
        when (id) {
            R.id.action_setting -> changeLanguage()
        }
    }

    private fun changeLanguage() {
        val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
        startActivity(mIntent)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.rvList.layoutManager?.onRestoreInstanceState(
            savedInstanceState?.getParcelable("KEY_RV")
        )
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable(
            "KEY_RV",
            binding.rvList.layoutManager?.onSaveInstanceState()
        )
    }


}