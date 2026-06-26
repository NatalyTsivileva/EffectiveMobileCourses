package ru.compose.tsivileva.effectivemobilecourses.login

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.net.toUri
import androidx.core.widget.doOnTextChanged
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import ru.compose.tsivileva.effectivemobilecourses.core.presentation.BaseFragment
import ru.compose.tsivileva.effectivemobilecourses.login.databinding.FrLoginBinding

class LoginFragment : BaseFragment<FrLoginBinding>(FrLoginBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.emailEditText.doOnTextChanged { text, start, before, count ->
            val password = binding.passwordEditText.text.toString()
            binding.loginBtn.isEnabled = isValidEmail(text.toString()) && isValidPassword(password)
        }

        binding.passwordEditText.doOnTextChanged { text, start, before, count ->
            val email = binding.emailEditText.text.toString()
            binding.loginBtn.isEnabled = isValidEmail(email) && isValidPassword(text.toString())
        }

        binding.vkLoginBtn.setOnClickListener {
            openUrl("https://vk.com/")
        }

        binding.odnoklassnikiLoginBtn.setOnClickListener {
            openUrl("https://ok.ru/")
        }

        binding.loginBtn.setOnClickListener {
            navigateToHome()
        }
    }

    private fun isValidEmail(text: String): Boolean {
        val emailRegex = Regex("""^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$""")
        return emailRegex.matches(text) && text.isNotEmpty()
    }

    private fun isValidPassword(text: String) = text.isNotEmpty()

    private fun navigateToHome() {
        val homeUri = "ru.compose.tsivileva.effectivemobilecourses://navigation/home".toUri()
        val intent = Intent(Intent.ACTION_VIEW, homeUri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        activity?.finish()
    }

    private fun openUrl(url: String){
        val intent = Intent(Intent.ACTION_VIEW, url.toUri())
        startActivity(intent)
    }
}