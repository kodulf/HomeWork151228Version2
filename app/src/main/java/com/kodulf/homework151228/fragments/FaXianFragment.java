package com.kodulf.homework151228.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kodulf.homework151228.R;

/**
 * Created by Administrator on 15-12-28.
 */
public class FaXianFragment extends Fragment {
    public FaXianFragment() {
        super();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View ret = inflater.inflate(R.layout.faxian_fragment,container,false);
        return ret;
//        return super.onCreateView(inflater, container, savedInstanceState);
    }
}
