package com.kiarielinus.leaderboardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiarielinus.leaderboardapp.model.LearningLeadersModel;

import java.util.List;

public class LearningRecyclerViewAdapter extends
        RecyclerView.Adapter<LearningRecyclerViewAdapter.ViewHolder> {

    private List<LearningLeadersModel> mLearningLeadersList;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;

    public LearningRecyclerViewAdapter(List<LearningLeadersModel> learningLeadersList, Context context) {
        mLearningLeadersList = learningLeadersList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public LearningRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.learning_leaders_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull LearningRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mTextName.setText(mLearningLeadersList.get(position).getName());
        String performanceAndCountry = mLearningLeadersList.get(position).getHours() + " learning hours, "
                + mLearningLeadersList.get(position).getCountry() + ".";
        holder.mTextPerformanceCountry.setText(performanceAndCountry);
    }

    @Override
    public int getItemCount() {
        return mLearningLeadersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextName;
        public final TextView mTextPerformanceCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_name);
            mTextPerformanceCountry = itemView.findViewById(R.id.text_performance_country);
        }
    }
}
