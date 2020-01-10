package com.celusion.fragmodel.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.celusion.fragmodel.R;
import com.celusion.fragmodel.databinding.LogFragmentBinding;
import com.celusion.fragmodel.viewmodel.LogViewModel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

public class LogFragment extends Fragment {

    public static final String FRAG = LogFragment.class.getSimpleName();
    LogFragmentBinding logFragmentBinding;
    LogViewModel logViewModel;



    public static LogFragment newInstance() {
        return new LogFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.log_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        logFragmentBinding = DataBindingUtil.setContentView((Activity) getContext(), R.layout.log_fragment);
        logViewModel = ViewModelProviders.of(this).get(LogViewModel.class);
        logFragmentBinding.setViewModel(logViewModel);
        setListener();
    }

    public void setListener() {
        logFragmentBinding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logViewModel.isValid.set(false);
            }
        });
    }


}
