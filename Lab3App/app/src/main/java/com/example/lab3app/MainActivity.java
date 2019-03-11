package com.example.lab3app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public class PhoneDialerActivity extends AppCompatActivity {

        private EditText phoneNumberEditText;
        private ImageButton callImageButton;
        private ImageButton hangupImageButton;
        private ImageButton backspaceImageButton;
        private Button genericButton;

        private CallImageButtonClickListener callImageButtonClickListener = new CallImageButtonClickListener();
        private class CallImageButtonClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                ProgressBar pr = null;
                if (ContextCompat.checkSelfPermission(PhoneDialerActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                            PhoneDialerActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE},
                            Constants.PERMISSION_REQUEST_CALL_PHONE);
                } else {
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + phoneNumberEditText.getText().toString()));
                    startActivity(intent);
                }
            }
        }

        private HangupImageButtonClickListener hangupImageButtonClickListener = new HangupImageButtonClickListener();
        private class HangupImageButtonClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                finish();
            }
        }

        private BackspaceButtonClickListener backspaceButtonClickListener = new BackspaceButtonClickListener();
        private class BackspaceButtonClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                String phoneNumber = phoneNumberEditText.getText().toString();
                if (phoneNumber.length() > 0) {
                    phoneNumber = phoneNumber.substring(0, phoneNumber.length() - 1);
                    phoneNumberEditText.setText(phoneNumber);
                }
            }
        }

        private GenericButtonClickListener genericButtonClickListener = new GenericButtonClickListener();
        private class GenericButtonClickListener implements View.OnClickListener {
            @Override
            public void onClick(View view) {
                phoneNumberEditText.setText(phoneNumberEditText.getText().toString() + ((Button)view).getText().toString());
            }
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_phone_dialer);
            //setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

            phoneNumberEditText = (EditText)findViewById(R.id.phone_number_edit_text);
            callImageButton = (ImageButton)findViewById(R.id.call_image_button);
            callImageButton.setOnClickListener(callImageButtonClickListener);
            hangupImageButton = (ImageButton)findViewById(R.id.hangup_image_button);
            hangupImageButton.setOnClickListener(hangupImageButtonClickListener);
            backspaceImageButton = (ImageButton)findViewById(R.id.backspace_image_button);
            backspaceImageButton.setOnClickListener(backspaceButtonClickListener);
            for (int index = 0; index < Constants.buttonIds.length; index++) {
                genericButton = (Button)findViewById(Constants.buttonIds[index]);
                genericButton.setOnClickListener(genericButtonClickListener);
            }
        }
    }
}
