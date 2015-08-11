package com.example.root.sharide;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Shashank Bhusha on 6/23/2015.
 */
public class RVAdapter_SentRequest extends RecyclerView.Adapter<RVAdapter_SentRequest.PersonViewHolder>{


        List<SentRequest_Object> SentRequest_Object;

    RVAdapter_SentRequest(List<SentRequest_Object> SentRequest_Object){
            this.SentRequest_Object = SentRequest_Object;
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public PersonViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
            View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.sentrequest_itemlayout, viewGroup, false);
            PersonViewHolder pvh = new PersonViewHolder(v);
            return pvh;
        }

        @Override
        public void onBindViewHolder(PersonViewHolder personViewHolder, int i) {
            SentRequest_Object lp = SentRequest_Object.get(i);
            personViewHolder.mUsername.setText(SentRequest_Object.get(i).mUsername);
            personViewHolder.mDate.setText(SentRequest_Object.get(i).mDate);
            personViewHolder.mTime.setText(SentRequest_Object.get(i).mTime);

//        if (Integer.valueOf(lp.percentage) < 30)
//            personViewHolder.percent.setTextColor(blue);
//        else if (Integer.valueOf(persons.get(i).percentage) < 60)
//        {
//            personViewHolder.percent.setTextColor(GREEN);
//
//        } else if (Integer.valueOf(persons.get(i).percentage) < 100)
//        {
//            personViewHolder.percent.setTextColor(RED);
//
//        }
            }

        @Override
        public int getItemCount() {
            return SentRequest_Object.size();
        }

        /**
         * Person View Holder
         */
        public static class PersonViewHolder extends RecyclerView.ViewHolder {

            CardView cv;

            TextView mUsername;
            TextView mTime;
            TextView mDate;
            PersonViewHolder(View itemView) {
                super(itemView);
                cv = (CardView)itemView.findViewById(R.id.card_view);
                mUsername = (TextView)itemView.findViewById(R.id.userName);
                mDate = (TextView)itemView.findViewById(R.id.userDate);
                mTime = (TextView)itemView.findViewById(R.id.userTime);
            }
        }

    }
