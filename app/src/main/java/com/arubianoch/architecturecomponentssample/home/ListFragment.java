package com.arubianoch.architecturecomponentssample.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
}
