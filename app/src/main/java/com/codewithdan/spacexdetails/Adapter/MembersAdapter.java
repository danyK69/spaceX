package com.codewithdan.spacexdetails.Adapter;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.helper.widget.Layer;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.codewithdan.spacexdetails.R;
import com.codewithdan.spacexdetails.model.Members;

import java.util.List;

public class MembersAdapter extends RecyclerView.Adapter<MembersAdapter.MemberViewHolder> {

    private Context context;
    private List<Members> membersList;

    public MembersAdapter(Context context, List<Members> membersList) {
        this.context = context;
        this.membersList = membersList;
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        return new MemberViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_layout,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MembersAdapter.MemberViewHolder holder, int position) {
        Members members = membersList.get(position);
        holder.name.setText("Name: " + members.getName());
        holder.status.setText("Status: " + members.getStatus());
        holder.agency.setText("Agency: " + members.getAgency());
        holder.wikipedia.setText("Link: " + members.getWikipedia());

        Glide.with(context)
                .load(members.getImage())
                .into(holder.imageView);

    }

    public void getAllMembers(List<Members> membersList)
    {
        this.membersList = membersList;
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }

    public static class MemberViewHolder extends RecyclerView.ViewHolder{
        public TextView name,status,agency,wikipedia;
        public ImageView imageView;
        public MemberViewHolder(@NonNull  View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            status = itemView.findViewById(R.id.status);
            wikipedia = itemView.findViewById(R.id.wikipedia);
            agency = itemView.findViewById(R.id.agency);

            imageView = itemView.findViewById(R.id.imageView);

        }
    }
}
