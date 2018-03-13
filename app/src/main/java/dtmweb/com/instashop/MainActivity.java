package dtmweb.com.instashop;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebViewFragment;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import dtmweb.com.instashop.Constants.Constants;
import dtmweb.com.instashop.fragments.AddProductFragment;
import dtmweb.com.instashop.fragments.HomeFragment;
import dtmweb.com.instashop.fragments.ManageOrderFragment;
import dtmweb.com.instashop.fragments.ManageProductFragment;
import dtmweb.com.instashop.fragments.MyStoreFragment;
import dtmweb.com.instashop.utils.CorrectSizeUtil;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CorrectSizeUtil mCorrectSize = null;
    private Toolbar mToolBar = null;
    private DrawerLayout mDrawerLayout = null;
    private DrawerLayout.DrawerListener mDrawerListener = null;
    private Context mContext = null;
    private ImageView btn_left_drawer = null;
    private ImageView btn_right_drawer = null;
    private RelativeLayout drawer_left_layout = null;
    private static LinearLayout drawer_right_layout = null;
    private boolean isOutSideClicked = false;

    private RelativeLayout main_container = null;
    //left drawers items
    private LinearLayout btn_manage_products = null;
    private LinearLayout btn_manage_orders = null;
    private LinearLayout btn_my_store = null;
    private LinearLayout btn_language = null;
    private LinearLayout btn_my_plan = null;
    private LinearLayout btn_logout = null;
    private LinearLayout btn_terms = null;
    private LinearLayout btn_rate = null;
    private LinearLayout btn_about = null;
    private ImageView btn_back_left = null;
    private ImageView btn_edit_profile = null;

    //right drawer items
    private EditText et_search = null;
    private EditText et_price = null;
    private EditText et_location = null;
    private EditText et_store = null;
    private ImageView btn_back_right = null;
    private Button btn_search = null;


    //fragment variables
    private FragmentManager mFragManager;
    private Fragment mCurrentFrag;
    private TextView header_title;
    private FragmentTransaction fragTransaction = null;
    private ArrayList<Fragment> mSecondStageFragArray = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;
        mSecondStageFragArray = new ArrayList<Fragment>();
        findViews();
        mDrawerLayout.setScrimColor(Color.TRANSPARENT);
        setSupportActionBar(mToolBar);
        getSupportActionBar().setHomeButtonEnabled(true);
        setListenersForViews();
        //show the initial home page
        afterClickMenuItem(Constants.FRAG_HOME);

        mCorrectSize = CorrectSizeUtil.getInstance(this);
        mCorrectSize.correctSize();
    }


    private void setListenersForViews() {
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {
                clearFocus();
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        btn_left_drawer.setOnClickListener(this);
        btn_right_drawer.setOnClickListener(this);
        //left drawer listener
        btn_manage_products.setOnClickListener(this);
        btn_manage_orders.setOnClickListener(this);
        btn_my_store.setOnClickListener(this);
        btn_language.setOnClickListener(this);
        btn_my_plan.setOnClickListener(this);
        btn_logout.setOnClickListener(this);
        btn_terms.setOnClickListener(this);
        btn_rate.setOnClickListener(this);
        btn_about.setOnClickListener(this);
        btn_back_left.setOnClickListener(this);
        btn_edit_profile.setOnClickListener(this);

        //right drawer listener
        btn_back_right.setOnClickListener(this);
        btn_search.setOnClickListener(this);

    }

    private void findViews() {
        btn_left_drawer = (ImageView) findViewById(R.id.btn_left_drawer);
        btn_right_drawer = (ImageView) findViewById(R.id.btn_right_drawer);
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        //appbar items
        mToolBar = (Toolbar) findViewById(R.id.toolbar);
        drawer_left_layout = (RelativeLayout) findViewById(R.id.drawer_left);
        drawer_right_layout = (LinearLayout) findViewById(R.id.drawer_right);
        header_title = (TextView) findViewById(R.id.header_title);

        //left drawer items
        btn_manage_products = (LinearLayout) findViewById(R.id.btn_manage_products);
        btn_manage_orders = (LinearLayout) findViewById(R.id.btn_manage_orders);
        btn_my_store = (LinearLayout) findViewById(R.id.btn_my_store);
        btn_language = (LinearLayout) findViewById(R.id.btn_language);
        btn_my_plan = (LinearLayout) findViewById(R.id.btn_my_plan);
        btn_logout = (LinearLayout) findViewById(R.id.btn_logout);
        btn_terms = (LinearLayout) findViewById(R.id.btn_terms);
        btn_rate = (LinearLayout) findViewById(R.id.btn_rate);
        btn_about = (LinearLayout) findViewById(R.id.btn_about);
        btn_back_left = (ImageView) findViewById(R.id.btn_back_left);
        btn_edit_profile = (ImageView) findViewById(R.id.btn_edit_profile);

        //right drawer item
        et_search = (EditText) findViewById(R.id.et_search);
        et_price = (EditText) findViewById(R.id.et_price);
        et_location = (EditText) findViewById(R.id.et_location);
        et_store = (EditText) findViewById(R.id.et_store);
        btn_back_right = (ImageView) findViewById(R.id.btn_back_right);
        btn_search = (Button) findViewById(R.id.btn_search);

        //main layout
        main_container = (RelativeLayout) findViewById(R.id.main_container);

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
                closeLeftDrawer();
                afterClickMenuItem(Constants.FRAG_MANAGE_PRODUCTS);
                break;
            case R.id.btn_manage_orders:
                closeLeftDrawer();
                afterClickMenuItem(Constants.FRAG_MANAGE_ORDERS);
                break;
            case R.id.btn_my_store:
                closeLeftDrawer();
                afterClickMenuItem(Constants.FRAG_MY_STORE);
                break;
            case R.id.btn_language:
                closeLeftDrawer();
                break;
            case R.id.btn_my_plan:
                closeLeftDrawer();
                break;
            case R.id.btn_logout:
                closeLeftDrawer();
                break;
            case R.id.btn_terms:
                closeLeftDrawer();
                break;
            case R.id.btn_rate:
                closeLeftDrawer();
                break;
            case R.id.btn_about:
                closeLeftDrawer();
                break;
            case R.id.btn_back_left:
                closeLeftDrawer();
                break;
            case R.id.btn_edit_profile:
                closeLeftDrawer();
                break;
            case R.id.btn_back_right:
                closeRightDrawer();
                break;
            case R.id.btn_search:
                closeRightDrawer();
                break;
        }
    }

    private void openLeftDrawer() {
        mDrawerLayout.openDrawer(drawer_left_layout);
        //focus on the left layout
        drawer_left_layout.bringToFront();
        drawer_left_layout.requestLayout();
    }

    private void closeLeftDrawer() {
        mDrawerLayout.closeDrawer(drawer_left_layout);
        //focus on the center layout
        //main_container.bringToFront();
        //main_container.requestLayout();
    }

    private void clearFocus() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_price.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_store.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_location.getWindowToken(), 0);
        imm.hideSoftInputFromWindow(et_search.getWindowToken(), 0);

    }

    private void openRightDrawer() {
        mDrawerLayout.openDrawer(drawer_right_layout);
        //focus on the right layout
        drawer_right_layout.bringToFront();
        drawer_right_layout.requestLayout();
    }

    private void closeRightDrawer() {
        mDrawerLayout.closeDrawer(drawer_right_layout);
        //focus on the center layout
        //main_container.bringToFront();
        //main_container.requestLayout();
    }

    private void afterClickRightDrawerBtn() {
        if (mDrawerLayout.isDrawerOpen(drawer_right_layout)) {
            closeRightDrawer();
        } else if (!mDrawerLayout.isDrawerOpen(drawer_right_layout)) {
            openRightDrawer();
        }
    }

    private void afterClickLeftDrawerBtn() {
        if (mDrawerLayout.isDrawerOpen(drawer_left_layout)) {
            closeLeftDrawer();
        } else if (!mDrawerLayout.isDrawerOpen(drawer_left_layout)) {
            openLeftDrawer();
        }
    }

    public void addFragment(int fragId, boolean isHasAnimation) {
        // init fragment manager
        mFragManager = getSupportFragmentManager();
        // create transaction
        fragTransaction = mFragManager.beginTransaction();

        // init argument
        Bundle args = new Bundle();

        //check if there is any backstack if yes then remove it
        int count = mFragManager.getBackStackEntryCount();
        if (count != 0) {
            //this will clear the back stack and displays no animation on the screen
            mFragManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }


        // check current fragment is wanted fragment
        if (mCurrentFrag != null && mCurrentFrag.getTag() != null && mCurrentFrag.getTag().equals(String.valueOf(fragId))) {
            return;
        }

        Fragment newFrag = null;
        // identify which fragment will be called
        switch (fragId) {
            case Constants.FRAG_HOME:
                newFrag = new HomeFragment();
                break;
            case Constants.FRAG_MANAGE_PRODUCTS:
                newFrag = new ManageProductFragment();
                break;
            case Constants.FRAG_MANAGE_ORDERS:
                newFrag = new ManageOrderFragment();
                break;
            case Constants.FRAG_MY_STORE:
                newFrag = new MyStoreFragment();
                break;
            default:
                break;
        }

        mCurrentFrag = newFrag;

        // set animation
        if (isHasAnimation) {
            fragTransaction.setCustomAnimations(
                    R.anim.view_transition_in_left,
                    R.anim.view_transition_out_right,
                    R.anim.view_transition_in_left,
                    R.anim.view_transition_out_right);
        }
        // param 1: container id, param 2: new fragment, param 3: fragment id
        fragTransaction.replace(R.id.main_container, newFrag, String.valueOf(fragId));
        // prevent showed when user press back fabReview
        //fragTransaction.addToBackStack(String.valueOf(fragId));
        fragTransaction.commit();
    }

    private void changeHeaderLayout(int fragId) {
        switch (fragId) {
            case Constants.FRAG_HOME:
                header_title.setText("");
                break;
            case Constants.FRAG_MANAGE_PRODUCTS:
                header_title.setText("");
                break;
            case Constants.FRAG_MANAGE_ORDERS:
                header_title.setText("");
                break;
            case Constants.FRAG_MY_STORE:
                header_title.setText("");
                break;
            case Constants.FRAG_ADD_PRODUCT:
                header_title.setText("Add Product");
                break;
            default:
                break;
        }
    }

    private void afterClickMenuItem(int fragId) {
        // add fragment by id
        addFragment(fragId, false);
        // change header layout
        changeHeaderLayout(fragId);
    }

    public void addSecondStageFragment(int destFragId, Object obj) {
        mFragManager = getSupportFragmentManager();
        // create transaction
        fragTransaction = mFragManager.beginTransaction();
        fragTransaction.setCustomAnimations(R.anim.view_transition_in_left,
                R.anim.view_transition_out_right, R.anim.view_transition_in_left,
                R.anim.view_transition_out_right);

        Bundle args = null;


        Fragment frag = null;
        switch (destFragId) {
            case Constants.FRAG_ADD_PRODUCT:
                frag = new AddProductFragment();
                changeHeaderLayout(Constants.FRAG_ADD_PRODUCT);
                break;
            default:
                break;
        }
        // add argument for sent to other fragment
        if (args != null) {
            frag.setArguments(args);
        }

        mCurrentFrag = frag;

        // param 1: container id, param 2: new fragment, param 3: fragment id
        fragTransaction.add(R.id.main_container, frag, String.valueOf(destFragId));
        fragTransaction.addToBackStack(String.valueOf(destFragId));
        fragTransaction.commit();
    }


    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int count = fragmentManager.getBackStackEntryCount();
        if (count == 0) {
            finish();
        } else {
            String title = fragmentManager.getBackStackEntryAt(count - 1).getName();
            super.onBackPressed();
            updateActionBar(Integer.valueOf(title), null);
        }
    }

    public void updateActionBar(int destFragId, Object obj) {
        String title = "";
        switch (destFragId) {
            case Constants.FRAG_ADD_PRODUCT:
                title = "";
                mCurrentFrag = null;
                break;
        }

        header_title.setText(title);
    }
}
