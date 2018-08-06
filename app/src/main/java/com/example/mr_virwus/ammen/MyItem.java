package com.example.mr_virwus.ammen;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.clustering.ClusterItem;

/**
 * Created by mr_virwus on 7/29/18.
 */

public class MyItem implements ClusterItem {
    private final LatLng mPosition;
    private final String mTitle;
    private final String mSnippet;


    public MyItem(LatLng s) {
        mPosition = s;
        this.mTitle="";
        this.mSnippet="";
    }
    public MyItem(double lat, double lng) {
        mPosition = new LatLng(lat, lng);
        this.mTitle="";
        this.mSnippet="";
    }

    public MyItem(double lat, double lng, String title, String snippet) {
        mPosition = new LatLng(lat, lng);
        mTitle = title;
        mSnippet = snippet;
    }

    @Override
    public LatLng getPosition() {
        return mPosition;
    }

    @Override
    public String getTitle() {
        return mTitle;
    }

    @Override
    public String getSnippet() {
        return mSnippet;
    }
}
