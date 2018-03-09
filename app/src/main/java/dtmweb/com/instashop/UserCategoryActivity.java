package dtmweb.com.instashop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import dtmweb.com.instashop.utils.CorrectSizeUtil;

public class UserCategoryActivity extends AppCompatActivity implements View.OnClickListener {

    private CorrectSizeUtil mCorrectSize = null;
    private Button btn_seller_signup = null;
    private Button btn_buyer_signup = null;
    private Context mContext = null;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_category);
        mContext = this;
        findViews();
        intitListenersForViews();
        mCorrectSize = CorrectSizeUtil.getInstance(this);
        mCorrectSize.correctSize();
    }

    private void intitListenersForViews() {
        btn_seller_signup.setOnClickListener(this);
        btn_buyer_signup.setOnClickListener(this);
    }

    private void findViews() {
        btn_seller_signup = (Button) findViewById(R.id.btn_seller_signup);
        btn_buyer_signup = (Button) findViewById(R.id.btn_buyer_signup);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_seller_signup:
                afterClickSeller();
                break;
            case R.id.btn_buyer_signup:
                afterClickBuyer();
                break;
        }
    }

    private void afterClickBuyer() {
        startActivity(new Intent(mContext, BuyerRegistrationActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }

    private void afterClickSeller() {
        startActivity(new Intent(mContext, SellerRegistrationActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right, R.anim.anim_slide_out_left);
    }
}
