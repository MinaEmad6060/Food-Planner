package com.example.foodplanner.Online;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.HomeScreen.View.HomeActivity;
import com.example.foodplanner.R;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginFragment extends Fragment {

    static final String TAG = "Login";

    public static final String SHARED_PREF = "sharedPre";
    EditText emailLogin;
    EditText passwordLogin;
    Button btnLogin;

    GoogleSignInClient googleSignInClient;

    ImageView btnGoogle;

    FirebaseAuth mAuth;

    Button btnGuest;
    TextView signUp;

    StartActivity myStartActivity;

    String userName;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUp=view.findViewById(R.id.Sign_Up);
        btnGuest=view.findViewById(R.id.btn_guest);
        myStartActivity =(StartActivity)getActivity();
        btnGoogle=view.findViewById(R.id.btn_Google);
        SharedPreferences sharedPreferences =
                myStartActivity.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();

        signUp.setOnClickListener(v -> {
            NavDirections action = LoginFragmentDirections.actionLoginFragmentToSignUpFragment();
            Navigation.findNavController(v).navigate(action);
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("name","");
                editor.apply();
                Intent moveToHome = new Intent(myStartActivity, HomeActivity.class);
                startActivity(moveToHome);
            }
        });


        emailLogin=view.findViewById(R.id.Login_Email);
        passwordLogin=view.findViewById(R.id.Login_Password);
        btnLogin=view.findViewById(R.id.btn_login);
        mAuth = FirebaseAuth.getInstance();

        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                String user = emailLogin.getText().toString();

                String pass = passwordLogin.getText().toString();
                if(!user.isEmpty() && Patterns.EMAIL_ADDRESS
                        .matcher(user).matches()){
                    if(!pass.isEmpty()){
                        mAuth.signInWithEmailAndPassword(user, pass)
                                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                                    @Override
                                    public void onSuccess(AuthResult authResult) {
                                        Log.i(TAG, "user: "+user);
                                        String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                        userName=extractUserName(userEmail);
                                        editor.putString("name",userName);
                                        editor.apply();
                                        Intent intent=new Intent(myStartActivity, HomeActivity.class);
                                        Toast.makeText(myStartActivity, "Welcome" + " " + userName,
                                                Toast.LENGTH_SHORT).show();
                                        startActivity(intent);
                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(myStartActivity, "Login Failed",
                                                Toast.LENGTH_SHORT).show();
                                    }
                                });
                    }else{
                        passwordLogin.setError("pass cant be empty");
                        editor.putString("name","");
                        editor.apply();
                    }

                } else if (user.isEmpty()) {
                    emailLogin.setError("email cant be empty");
                    editor.putString("name","");
                    editor.apply();
                }else{
                    emailLogin.setError("enter valid email");
                    editor.putString("name","");
                    editor.apply();
                }
            }
        });




        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("173711819771-ikp71o791cj1vq0b75l0drndg81489up.apps.googleusercontent.com")
                .requestEmail()
                .build();
        googleSignInClient = GoogleSignIn.getClient(getContext(), googleSignInOptions);
        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = googleSignInClient.getSignInIntent();
                startActivityForResult(intent, 100);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Task<GoogleSignInAccount> signInAccountTask = GoogleSignIn.getSignedInAccountFromIntent(data);
            if (signInAccountTask.isSuccessful()) {
                String s = "Google sign in successful";
                Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                try {
                    GoogleSignInAccount googleSignInAccount = signInAccountTask.getResult(ApiException.class);
                    if (googleSignInAccount != null) {
                        AuthCredential authCredential = GoogleAuthProvider.getCredential(googleSignInAccount.getIdToken(), null);
                        mAuth.signInWithCredential(authCredential).addOnCompleteListener(requireActivity(),
                                new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            String userEmail = FirebaseAuth.getInstance().getCurrentUser().getEmail();
                                            SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            userName=extractUserName(userEmail);
                                            editor.putString("name", userName);
                                            editor.apply();
                                            Log.i(TAG, "gmail: "+userName);
                                            startActivity(new Intent(getActivity(), HomeActivity.class));
                                            Toast.makeText(getContext(), s, Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getContext(), "Login fail, please try again", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                } catch (ApiException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    String extractUserName(String email){
        String[] userInfo=email.split("@");
        return userInfo[0];
    }
}