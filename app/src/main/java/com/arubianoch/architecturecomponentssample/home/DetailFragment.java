package com.arubianoch.architecturecomponentssample.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arubianoch.architecturecomponentssample.R;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class DetailFragment extends Fragment {

    private TextView repoName;
    private TextView repoDescription;
    private TextView forks;
    private TextView stars;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.screen_detail, container, false);

        repoName = view.findViewById(R.id.tv_repo_name);
        repoDescription = view.findViewById(R.id.tv_repo_description);
        forks = view.findViewById(R.id.tv_forks);
        stars = view.findViewById(R.id.tv_stars);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        displayRepo();
    }

    private void displayRepo() {

    }
}
