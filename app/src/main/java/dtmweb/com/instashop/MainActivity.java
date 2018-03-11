package dtmweb.com.instashop;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import dtmweb.com.instashop.utils.CorrectSizeUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CorrectSizeUtil mCorrectSize = null;
    private Toolbar mToolBar = null;
    private DrawerLayout mDrawerLayout = null;
    private ActionBarDrawerToggle mDrawerToggle = null;
    private Context mContext = null;
    private ImageView btn_left_drawer = null;
    private ImageView btn_right_drawer = null;
    private RelativeLayout drawer_left_layout = null;
    private LinearLayout drawer_right_layout = null;
    private boolean isOutSideClicked = false;

    private RelativeLayout main_container = null;
    //left drawers items
    private LinearLayout btn_manage_products = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        findViews();
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        setListenersForViews();
        mCorrectSize = CorrectSizeUtil.getInstance(this);
        mCorrectSize.correctSize();
    }

    private void setListenersForViews() {
        btn_left_drawer.setOnClickListener(this);
        btn_right_drawer.setOnClickListener(this);
        //left drawer listener
        btn_manage_products.setOnClickListener(this);
    }

    private void findViews() {
        btn_left_drawer = (ImageView) findViewById(R.id.btn_left_drawer);
        btn_right_drawer = (ImageView) findViewById(R.id.btn_right_drawer);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        drawer_left_layout = (RelativeLayout) findViewById(R.id.drawer_left);
        drawer_right_layout = (LinearLayout) findViewById(R.id.drawer_right);

        main_container = (RelativeLayout) findViewById(R.id.main_container);
        //left drawer items
        btn_manage_products = (LinearLayout) findViewById(R.id.btn_manage_products);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_left_drawer:
                afterClickLeftDrawerBtn();
                break;
            case R.id.btn_right_drawer:
                afterClickRightDrawerBtn();
                break;
            case R.id.btn_manage_products:
                Log.e("manage products", "pressed");
                break;
        }
    }

    private void afterClickRightDrawerBtn() {
        if (mDrawerLayout.isDrawerOpen(drawer_right_layout)) {
            mDrawerLayout.closeDrawer(drawer_right_layout);
            //focus on the center layout
            main_container.bringToFront();
            main_container.requestLayout();
        } else if (!mDrawerLayout.isDrawerOpen(drawer_right_layout)) {
            mDrawerLayout.openDrawer(drawer_right_layout);
            //focus on the right layout
            drawer_right_layout.bringToFront();
            drawer_right_layout.requestLayout();
        }
    }

    private void afterClickLeftDrawerBtn() {
        if (mDrawerLayout.isDrawerOpen(drawer_left_layout)) {
            mDrawerLayout.closeDrawer(drawer_left_layout);
            //focus on the center layout
            main_container.bringToFront();
            main_container.requestLayout();
        } else if (!mDrawerLayout.isDrawerOpen(drawer_left_layout)) {
            mDrawerLayout.openDrawer(drawer_left_layout);
            //focus on the left layout
            drawer_left_layout.bringToFront();
            drawer_left_layout.requestLayout();
        }
    }

}
