package com.example.AndroidPhotos42;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

public class FirstFragment extends Fragment {

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup vg,
            Bundle savedInstanceState
    ) {




        return inflater.inflate(R.layout.fragment_first, vg, false);
    
    
    
    }

   

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        View buttonFirst = view.findViewById(R.id.button_first);
        buttonFirst.setOnClickListener(v -> navigateToSecondFragment());
    }

    private void navigateToSecondFragment() {
        NavHostFragment.findNavController(this).navigate(R.id.action_FirstFragment_to_SecondFragment);
    }




}
