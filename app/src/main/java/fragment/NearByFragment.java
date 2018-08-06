package fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.mr_virwus.ammen.R;

import java.util.ArrayList;

import adapter.RecycleAdapter_tentelist;
import model.Tente;


/**
 * Created by Wolf Soft on 10/10/2017.
 */

public class NearByFragment extends Fragment {

    View view;

    private String address[]= {"TENTE 1 ","TENTE 2","TENTE 3","TENTE 4"};
    private String bed[]= {"30 BED","30 bed","20 bed","30 bed"};
    private String shower[]= {"2","3","3" ,"2"};
    private String sqft[]= {"20 PERSON","30 PERSON","15 PERSON","25 PERSON"};
    private String price[]= {"GROUPE1","GROUPE2","GROUPE3","GROUPE4"};
    private int image[]= {R.drawable.buidingtwo,R.drawable.buidingtwo,R.drawable.buidingtwo,R.drawable.buidingtwo};


    private ArrayList<Tente> propertyArrayList;
    private RecyclerView recyclerView;
    private RecycleAdapter_tentelist mAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_neabyproperty, container, false);



        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerview_property);
        propertyArrayList = new ArrayList<>();



        for (int i = 0; i < address.length; i++) {
            Tente beanClassForRecyclerView_contacts = new Tente(address[i],price[i],bed[i],shower[i],sqft[i],image[i]);

            propertyArrayList.add(beanClassForRecyclerView_contacts);
        }


        mAdapter = new RecycleAdapter_tentelist(getActivity(),propertyArrayList);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        return view;

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);





    }




}
