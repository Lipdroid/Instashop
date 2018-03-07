package dtmweb.com.instashop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import dtmweb.com.instashop.utils.CorrectSizeUtil;

public class LoginActivity extends AppCompatActivity {
    private CorrectSizeUtil mCorrectSize = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCorrectSize = CorrectSizeUtil.getInstance(this);
        mCorrectSize.correctSize();
    }
}
