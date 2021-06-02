package com.codewithdan.spacexdetails.Network;

import com.codewithdan.spacexdetails.model.Members;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {

    @GET("crew")
    Call<List<Members>> getAllMembers();

}
