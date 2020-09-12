package com.kiarielinus.leaderboardapp;

import com.kiarielinus.leaderboardapp.model.LearningLeadersModel;
import com.kiarielinus.leaderboardapp.model.SkillIQLeadersModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GadsApi {

    @GET("/api/hours")
    Call<List<LearningLeadersModel>> getLearningLeaders();

    @GET("/api/skilliq")
    Call<List<SkillIQLeadersModel>> getSkillIQLeaders();

}
