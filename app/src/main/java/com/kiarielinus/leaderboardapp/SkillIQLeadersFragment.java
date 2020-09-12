package com.kiarielinus.leaderboardapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.kiarielinus.leaderboardapp.model.SkillIQLeadersModel;
import com.kiarielinus.leaderboardapp.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SkillIQLeadersFragment extends Fragment {


    private View mView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private SkillIQRecyclerViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.skill_iq_leaders,container,false);

        GadsApi gadsApi = RetrofitClientInstance.getRetrofitInstance().create(GadsApi.class);
        Call<List<SkillIQLeadersModel>>  call = gadsApi.getSkillIQLeaders();
        call.enqueue(new Callback<List<SkillIQLeadersModel>>() {
            @Override
            public void onResponse(Call<List<SkillIQLeadersModel>> call, Response<List<SkillIQLeadersModel>> response) {
                generateLearningLeadersList(response.body());
            }

            @Override
            public void onFailure(Call<List<SkillIQLeadersModel>> call, Throwable t) {
                Toast.makeText(getActivity(),"Please provide internet connection..." ,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return mView;
    }

    private void generateLearningLeadersList(List<SkillIQLeadersModel> skillIQLeadersList) {
        mRecyclerView = mView.findViewById(R.id.skill_iq_leaders_rv);
        mLinearLayoutManager = new LinearLayoutManager(mView.getContext());
        mAdapter = new SkillIQRecyclerViewAdapter(skillIQLeadersList,mView.getContext());

        displayLearningLeaders();
    }

    private void displayLearningLeaders() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}

