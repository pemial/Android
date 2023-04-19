package com.petrovmikhail.android.screens

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

sealed class SignUpScreenEvent {
    object KeepMeSignedInChecked: SignUpScreenEvent()
    object EmailMeChecked: SignUpScreenEvent()
    data class CreateAccountClicked(val navController: NavController): SignUpScreenEvent()
    object HaveAccountClicked: SignUpScreenEvent()
    object PasswordVisibilityChanged: SignUpScreenEvent()
    data class LoginChanged(val value: String): SignUpScreenEvent()
    data class EmailChanged(val value: String): SignUpScreenEvent()
    data class PasswordChanged(val value: String): SignUpScreenEvent()
}

data class SignUpScreenViewState (
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val isKeepMeSignedInChecked: Boolean = false,
    val isEmailMeChecked: Boolean = false,
    val isPasswordHidden: Boolean = true
)

@HiltViewModel
class SignUpViewModel  @Inject constructor() : ViewModel() {
    private val _viewState = MutableStateFlow(SignUpScreenViewState())
    val viewState: StateFlow<SignUpScreenViewState> = _viewState

    fun obtainEvent(event: SignUpScreenEvent) {
        when(event) {
            SignUpScreenEvent.KeepMeSignedInChecked -> checkKeepMeSignedIn()
            is SignUpScreenEvent.CreateAccountClicked -> signUp(event.navController)
            SignUpScreenEvent.EmailMeChecked -> checkEmailMe()
            SignUpScreenEvent.HaveAccountClicked -> switchToSignInScreen()
            SignUpScreenEvent.PasswordVisibilityChanged -> changePasswordVisibility()
            is SignUpScreenEvent.EmailChanged -> changeEmail(event.value)
            is SignUpScreenEvent.LoginChanged -> changeLogin(event.value)
            is SignUpScreenEvent.PasswordChanged -> changePassword(event.value)
        }
    }

    private fun checkKeepMeSignedIn() {
        _viewState.value = _viewState.value.copy(isKeepMeSignedInChecked = !_viewState.value.isKeepMeSignedInChecked)
    }

    private fun checkEmailMe() {
        _viewState.value = _viewState.value.copy(isEmailMeChecked = !_viewState.value.isEmailMeChecked)
    }

    private fun signUp(navController: NavController) {
        navController.navigate("catalog")
    }

    private fun switchToSignInScreen() {

    }

    private fun changePasswordVisibility() {
        _viewState.value = _viewState.value.copy(isPasswordHidden = !_viewState.value.isPasswordHidden)
    }

    private fun changeEmail(value: String) {
        _viewState.value = _viewState.value.copy(email = value)
    }

    private fun changeLogin(value: String) {
        _viewState.value = _viewState.value.copy(login = value)
    }

    private fun changePassword(value: String) {
        _viewState.value = _viewState.value.copy(password = value)
    }
}