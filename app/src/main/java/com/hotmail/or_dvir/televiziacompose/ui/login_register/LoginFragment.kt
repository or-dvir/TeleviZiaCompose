package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.WindowCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hotmail.or_dvir.televiziacompose.R
import com.hotmail.or_dvir.televiziacompose.ui.login_register.LoginViewModel.LoginEvent
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment()
{
    private val viewModel: LoginViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(requireActivity().window, false)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.loginFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            collectLoginEvents()

            setContent {
                LoginScreen(
                    viewModel = viewModel,
                    onRegisterClicked = { email, password ->
                        findNavController().navigate(
                            LoginFragmentDirections.toRegisterFragment(email, password)
                        )
                    }
                )
            }
        }
    }

    private fun collectLoginEvents()
    {
        lifecycleScope.launchWhenStarted {
            viewModel.loginEventsFlow.collect {
                when (it)
                {
                    is LoginEvent.Success ->
                    {
                        //todo temporary! navigate to appropriate screen
                        AlertDialog.Builder(requireContext()).apply {
                            setMessage("success")
                            setPositiveButton(R.string.ok) { _, _ -> /*do nothing*/ }
                        }.show()
                    }
                    is LoginEvent.Error ->
                    {
                        AlertDialog.Builder(requireContext()).apply {
                            setMessage(it.error)
                            setPositiveButton(R.string.ok) { _, _ -> /*do nothing*/ }
                        }.show()
                    }
                }
            }
        }
    }
}

