package com.example.sitemada;
// ApiService.java
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/generate")
    Call<List<AgenceModel>> generateData();

    @GET("/{id}")
    Call<AgenceModel> getById(@Path("id") String id);

    @GET("find/findAll")
    Call<List<AgenceModel>> getAllAgence();

    @POST("login/inscription")
    Call<List<AgenceModel>> signup();

    @POST("login/seConnecter")
    Call<List<AgenceModel>> login();

    @PUT("login/mofidAgence")
    Call<List<AgenceModel>> mofidAgence();

    @DELETE("delete/{id}")
    Call<List<AgenceModel>> deleteAgence();

    @GET("/{agenceId}/publication/{publicationId}")
    Call<AgenceModel> getPublicationById(@Path("agenceId") String agenceId,@Path("publicationId") String publicationId);

    @PUT("/{agenceId}/publication/{publicationId}")
    Call<AgenceModel> updatePublication(@Path("agenceId") String agenceId,@Path("publicationId") String publicationId);

    @DELETE("/{agenceId}/publication/{publicationId}")
    Call<AgenceModel> deletePublication(@Path("agenceId") String agenceId,@Path("publicationId") String publicationId);

    @POST("/publication/{publicationId}")
    Call<AgenceModel> insertPublication(@Path("publicationId") String publicationId);

    @POST("/{id}/publication/{idPublication}/photo")
    Call<AgenceModel> addPhotoPublication(@Path("id") String id,@Path("idPublication") String idPublication);

    @DELETE("/{id}/publication/{idPublication}/photo/{photoIndex}")
    Call<AgenceModel> deletePhotoPublication(@Path("agenceId") String agenceId,@Path("publicationId") String publicationId,@Path("photoIndex") String photoIndex);

}
