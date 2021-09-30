package com.hotmail.or_dvir.televiziacompose.ui.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.hotmail.or_dvir.database.users.UsersDataSource.RegisterResponse
import com.hotmail.or_dvir.televiziacompose.R
import kotlinx.coroutines.flow.collect
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterFragment : Fragment()
{
    private val viewModel: RegisterViewModel by viewModel()
    private val fragArgs: RegisterFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View
    {
        return ComposeView(requireContext()).apply {
            // In order for savedState to work, the same ID needs to be used for all instances.
            id = R.id.registerFragment

            layoutParams = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )

            viewModel.apply {
                onEmailInputChanged(fragArgs.email)
                onPasswordInputChanged(fragArgs.password)
            }

            collectRegisterEvents()
            setContent { RegisterScreen(viewModel) }
        }
    }

    private fun collectRegisterEvents()
    {
        lifecycleScope.launchWhenStarted {
            viewModel.registerEventsFlow.collect {
                when (it)
                {
                    is RegisterResponse.Success ->
                    {
                        //todo temporary! navigate to appropriate screen
                        AlertDialog.Builder(requireContext()).apply {
                            setMessage("success")
                            setPositiveButton(R.string.ok) { _, _ -> /*do nothing*/ }
                        }.show()
                    }
                    is RegisterResponse.Error ->
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