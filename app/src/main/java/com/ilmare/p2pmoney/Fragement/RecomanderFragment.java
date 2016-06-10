package com.ilmare.p2pmoney.Fragement;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ilmare.p2pmoney.R;
import com.ilmare.p2pmoney.Utils.UIUtils;

/**
 * ===============================
 * 作者: ilmare:
 * 创建时间：6/10/2016 6:19 PM
 * 版本号： 1.0
 * 版权所有(C) 6/10/2016
 * 描述：
 * ===============================
 */

public class RecomanderFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =View.inflate(getActivity(),R.layout.fragment_product_recommonder,null);
        return view;
    }
}
