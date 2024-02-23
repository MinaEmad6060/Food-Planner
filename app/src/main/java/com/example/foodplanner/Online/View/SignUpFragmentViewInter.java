package com.example.foodplanner.Online.View;

public interface SignUpFragmentViewInter {
    void showSuccessToast();
    void showFailureToast();
    void errEmptyEmail();
    void errInvalidEmail();
    void errEmptyPassword();
    void errLengthOfPassword();
    void errPasswordConfirmation();
}
