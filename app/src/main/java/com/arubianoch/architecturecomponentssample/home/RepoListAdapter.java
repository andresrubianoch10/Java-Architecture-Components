package com.arubianoch.architecturecomponentssample.home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class RepoListAdapter {

    static final class RepoViewHolder extends RecyclerView.ViewHolder {

        private TextView repoName;
        private TextView repoDescription;
        private TextView forks;
        private TextView stars;

        public RepoViewHolder(View itemView) {
            super(itemView);


        }
    }
}
