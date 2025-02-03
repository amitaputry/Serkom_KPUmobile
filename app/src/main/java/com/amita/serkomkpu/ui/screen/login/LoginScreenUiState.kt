package com.amita.serkomkpu.ui.screen.login

import com.amita.serkomkpu.util.emptyString

data class LoginScreenUiState(
    val email: String = emptyString(),
    val password: String = emptyString(),
)
