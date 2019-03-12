package com.arubianoch.architecturecomponentssample.home;

import android.arch.lifecycle.LifecycleOwner;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arubianoch.architecturecomponentssample.R;
import com.arubianoch.architecturecomponentssample.model.Repo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.RepoViewHolder> {

    private final List<Repo> data = new ArrayList<>();
    private RepoSelectedListener repoSelectedListener;

    public RepoListAdapter(ListViewModel viewModel, LifecycleOwner lifecycleOwner,
                           RepoSelectedListener repoSelectedListener) {
        this.repoSelectedListener = repoSelectedListener;
        viewModel.getRepos().observe(lifecycleOwner, repos -> {
            data.clear();
            if (repos != null) {
                data.addAll(repos);
                //This validation always should go outside 'if' check.
                notifyDataSetChanged();//TODO; Use DiffUtils when we have AutoValue models
            }
//            setHasStableIds(true);
        });
    }

    @Override
    public RepoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_repo_list_item, parent, false);
        return new RepoViewHolder(view, repoSelectedListener);
    }

    @Override
    public void onBindViewHolder(RepoViewHolder holder, int position) {
        holder.bind(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    @Override
    public long getItemId(int position) {
        return data.get(position).id;
    }

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        private TextView repoName;
        private TextView repoDescription;
        private TextView forks;
        private TextView stars;

        private Repo repo;

        public RepoViewHolder(View itemView, RepoSelectedListener repoSelectedListener) {
            super(itemView);

            repoName = itemView.findViewById(R.id.tv_repo_name);
            repoDescription = itemView.findViewById(R.id.tv_repo_description);
            forks = itemView.findViewById(R.id.tv_forks);
            stars = itemView.findViewById(R.id.tv_stars);

            itemView.setOnClickListener(v -> {
                if (repo != null) {
                    repoSelectedListener.onRepoSelected(repo);
                }
            });
        }

        void bind(@NonNull Repo repo) {
            this.repo = repo;
            repoName.setText(repo.name);
            repoDescription.setText(repo.description);
            forks.setText(String.valueOf(repo.forks));
            stars.setText(String.valueOf(repo.stars));
        }
    }
}
