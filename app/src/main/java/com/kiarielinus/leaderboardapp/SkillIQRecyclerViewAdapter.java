package com.kiarielinus.leaderboardapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kiarielinus.leaderboardapp.model.SkillIQLeadersModel;

import java.util.List;

public class SkillIQRecyclerViewAdapter extends
        RecyclerView.Adapter<SkillIQRecyclerViewAdapter.ViewHolder> {

    private List<SkillIQLeadersModel> mSkillIQLeadersList;
    private Context mContext;
    private final LayoutInflater mLayoutInflater;

    public SkillIQRecyclerViewAdapter(List<SkillIQLeadersModel> skillIQLeadersList, Context context) {
        mSkillIQLeadersList = skillIQLeadersList;
        mContext = context;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @NonNull
    @Override
    public SkillIQRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mLayoutInflater.inflate(R.layout.skill_iq_leaders_list_item,parent,false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIQRecyclerViewAdapter.ViewHolder holder, int position) {
        holder.mTextName.setText(mSkillIQLeadersList.get(position).getName());
        String performanceAndCountry = mSkillIQLeadersList.get(position).getScore() + " skill IQ Score, "
                + mSkillIQLeadersList.get(position).getCountry() + ".";
        holder.mTextPerformanceCountry.setText(performanceAndCountry);
    }

    @Override
    public int getItemCount() {
        return mSkillIQLeadersList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public final TextView mTextName;
        public final TextView mTextPerformanceCountry;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextName = itemView.findViewById(R.id.text_name_skill);
            mTextPerformanceCountry = itemView.findViewById(R.id.text_skill_country);
        }
    }
}
