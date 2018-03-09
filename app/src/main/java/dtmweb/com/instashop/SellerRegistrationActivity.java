package dtmweb.com.instashop;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import dtmweb.com.instashop.utils.CorrectSizeUtil;

public class SellerRegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private CorrectSizeUtil mCorrectSize = null;
    private ImageView btn_cross = null;
    private ImageView btn_inta_connect = null;
    private ImageView btn_image_selection = null;
    private Button btn_go = null;
    private EditText et_store_name = null;
    private EditText et_password = null;
    private EditText et_password_retype = null;
    private EditText et_bank_name = null;
    private EditText et_bank_account_name = null;
    private EditText et_bank_account_number = null;
    private EditText et_country = null;
    private EditText et_city = null;
    private EditText et_address = null;
    private EditText et_store_owner_name = null;
    private EditText et_store_owner_contact = null;
    private Context mContext = null;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        afterClickBack();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_registration);
        mContext = this;
        findViews();
        initListenersForViews();
        mCorrectSize = CorrectSizeUtil.getInstance(this);
        mCorrectSize.correctSize();
    }

    private void findViews() {
        btn_cross = (ImageView) findViewById(R.id.btn_cross);
        btn_inta_connect = (ImageView) findViewById(R.id.btn_inta_connect);
        btn_image_selection = (ImageView) findViewById(R.id.btn_image_selection);
        btn_go = (Button) findViewById(R.id.btn_go);
        et_store_name = (EditText) findViewById(R.id.et_store_name);
        et_password = (EditText) findViewById(R.id.et_password);
        et_password_retype = (EditText) findViewById(R.id.et_password_retype);
        et_bank_name = (EditText) findViewById(R.id.et_bank_name);
        et_bank_account_name = (EditText) findViewById(R.id.et_bank_account_name);
        et_bank_account_number = (EditText) findViewById(R.id.et_bank_account_number);
        et_country = (EditText) findViewById(R.id.et_country);
        et_city = (EditText) findViewById(R.id.et_city);
        et_address = (EditText) findViewById(R.id.et_address);
        et_store_owner_name = (EditText) findViewById(R.id.et_store_owner_name);
        et_store_owner_contact = (EditText) findViewById(R.id.et_store_owner_contact);

    }

    private void initListenersForViews() {
        btn_go.setOnClickListener(this);
        btn_cross.setOnClickListener(this);
        btn_inta_connect.setOnClickListener(this);
        btn_image_selection.setOnClickListener(this);
        et_country.setOnClickListener(this);
        et_city.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go:
                afterClickSumbit();
                break;
            case R.id.btn_cross:
                afterClickBack();
                break;
            case R.id.btn_inta_connect:
                afterClickIntaConnect();
                break;
            case R.id.btn_image_selection:
                afterClickImageSelection();
                break;
            case R.id.et_country:
                afterClickCountry();
                break;
            case R.id.et_city:
                afterClickCity();
                break;

        }
    }

    private void afterClickCity() {
    }

    private void afterClickCountry() {
    }

    private void afterClickImageSelection() {
    }

    private void afterClickIntaConnect() {
    }

    private void afterClickBack() {
        finish();
        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_right);
    }

    private void afterClickSumbit() {
        //go to main after checking validity and api call
        goToMainPage();
    }
    private void goToMainPage(){
        startActivity(new Intent(mContext,MainActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }
}
