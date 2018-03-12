package dtmweb.com.instashop.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dtmweb.com.instashop.R;
import dtmweb.com.instashop.utils.MultipleScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageOrderFragment extends Fragment {


    public ManageOrderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_manage_order, container, false);
        new MultipleScreen(getActivity());
        MultipleScreen.resizeAllView((ViewGroup) root);
        return root;    }

}
