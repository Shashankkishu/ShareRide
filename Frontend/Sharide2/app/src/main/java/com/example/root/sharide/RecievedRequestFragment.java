package com.example.root.sharide;

import android.content.Context;
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
public class RecievedRequestFragment extends Fragment {


    Context mContext;

    private List<RecievedRequest_Object> RecievedRequest_Object;
    private RecyclerView rv;


    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        rootView = inflater.inflate(R.layout.recievedrequest_layout, container, false);
        rv = (RecyclerView) rootView.findViewById(R.id.rv);
        LinearLayoutManager llm = new LinearLayoutManager(getActivity());
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);

        initializeData();
        initializeAdapter();



        return rootView;
    }

    private void initializeData() {


        this.RecievedRequest_Object = new ArrayList<RecievedRequest_Object>();
        this.RecievedRequest_Object.add(new RecievedRequest_Object("Shashank bhushan has rquested for a ride", "Date", "Time"));
        this.RecievedRequest_Object.add(new RecievedRequest_Object("Romit Choudhary has rquested for a ride", "Date", "Time"));
        this.RecievedRequest_Object.add(new RecievedRequest_Object("R Sundrarajan has rquested for a ride", "Date", "Time"));
        this.RecievedRequest_Object.add(new RecievedRequest_Object("Siddhant Dangi has rquested for a ride", "Date", "Time"));
        this.RecievedRequest_Object.add(new RecievedRequest_Object("Shashank bhushan has rquested for a ride", "Date", "Time"));
        this.RecievedRequest_Object.add(new RecievedRequest_Object("Romit Choudhary has rquested for a ride","Date", "Time"));
        this.RecievedRequest_Object.add(new RecievedRequest_Object("R Sundrarajan has rquested for a ride", "Date", "Time"));
        this.RecievedRequest_Object.add(new RecievedRequest_Object("Siddhant Dangi has rquested for a ride","Date", "Time"));
    }

    private void initializeAdapter() {

        RVAdapter_RecievedRequest adapter = new RVAdapter_RecievedRequest(this.RecievedRequest_Object);
        this.rv.setAdapter(adapter);


    }
}



