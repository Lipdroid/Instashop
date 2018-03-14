package dtmweb.com.instashop.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.GridView;

import java.util.List;

import dtmweb.com.instashop.constants.Constants;
import dtmweb.com.instashop.MainActivity;
import dtmweb.com.instashop.R;
import dtmweb.com.instashop.adapters.ProductGridAdapter;
import dtmweb.com.instashop.models.ProductObject;
import dtmweb.com.instashop.utils.MultipleScreen;

/**
 * A simple {@link Fragment} subclass.
 */
public class ManageProductFragment extends Fragment {


    private GridView gridview = null;
    private List<ProductObject> mListProduct = null;
    private ProductGridAdapter adapter = null;
    private Context mContext = null;
    private Button btn_add = null;

    public ManageProductFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_manage_product, container, false);
        gridview = (GridView) root.findViewById(R.id.gridview);
        btn_add = (Button) root.findViewById(R.id.btn_add);
        mContext = getActivity();
        populateList();
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) mContext).addSecondStageFragment(Constants.FRAG_ADD_PRODUCT, null);
            }
        });
        new MultipleScreen(getActivity());
        MultipleScreen.resizeAllView((ViewGroup) root);
        return root;
    }

    private void populateList() {
        adapter = new ProductGridAdapter(mContext, mListProduct);
        gridview.setAdapter(adapter);
    }

}
