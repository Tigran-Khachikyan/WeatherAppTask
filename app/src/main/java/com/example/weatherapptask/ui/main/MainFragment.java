package com.example.weatherapptask.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapptask.R;
import com.example.weatherapptask.data.repositories.Errors;
import com.example.weatherapptask.data.repositories.State;
import com.example.weatherapptask.databinding.MainFragmentBinding;
import com.example.weatherapptask.domain.weather.models.WeatherInfo;

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

        initViews();

        viewModel.observeCurrentWeather().observe(getViewLifecycleOwner(), state -> {
            if (state instanceof State.Loading) {
                binding.progressBar.setVisibility(View.VISIBLE);
            } else {
                binding.progressBar.setVisibility(View.GONE);
                if (state instanceof State.Error) {
                    Errors error = ((State.Error<WeatherInfo>) state).getError();
                    String issue = "Some other issues occurred";
                    switch (error) {
                        case ERROR_CONNECTION:
                            issue = "Check connection!";
                            break;
                        case ERROR_RESPONSE_EMPTY:
                            issue = "Response is empty!";
                            break;
                        case ERROR_RESPONSE_NOT_SUCCEED:
                            issue = "Response failed!";
                            break;
                        case CACHE_EMPTY:
                            issue = "Not found in cache";
                            break;
                    }
                    Toast.makeText(getContext(), issue, Toast.LENGTH_SHORT).show();
                    adapter.clearInfo();
                } else {
                    if (state instanceof State.Success) {
                        if (state instanceof State.Success.FromNetwork) {
                            Toast.makeText(getContext(), "Latest data", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getContext(), "Data from cache", Toast.LENGTH_SHORT).show();
                        }
                        adapter.setWeatherInfo(((State.Success<WeatherInfo>) state).getData());
                    }
                }
            }
        });
    }

    private void initViews() {
        String into = getString(R.string.search_by) + " " + getString(R.string.location);
        binding.tvIntro.setText(into);

        adapter = new WeatherInfoAdapter();
        binding.rvWeatherInfo.setAdapter(adapter);

        binding.btnGetWeather.setOnClickListener(v -> {
            String input = binding.etInput.getText().toString();
            viewModel.act(new MainViewModel.Action.GetWeatherInfo(input));
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}