package com.rentables.testcenter;

import android.annotation.SuppressLint;
import android.app.ListFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class BrowseFragment extends Fragment implements OnItemClickListener{
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_browse, container,
                false);
        ListView lst = (ListView) rootView.findViewById(android.R.id.list);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Planets, android.R.layout.simple_list_item_1);
        lst.setAdapter(adapter);

        // Inflate the layout for this fragment
        return rootView;
                //inflater.inflate(R.layout.fragment_browse, container, false);
    }

    /* @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.Planets, android.R.layout.simple_list_item_1);
        setListAdapter(adapter);
        getListView().setOnItemClickListener(this);
    }
    */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Toast.makeText(getActivity(), "Item: " + position, Toast.LENGTH_SHORT).show();
    }
}