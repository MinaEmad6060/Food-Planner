package com.example.foodplanner.Online;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpFragment extends Fragment {
    private EditText email;
    private EditText password;
    private EditText confirmPassword;



    private FirebaseAuth mAuth;
    StartActivity startActivity;
    Button btnSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnSignUp=view.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(v -> {
            NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment();
            Navigation.findNavController(v).navigate(action);
        });

        startActivity = (StartActivity) getActivity();

        mAuth = FirebaseAuth.getInstance();

        email=view.findViewById(R.id.SignUP_Email);
        password=view.findViewById(R.id.SignUP_Password);
        confirmPassword=view.findViewById(R.id.Confirm_Password);
        btnSignUp=view.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String user = email.getText().toString().trim();
                String pass = password.getText().toString().trim();
                String confPass = confirmPassword.getText().toString().trim();


                if (user.isEmpty()){
                    email.setError("Email cannot be empty");
                }else if (pass.isEmpty()) {
                    password.setError("Password cannot be empty");
                }else if (pass.length()<6) {
                    password.setError("Password should be at least 6 digits");
                }else if (!pass.equals(confPass)) {
                    confirmPassword.setError("Password not matched");
                }else{
                    mAuth.createUserWithEmailAndPassword(user, pass)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        Toast.makeText(startActivity, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                        NavDirections action = SignUpFragmentDirections.actionSignUpFragmentToLoginFragment();
                                        Navigation.findNavController(view).navigate(action); }
                                    else {
                                        email.setError("Email Format should be like a@b.com");
                                        Toast.makeText(startActivity, "SignUp Failed", Integer.parseInt(task.getException().getMessage())).show();
                                    }
                                }
                            });
                }}});


    }
}