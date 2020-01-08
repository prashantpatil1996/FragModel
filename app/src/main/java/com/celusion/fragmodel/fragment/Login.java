package com.celusion.fragmodel.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.celusion.fragmodel.R;
import com.celusion.fragmodel.databinding.LoginFragmentBinding;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

public class Login extends Fragment {
    public static final String TAG = Login.class.getSimpleName();
    private LoginViewModel mViewModel;
    LoginFragmentBinding loginFragmentBinding;

    public static Login newInstance() {
        return new Login();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.login_fragment, container, false);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        loginFragmentBinding= DataBindingUtil.setContentView((Activity) getContext(),R.layout.login_fragment);
        loginFragmentBinding.setViewModel(new LoginViewModel());
        loginFragmentBinding.executePendingBindings();
    }

    @BindingAdapter({"toastMessage"})
    public static void runMe(View view, String message) {
        if (message != null)
            Toast.makeText(view.getContext(), message, Toast.LENGTH_SHORT).show();
    }


}
