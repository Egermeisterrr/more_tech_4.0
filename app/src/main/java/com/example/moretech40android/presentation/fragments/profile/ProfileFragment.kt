package com.example.moretech40android.presentation.fragments.profile

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.moretech40android.R
import com.example.moretech40android.databinding.FragmentProfileBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()
    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = FragmentProfileBinding.inflate(inflater, container, false)
        binding.editIcon1.setOnClickListener {
            viewModel.toEditEmailNavigation()
        }
        binding.logOut.setOnClickListener {
            showAlertDialog(requireContext())
        }
        binding.editIcon2.setOnClickListener {
            viewModel.toEditUsernameNavigation()
        }
        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            findNavController().navigate(action)
        }
        viewModel.getProfile()
        viewModel.username.observe(viewLifecycleOwner) { username ->
            binding.usernameProfile.text = username
        }

        return binding.root
    }

    fun showAlertDialog(context: Context) {
        val builder = AlertDialog.Builder(context, R.style.MyDialogTheme)
        builder.setTitle(CONFIRMATION)
        builder.setMessage(QUESTION)
        builder.setPositiveButton(
            YES
        ) { dialog, id ->
            viewModel.logout()
        }
        builder.setNegativeButton(
            NO
        ) { dialog, id ->
        }
        builder.show()
    }

    companion object {
        private const val CONFIRMATION = R.string.confirmation
        private const val QUESTION = R.string.question
        private const val YES = R.string.yes
        private const val NO = R.string.no
    }
}