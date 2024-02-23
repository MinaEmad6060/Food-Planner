package com.example.foodplanner.Online.Presenter;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.util.Patterns;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.Online.View.LoginFragment;
import com.example.foodplanner.Online.View.StartActivity;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginFragmentPres {

    public static final String SHARED_PREF = "sharedPre";
    GoogleSignInClient googleSignInClient;
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    String userName;

    Context context;
    SharedPreferences.Editor editor;
    SharedPreferences sharedPreferences;

    LoginFragment loginFragment;

    public LoginFragmentPres(Context context) {
        this.context = context;
    }




    public void accessGuest(){
        loginFragment=new LoginFragment();
        sharedPreferences =
                context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();
        editor.putString("name","");
        editor.apply();
    }


    public void accessLogin(EditText emailLogin, EditText passwordLogin){
        loginFragment=new LoginFragment();
        sharedPreferences =
                context.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        editor=sharedPreferences.edit();

        String user = emailLogin.getText().toString();
        String pass = passwordLogin.getText().toString();

        if(!user.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(user).matches()){
            if(!pass.isEmpty()){
                mAuth.signInWithEmailAndPassword(user, pass)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                userName=extractUserName(userEmail);
                                editor.putString("name",userName);
                                editor.apply();
                                loginFragment.showSuccessLogin(userName,context);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                loginFragment.showFailLogin(context);
                                loginFragment.errWrongPass(passwordLogin);
                            }
                        });
            }else{
                loginFragment.errEmptyPass(passwordLogin);
                editor.putString("name","");
                editor.apply();
            }

        } else if (user.isEmpty()) {
            loginFragment.errEmptyEmail(emailLogin);
            editor.putString("name","");
            editor.apply();
        }else{
            loginFragment.errInvalidEmail(emailLogin);
            editor.putString("name","");
            editor.apply();
        }
    }



















    String extractUserName(String email) {
        String[] userInfo = email.split("@");
        return userInfo[0];
    }


}
