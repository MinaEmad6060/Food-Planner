package com.example.foodplanner.Online.Presenter;

import android.view.View;
import android.widget.EditText;

public interface SignUpFragmentPresInter {
    void validateSignUp(EditText email, EditText password
            , EditText confirmPassword, View view);
}
