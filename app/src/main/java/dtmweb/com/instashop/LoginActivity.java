package dtmweb.com.instashop;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import dtmweb.com.instashop.utils.CorrectSizeUtil;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private CorrectSizeUtil mCorrectSize = null;
    private EditText et_mail = null;
    private EditText et_password = null;
    private ImageView intagram_login = null;
    private Button btn_go = null;
    private TextView btn_forget_pass = null;
    private TextView btn_sign_up = null;
    private Context mContext = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mContext = this;
        findViews();
        initListenersForViews();
        mCorrectSize = CorrectSizeUtil.getInstance(this);
        mCorrectSize.correctSize();
    }

    private void initListenersForViews() {
        btn_go.setOnClickListener(this);
        btn_forget_pass.setOnClickListener(this);
        btn_sign_up.setOnClickListener(this);

    }

    private void findViews() {
        et_mail = (EditText) findViewById(R.id.et_mail);
        et_password = (EditText) findViewById(R.id.et_password);
        intagram_login = (ImageView) findViewById(R.id.intagram_login);
        btn_go = (Button) findViewById(R.id.btn_go);
        btn_forget_pass = (TextView) findViewById(R.id.btn_forget_pass);
        btn_sign_up = (TextView) findViewById(R.id.btn_sign_up);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_go:
                afterClickSumbit();
                break;
            case R.id.btn_forget_pass:
                afterClickForgetPassword();
                break;
            case R.id.btn_sign_up:
                afterClickSignUp();
                break;
        }
    }

    private void afterClickSignUp() {
        startActivity(new Intent(mContext,UserCategoryActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }

    private void afterClickForgetPassword() {
    }

    private void afterClickSumbit() {
        goToMainPage();
    }

    private void goToMainPage(){
        startActivity(new Intent(mContext,MainActivity.class));
        overridePendingTransition(R.anim.anim_slide_in_right,R.anim.anim_slide_out_left);
    }
}
