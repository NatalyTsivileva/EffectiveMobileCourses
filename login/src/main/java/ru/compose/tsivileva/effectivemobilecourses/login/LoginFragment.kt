package ru.compose.tsivileva.effectivemobilecourses.login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.net.toUri
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController

class LoginFragment : Fragment(R.layout.fr_login) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<Button>(R.id.loginBtn).setOnClickListener {
            navigateToHome()
        }
    }

    fun navigateToHome() {
        val homeUri = "ru.compose.tsivileva.effectivemobilecourses://navigation/home".toUri()
        val intent = Intent(Intent.ACTION_VIEW, homeUri).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }
        startActivity(intent)
        activity?.finish()
    }
}