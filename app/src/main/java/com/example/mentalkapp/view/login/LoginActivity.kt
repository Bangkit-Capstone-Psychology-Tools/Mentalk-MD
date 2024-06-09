package com.example.mentalkapp.view.login

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.mentalkapp.R
import com.example.mentalkapp.data.response.ErrorResponse
import com.example.mentalkapp.data.userdata.UserModel
import com.example.mentalkapp.databinding.ActivityLoginBinding
import com.example.mentalkapp.view.ViewModelFactory
import com.example.mentalkapp.view.main.MainActivity
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {
    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    private var _binding: ActivityLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        showLoading(false)
        setupAction()
        playAnimation()
    }

    private fun setupAction() {
        binding.loginButton.setOnClickListener {
            try {
                viewModel.isLoading.observe(this) {
                    showLoading(it)
                }

                val email = binding.loginEmailCustom.text.toString()
                val password = binding.loginPasswordCustom.text.toString()

                if (email.isEmpty()) {
                    binding.loginEmailLayout.error = getString(R.string.fill_email)
                } else if (password.isEmpty()) {
                    binding.loginPasswordLayout.error = getString(R.string.fill_password)
                }


                viewModel.executelogin(email, password)
                viewModel.loginResult.observe(this) {
                    Log.e("Login", "it: ${it}")
                    if (it.error == false) {
                        save(
                            UserModel(
                                it.loginResult?.token.toString(),
                                it.loginResult?.name.toString(),
                                it.loginResult?.userId.toString(),
                                true
                            )
                        )
                    }
                }
            } catch (e: retrofit2.HttpException) {
                showLoading(false)
                val errorBody = e.response()?.errorBody()?.string()
                val errorResponse = Gson().fromJson(errorBody, ErrorResponse::class.java)
                showToast(errorResponse.message)
            }

        }
    }


    private fun playAnimation() {
        ObjectAnimator.ofFloat(binding.loginImage, View.TRANSLATION_X, -30f, 30f).apply {
            duration = 6000
            repeatCount = ObjectAnimator.INFINITE
            repeatMode = ObjectAnimator.REVERSE
        }.start()


        val emailTextView =
            ObjectAnimator.ofFloat(binding.loginEmailTextView, View.ALPHA, 1f).setDuration(100)
        val emailEditTextLayout =
            ObjectAnimator.ofFloat(binding.loginEmailLayout, View.ALPHA, 1f).setDuration(100)
        val passwordTextView =
            ObjectAnimator.ofFloat(binding.loginPasswordTextView, View.ALPHA, 1f).setDuration(100)
        val passwordEditTextLayout =
            ObjectAnimator.ofFloat(binding.loginPasswordLayout, View.ALPHA, 1f).setDuration(100)
        val login = ObjectAnimator.ofFloat(binding.loginButton, View.ALPHA, 1f).setDuration(100)

        AnimatorSet().apply {
            playSequentially(
                emailTextView,
                emailEditTextLayout,
                passwordTextView,
                passwordEditTextLayout,
                login
            )
            startDelay = 100
        }.start()
    }

    private fun save(session: UserModel) {
        lifecycleScope.launch {
            viewModel.saveUserSession(session)
            val intent = Intent(this@LoginActivity, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            ViewModelFactory.clearInstance()
            startActivity(intent)
        }
    }


    private fun showToast(message: String?) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    private fun showLoading(isLoading: Boolean) {
        binding.loginLoading.visibility = if (isLoading) View.VISIBLE else View.GONE

    }

}