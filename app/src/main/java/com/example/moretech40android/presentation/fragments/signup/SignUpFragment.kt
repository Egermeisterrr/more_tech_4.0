package com.example.moretech40android.presentation.fragments.signup

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListPopupWindow
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.net.toUri
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.example.moretech40android.R
import com.example.moretech40android.databinding.FragmentSignUpBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    private val viewModel: SignUpViewModel by viewModels()
    private lateinit var binding: FragmentSignUpBinding
    private lateinit var someActivityResultLauncher: ActivityResultLauncher<Intent>
    private var imageUri: String = ""

    override fun onAttach(context: Context) {
        super.onAttach(context)

        someActivityResultLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data: Intent? = result.data
                if (data != null) {
                    imageUri = data.data.toString()
                    Glide.with(context)
                        .load(imageUri.toUri())
                        .transform(CircleCrop())
                        .into(binding.userPhoto)
                }
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSignUpBinding.inflate(inflater, container, false)
        binding.userPhoto.setOnClickListener {
            chooseImage()
        }
        binding.backArrow.setOnClickListener {
            viewModel.toSignInNavigation()
        }
        binding.checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                binding.registerButton.isEnabled = true
                binding.registerButton.alpha = 1f
            } else {
                binding.registerButton.alpha = 0.7f
            }
        }
        binding.registerButton.setOnClickListener {
            hideKeyBoard()
            if (binding.email.text.toString().isEmpty() || binding.signUpPassword.text.toString()
                    .isEmpty() || binding.confirmSignUpPassword.text.toString()
                    .isEmpty() || binding.username.text.toString()
                    .isEmpty() || binding.field.text.toString().isEmpty()
            ) {
                Snackbar.make(binding.root, "Введите все данные", Snackbar.LENGTH_LONG).show()
            } else {

            }
        }
        viewModel.navEvent.observe(viewLifecycleOwner) { action ->
            this.findNavController().navigate(action)
        }
        binding.differentJobPosition.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(
                text: CharSequence?,
                start: Int,
                count: Int,
                after: Int
            ) {
            }

            override fun onTextChanged(text: CharSequence?, start: Int, before: Int, count: Int) {
                binding.field.text = text
            }

            override fun afterTextChanged(text: Editable?) {}

        })
        binding.differentJobPosition.setOnKeyListener(object : View.OnKeyListener {
            override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                if (event.action == KeyEvent.ACTION_DOWN ||
                    keyCode == KeyEvent.KEYCODE_ENTER
                ) {
                    binding.differentJobPosition.visibility = View.GONE
                    hideKeyBoard()
                    return true
                }
                return false
            }
        })
        createDropDownMenu()
        checkEmailAndPassword()

        return binding.root
    }


    private fun checkEmailAndPassword() {
        binding.signUpPassword.addTextChangedListener {
            if ((binding.confirmSignUpPassword.text.toString() != binding.signUpPassword.text.toString()) && binding.confirmSignUpPassword.text.toString()
                    .isNotEmpty()
            ) {
                binding.confirmPassword.error = MATCH_PASSWORDS
            } else {
                binding.confirmPassword.error = null
            }
        }
        binding.confirmSignUpPassword.addTextChangedListener {
            if (binding.confirmSignUpPassword.text.toString() != binding.signUpPassword.text.toString()) {
                binding.confirmPassword.error = MATCH_PASSWORDS
            } else {
                binding.confirmPassword.error = null
            }
        }
    }

    private fun createDropDownMenu() {
        val listPopupWindowButton = binding.field
        val listPopupWindow = ListPopupWindow(requireContext(), null)
        listPopupWindow.anchorView = listPopupWindowButton

        val adapter = ArrayAdapter(requireContext(), R.layout.list_popup_window_item, items)
        listPopupWindow.setAdapter(adapter)
        listPopupWindow.setOnItemClickListener { _: AdapterView<*>?, _: View?, position: Int, _: Long ->
            binding.field.text = items[position]
            if (position == 2) {
                listPopupWindow.dismiss()
                binding.differentJobPosition.visibility = View.VISIBLE
            } else {
                listPopupWindow.dismiss()
                binding.differentJobPosition.visibility = View.GONE
            }
        }
        listPopupWindowButton.setOnClickListener { v: View? -> listPopupWindow.show() }
    }


    private fun chooseImage() {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)
        intent.type = "image/*"
        someActivityResultLauncher.launch(intent)
    }

    private fun hideKeyBoard() {
        val inputMethod =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethod.hideSoftInputFromWindow(requireView().windowToken, 0)
    }

    companion object {
        private const val MATCH_PASSWORDS = "Пароли должны совпадать"
        val items = listOf(
            "Генеральный директор",
            "Бухгалтер",
            "Другое"
        )

    }
}