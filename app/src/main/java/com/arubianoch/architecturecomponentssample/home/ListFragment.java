package com.arubianoch.architecturecomponentssample.home;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arubianoch.architecturecomponentssample.R;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class ListFragment extends Fragment {

    private RecyclerView listView;
    private TextView errorTextView;
    private View loadingView;
    private ListViewModel viewModel;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        View view = inflater.inflate(R.layout.screen_list, container, false);

        listView = view.findViewById(R.id.recycler_view);
        errorTextView = view.findViewById(R.id.tv_error);
        loadingView = view.findViewById(R.id.loading_view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this).get(ListViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new RepoListAdapter(viewModel, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        observeViewModel();
    }

    private void observeViewModel() {
        viewModel.getRepos().observe(this, repos -> {
            if (repos != null) {
                listView.setVisibility(View.VISIBLE);
            }
        });
        viewModel.getRepoLoadError().observe(this, isError -> {
            if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText(R.string.api_error_response);
            }
        });
        viewModel.getLoading().observe(this, isLoading -> {
            loadingView.setVisibility(isLoading? View.VISIBLE : View.GONE);
            if (isLoading) {
                errorTextView.setVisibility(View.GONE);
                listView.setVisibility(View.GONE);
            }
        });
    }
}
