package dtmweb.com.instashop.adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import dtmweb.com.instashop.R;
import dtmweb.com.instashop.holders.ProductHolder;
import dtmweb.com.instashop.models.ProductObject;
import dtmweb.com.instashop.utils.MultipleScreen;

/**
 * Created by mdmunirhossain on 3/13/18.
 */

public class ProductGridAdapter extends BaseAdapter {
    private Context mContext = null;
    private Activity mActivity = null;
    private List<ProductObject> mListData = null;
    private ProductHolder mHolder = null;


    @Override
    public int getCount() {
        //return mListData.size();
        return 10;
    }

    public ProductGridAdapter(Context mContext, List<ProductObject> mListData) {
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
            convertView = mActivity.getLayoutInflater().inflate(R.layout.item_product, viewGroup, false);
            mHolder = new ProductHolder();

            new MultipleScreen(mActivity);
            MultipleScreen.resizeAllView((ViewGroup) convertView);

            convertView.setTag(mHolder);
        } else {
            mHolder = (ProductHolder) convertView.getTag();
        }


        return convertView;
    }

}
