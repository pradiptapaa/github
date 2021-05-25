package id.co.ppa_github.ui.setting

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import id.co.ppa_github.R
import id.co.ppa_github.databinding.LayoutSettingBinding
import id.co.ppa_github.infrastructure.changeLanguage
import id.co.ppa_github.ui.MainActivity
import id.co.ppa_github.ui.search.list.SearchUserFragmentDirections

class SettingFragment : Fragment() {

    private val binding: LayoutSettingBinding by lazy {
        LayoutSettingBinding.inflate(layoutInflater)
    }

    private val viewModel by activityViewModels<SettingViewModel>()

    companion object {
        fun navigateFromSearchToSetting(v: View) {
            val action =
                SearchUserFragmentDirections.actionSearchUserFragmentToSettingFragment()
            v.findNavController().navigate(action)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (requireActivity() as MainActivity)
            .supportActionBar?.apply {
                this.title = getString(R.string.label_setting)
                this.show()
            }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        render(binding)
    }

    private fun render(binding: LayoutSettingBinding) {
        with(binding) {
            settingSwitchReminder.isChecked = viewModel.isReminderExist()
            settingSwitchReminder.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    viewModel.setReminder(isChecked)
                    Toast.makeText(requireContext(), "Reminder is turned on", Toast.LENGTH_SHORT)
                        .show()
                } else {
                    viewModel.setReminder(isChecked)
                    Toast.makeText(requireContext(), "Reminder is turned off", Toast.LENGTH_SHORT)
                        .show()
                }
            }
            settingBtnLanguage.setOnClickListener { it.changeLanguage() }
        }
    }


}