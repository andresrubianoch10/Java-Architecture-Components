package com.arubianoch.architecturecomponentssample.networking;

import retrofit2.Retrofit;
import retrofit2.converter.moshi.MoshiConverterFactory;

/**
 * @author Andres Rubiano Del Chiaro
 */
public class RepoApi {

    private static final String BASE_URL = "https://api.github.com/";

    private static Retrofit retrofit;
    private static RepoService repoService;

    public static RepoService getInstance() {
        if (repoService != null) {
            return repoService;
        }

        if (retrofit == null) {
            initializeRetrofit();
        }

        repoService = retrofit.create(RepoService.class);
        return repoService;
    }

    private static void initializeRetrofit() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(MoshiConverterFactory.create())
                .build();
    }

    public RepoApi() {
    }
}
