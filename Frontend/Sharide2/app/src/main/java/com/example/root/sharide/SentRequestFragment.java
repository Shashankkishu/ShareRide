package com.example.root.sharide;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Shashank Bhusha on 6/22/2015.
 */
public class SentRequestFragment extends Fragment {


    private List<SentRequest_Object> SentRequest_Object;
    private RecyclerView rv;



    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.sentrequest_layout, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();



        return rootView;
    }

    private void initializeData() {


        this.SentRequest_Object = new ArrayList<SentRequest_Object>();
        this.SentRequest_Object.add(new SentRequest_Object("Shashank bhushan is rquested for a ride", "Date", "Time"));
        this.SentRequest_Object.add(new SentRequest_Object("Romit Choudhary is rquested for a ride", "Date", "Time"));
        this.SentRequest_Object.add(new SentRequest_Object("R Sundrarajan is rquested for a ride", "Date", "Time"));
        this.SentRequest_Object.add(new SentRequest_Object("Siddhant Dangi is rquested for a ride", "Date", "Time"));
        this.SentRequest_Object.add(new SentRequest_Object("Shashank bhushan is rquested for a ride", "Date", "Time"));
        this.SentRequest_Object.add(new SentRequest_Object("Romit Choudhary is rquested for a ride","Date", "Time"));
        this.SentRequest_Object.add(new SentRequest_Object("R Sundrarajan is rquested for a ride", "Date", "Time"));
        this.SentRequest_Object.add(new SentRequest_Object("Siddhant Dangi is rquested for a ride","Date", "Time"));
    }

    private void initializeAdapter() {

        RVAdapter_SentRequest adapter = new RVAdapter_SentRequest(this.SentRequest_Object);
        this.rv.setAdapter(adapter);


    }
}



