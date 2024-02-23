package com.example.foodplanner.Online.View;

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
import com.example.foodplanner.Online.Presenter.SignUpFragmentPres;
import com.example.foodplanner.Online.Presenter.SignUpFragmentPresInter;
import com.example.foodplanner.R;

public class SignUpFragment extends Fragment implements SignUpFragmentViewInter{
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    StartActivity findStartActivity;
    SignUpFragmentPresInter signUpFragmentPresInter;
    Button btnSignUp;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_sign_up, container, false);
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        signUpFragmentPresInter=new SignUpFragmentPres();
        btnSignUp=view.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(v -> {
            NavDirections action = SignUpFragmentDirections
                    .actionSignUpFragmentToLoginFragment();
            Navigation.findNavController(v).navigate(action);
        });

        findStartActivity = (StartActivity) getActivity();
        email=view.findViewById(R.id.SignUP_Email);
        password=view.findViewById(R.id.SignUP_Password);
        confirmPassword=view.findViewById(R.id.Confirm_Password);
        btnSignUp=view.findViewById(R.id.btn_signup);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
            signUpFragmentPresInter.validateSignUp(email, password, confirmPassword,view);
            }
        });
    }

    @Override
    public void showSuccessToast(){
        Toast.makeText(findStartActivity, "SignUp Successful", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void showFailureToast(){
        Toast.makeText(findStartActivity, "SignUp Failed", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void errEmptyEmail(){
        email.setError("Email cannot be empty");    }
    @Override
    public void errInvalidEmail(){
        email.setError("Email format should be a@b.com");    }
    @Override
    public void errEmptyPassword(){
        password.setError("Password cannot be empty");
    }
    public void errLengthOfPassword(){
        password.setError("Password should be at least 6 digits");
    }
    @Override
    public void errPasswordConfirmation(){
        confirmPassword.setError("Confirm password fail!");
    }


}