package com.hotmail.or_dvir.televiziacompose.ui.login_register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.hotmail.or_dvir.televiziacompose.R
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

            //todo set viewmodel ui state with username/password

            setContent { RegisterScreen(viewModel) }
        }
    }
}