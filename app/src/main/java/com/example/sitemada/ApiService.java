package com.example.sitemada;
// ApiService.java
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {
    @GET("/generate")
    Call<List<AgenceModel>> generateData();

    @GET("{id}")
    Call<AgenceModel> getAgenceById(@Path("id") String id);

    @GET("api/agence/")
    Call<List<AgenceModel>> getAllAgence();

    @GET("api/desti/search/{titre}")
    Call<List<DestinationModel>> search(@Path("titre") String titre);

    @GET("api/desti/")
    Call<List<DestinationModel>> getAllDesti();

    @POST("login/inscription")
    Call<RegisterResponse> register(@Body RegisterRequest registerRequest);

    @POST("login/seConnecter")
    Call<LoginResponse> login(@Body LoginRequest loginRequest);

    @PUT("mofidAgence")
    Call<AgenceModel> updateAgence(@Body AgenceModel agence);

    @DELETE("delete/{id}")
    Call<List<AgenceModel>> deleteAgence(@Path("id")String agenceId);

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
