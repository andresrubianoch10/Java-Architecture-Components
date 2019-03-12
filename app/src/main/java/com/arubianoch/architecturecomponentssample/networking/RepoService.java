package com.arubianoch.architecturecomponentssample.networking;

import com.arubianoch.architecturecomponentssample.model.Repo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author Andres Rubiano Del Chiaro
 */
public interface RepoService {

    @GET("orgs/Google/repos")
    Call<List<Repo>> getRepositories();

    @GET("repos/{owner}/{name}")
    Call<Repo> getRepo(@Path("owner") String repoOwner, @Path("name") String repoName);
}
