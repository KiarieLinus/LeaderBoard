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

import com.kiarielinus.leaderboardapp.model.LearningLeadersModel;
import com.kiarielinus.leaderboardapp.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LearningLeadersFragment extends Fragment {


    private View mView;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private LearningRecyclerViewAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.learning_leaders,container,false);


        GadsApi gadsApi = RetrofitClientInstance.getRetrofitInstance().create(GadsApi.class);
        Call<List<LearningLeadersModel>> call = gadsApi.getLearningLeaders();
        call.enqueue(new Callback<List<LearningLeadersModel>>() {
            @Override
            public void onResponse(Call<List<LearningLeadersModel>> call, Response<List<LearningLeadersModel>> response) {
                generateLearningLeadersList(response.body());
            }

            @Override
            public void onFailure(Call<List<LearningLeadersModel>> call, Throwable t) {
                Toast.makeText(getActivity(),"Please provide internet connection..." ,
                        Toast.LENGTH_SHORT).show();
            }
        });

        return mView;
    }

    private void generateLearningLeadersList(List<LearningLeadersModel> learningLeadersList) {
        mRecyclerView = mView.findViewById(R.id.learning_leaders_rv);
        mLinearLayoutManager = new LinearLayoutManager(mView.getContext());
        mAdapter = new LearningRecyclerViewAdapter(learningLeadersList,mView.getContext());

        displayLearningLeaders();
    }

    private void displayLearningLeaders() {
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}

