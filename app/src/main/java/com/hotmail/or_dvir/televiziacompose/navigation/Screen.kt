package com.hotmail.or_dvir.televiziacompose.navigation

sealed class Screen {

    //todo do i need login screen here? it is already set as start destination in nav_graph.xml
    class RegisterScreen(email: String, password: String): Screen()
}
