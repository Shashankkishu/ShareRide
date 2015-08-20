package com.example.root.sharide;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.Serializable;
import java.util.List;

/**
 * Created by root on 28/6/15.
 */
public class RidesListAdapter extends RecyclerView.Adapter<RidesListAdapter.RideViewHolder>{
    public  Context mContext;
    private List<RidePost> rideGetList;
    private CardView cv;
    public RidesListAdapter(List<RidePost> Ridelist) {
        this.rideGetList = Ridelist;
    }
    private int position;
    @Override
    public int getItemCount() {
        return rideGetList.size();
    }

    @Override
    public void onBindViewHolder(RideViewHolder rideViewHolder, int i) {
        RidePost ride  = rideGetList.get(i);
        rideViewHolder.userName.setText("Shashank Bhushan");
        rideViewHolder.userTime.setText(ride.getlTime());
        rideViewHolder.userDate.setText(ride.getlDate());
        rideViewHolder.origin.setText(ride.getlOrigin());
        rideViewHolder.destination.setText(ride.getlDestination());
        position = rideViewHolder.getAdapterPosition();
    }

    @Override
    public RideViewHolder onCreateViewHolder(final ViewGroup viewGroup, final int i) {
        final View itemView = LayoutInflater.
                from(viewGroup.getContext()).
                inflate(R.layout.list_item_rides, viewGroup, false);
        cv = (CardView)viewGroup.findViewById(R.id.card_view);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GlobalObjects.rideGlobal = rideGetList.get(position);
                Intent intent = new Intent(viewGroup.getContext(), RideDedicatedPage.class);
//                intent.setAction(rideGetList.get(i).getlDate());
                viewGroup.getContext().startActivity(intent);
            }
        });
        return new RideViewHolder(itemView);
    }


    public static class RideViewHolder extends RecyclerView.ViewHolder {
        protected TextView userName;
        protected TextView userTime;
        protected TextView userDate;
        protected TextView origin;
        protected TextView destination;
        public View view;
        public RideViewHolder(View v) {
            super(v);
//            view = v;
//            view.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(view, RideDedicatedPage.class);
//                    view.startActivity(intent);
//                }
//            });

            userName =  (TextView) v.findViewById(R.id.userName);
            userDate = (TextView)  v.findViewById(R.id.userDate);
            userTime = (TextView)  v.findViewById(R.id.userTime);
            origin = (TextView) v.findViewById(R.id.origin);
            destination= (TextView) v.findViewById(R.id.destination);
        }
    }
}
