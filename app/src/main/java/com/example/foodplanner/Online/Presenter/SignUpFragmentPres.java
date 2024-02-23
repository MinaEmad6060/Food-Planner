package com.example.foodplanner.Online.Presenter;

import com.example.foodplanner.Online.View.SignUpFragment;
import com.example.foodplanner.Online.View.SignUpFragmentDirections;
import com.example.foodplanner.Online.View.SignUpFragmentViewInter;
import com.google.firebase.auth.FirebaseAuth;
import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import android.view.View;
import android.widget.EditText;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class SignUpFragmentPres implements SignUpFragmentPresInter{
    FirebaseAuth mAuth = FirebaseAuth.getInstance();
    SignUpFragmentViewInter signUpFragmentViewInter;
    public void validateSignUp(EditText email, EditText password
                                      ,EditText confirmPassword, View view){
        signUpFragmentViewInter = new SignUpFragment();
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        String confirmPass = confirmPassword.getText().toString().trim();

        if (user.isEmpty()){
            signUpFragmentViewInter.errEmptyEmail();
        }else if (pass.isEmpty()) {
            signUpFragmentViewInter.errEmptyPassword();
        }else if (pass.length()<6) {
            signUpFragmentViewInter.errLengthOfPassword();
        }else if(!pass.equals(confirmPass)){
            signUpFragmentViewInter.errPasswordConfirmation();
        }
        else{
            mAuth.createUserWithEmailAndPassword(user, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                signUpFragmentViewInter.showSuccessToast();

                                NavDirections action = SignUpFragmentDirections
                                        .actionSignUpFragmentToLoginFragment();
                                Navigation.findNavController(view).navigate(action); }
                            else {
                                signUpFragmentViewInter.errInvalidEmail();
                                signUpFragmentViewInter.showFailureToast();
                            }
                        }
                    });
        }
    }
}
