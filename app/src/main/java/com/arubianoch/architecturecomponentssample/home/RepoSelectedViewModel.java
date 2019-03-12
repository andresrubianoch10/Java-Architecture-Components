package com.arubianoch.architecturecomponentssample.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.util.Log;

import com.arubianoch.architecturecomponentssample.model.Repo;
import com.arubianoch.architecturecomponentssample.networking.RepoApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class RepoSelectedViewModel extends ViewModel {

    private static final String TAG = RepoSelectedViewModel.class.getSimpleName();
    private final MutableLiveData<Repo> selectedRepo = new MutableLiveData<>();
    private Call<Repo> repoCall;

    public LiveData<Repo> getSelectedRepo() {
        return selectedRepo;
    }

    public void setSelectedRepo(Repo repo) {
        selectedRepo.setValue(repo);
    }

    public void saveToBundle(Bundle outState) {
        if (selectedRepo.getValue() != null) {
            outState.putStringArray("repo_details",
                    new String[]{
                            selectedRepo.getValue().owner.login,
                            selectedRepo.getValue().name});
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if (selectedRepo.getValue() == null) {
            if (savedInstanceState != null && savedInstanceState.containsKey("repo_details")) {
                loadRepo(savedInstanceState.getStringArray("repo_details"));
            }
        }
    }

    private void loadRepo(String[] repoDetails) {
        repoCall = RepoApi.getInstance().getRepo(repoDetails[0], repoDetails[1]);
        repoCall.enqueue(new Callback<Repo>() {
            @Override
            public void onResponse(Call<Repo> call, Response<Repo> response) {
                selectedRepo.setValue(response.body());
                repoCall = null;
            }

            @Override
            public void onFailure(Call<Repo> call, Throwable t) {
                Log.e(TAG, "Error downloading Repo");
                repoCall = null;
            }
        });
    }

    @Override
    protected void onCleared() {
        if (repoCall != null) {
            repoCall.cancel();
            repoCall = null;
        }

        super.onCleared();
    }
}
