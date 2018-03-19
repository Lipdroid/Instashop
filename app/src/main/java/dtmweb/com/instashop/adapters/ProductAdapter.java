package dtmweb.com.instashop.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.List;

import dtmweb.com.instashop.MainActivity;
import dtmweb.com.instashop.R;
import dtmweb.com.instashop.constants.Constants;
import dtmweb.com.instashop.holders.OrderHistoryHolder;
import dtmweb.com.instashop.holders.ProductHolder;
import dtmweb.com.instashop.models.OrderObject;
import dtmweb.com.instashop.models.ProductObject;
import dtmweb.com.instashop.utils.MultipleScreen;

/**
 * Created by mdmunirhossain on 3/16/18.
 */

public class ProductAdapter extends BaseAdapter {
    private Context mContext = null;
    private Activity mActivity = null;
    private List<ProductObject> mListData = null;
    private ProductHolder mHolder = null;


    @Override
    public int getCount() {
        //return mListData.size();
        return 10;
    }

    public ProductAdapter(Context mContext, List<ProductObject> mListData) {
        this.mContext = mContext;
        mActivity = (Activity) mContext;
        this.mListData = mListData;

    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup viewGroup) {
        if (convertView == null) {
            convertView = mActivity.getLayoutInflater().inflate(R.layout.item_product_main, viewGroup, false);
            mHolder = new ProductHolder();
            mHolder.main_root = (LinearLayout) convertView.findViewById(R.id.main_root);

            new MultipleScreen(mActivity);
            MultipleScreen.resizeAllView((ViewGroup) convertView);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ProductHolder) convertView.getTag();
        }

        setListenersForViews(position);
        return convertView;
    }

    private void setListenersForViews(final int position) {
        mHolder.main_root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ((MainActivity) mContext).addSecondStageFragment(Constants.FRAG_PRODUCT_DETAILS, mListData.get(position));
                ((MainActivity) mContext).addSecondStageFragment(Constants.FRAG_PRODUCT_DETAILS, null);

            }
        });
    }

}

