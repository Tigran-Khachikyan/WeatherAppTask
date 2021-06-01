package com.example.weatherapptask.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapptask.R;
import com.example.weatherapptask.databinding.MainFragmentBinding;

public class MainFragment extends Fragment {

    public static MainFragment newInstance() {
        return new MainFragment();
    }

    private MainViewModel viewModel;
    private MainFragmentBinding binding;
    private WeatherInfoAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);
        binding = MainFragmentBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.observeCurrentWeather().observe(getViewLifecycleOwner(), info -> {
            if (info != null) {
                adapter.setWeatherInfo(info);
            }
            binding.progressBar.setVisibility(View.GONE);
        });

        adapter = new WeatherInfoAdapter();
        binding.rvWeatherInfo.setAdapter(adapter);

        binding.btnGetWeather.setOnClickListener(v -> {
            binding.progressBar.setVisibility(View.VISIBLE);
            String input = binding.etInput.getText().toString();
            viewModel.getCurrentWeather(input);
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}