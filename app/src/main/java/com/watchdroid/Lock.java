package com.watchdroid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.arx_era.watchdroid.R;

/**
 * Created by Tronix on 17-02-2017.
 */
public class Lock extends AppCompatActivity implements View.OnClickListener {

    Button btn[] = new Button[10];


    String fsp, ssp, fsn, ssn;
    private boolean lock = false, keyloggingset;
    private EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lock_main);

        //register the buttons
        btn[0] = (Button) findViewById(R.id.btn1);
        btn[1] = (Button) findViewById(R.id.btn2);
        btn[2] = (Button) findViewById(R.id.btn3);
        btn[3] = (Button) findViewById(R.id.btn4);
        btn[4] = (Button) findViewById(R.id.btn5);
        btn[5] = (Button) findViewById(R.id.btn6);
        btn[6] = (Button) findViewById(R.id.btn7);
        btn[7] = (Button) findViewById(R.id.btn8);
        btn[8] = (Button) findViewById(R.id.btn9);
        btn[9] = (Button) findViewById(R.id.btn0);

        //register onClick event
        for (int i = 0; i < 10; i++) {
            btn[i].setOnClickListener(this);
        }

        password = (EditText) findViewById(R.id.password);
        InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        im.hideSoftInputFromWindow(password.getWindowToken(), 0);

        final SharedPreferences pref = getApplicationContext().getSharedPreferences("Pref", Context.MODE_PRIVATE);

        fsp = pref.getString("firstpsn", null);
        fsn = pref.getString("firstsn", null);
        ssp = pref.getString("secondpsn", null);
        ssn = pref.getString("secondsn", null);

        Button login = (Button) findViewById(R.id.login);
        Button back = (Button) findViewById(R.id.back);

        login.setOnClickListener(new View.OnClickListener()

                                 {
                                     @Override
                                     public void onClick(View v) {
                                         String pass = '0' + password.getText().toString();
                                         char[] pass_char = pass.toCharArray();

                                         try {
                                             int fsp_int = Integer.parseInt(fsp);
                                             int fsn_int = Integer.parseInt(fsn);
                                             int ssp_int = Integer.parseInt(ssp);
                                             int ssn_int = Integer.parseInt(ssn);

                                             for (int fcheck = 0; fcheck < pass_char.length; fcheck++) {
                                                 int editfsn_int = Integer.parseInt(String.valueOf(pass_char[fcheck]));
                                                 if (fcheck == fsp_int && fsn_int == editfsn_int) {
                                                     for (int fcheck2 = 0; fcheck2 < pass_char.length; fcheck2++) {
                                                         int editssn_int = Integer.parseInt(String.valueOf(pass_char[fcheck2]));
                                                         if (fcheck2 == ssp_int && ssn_int == editssn_int) {
                                                             lock = true;
                                                         }
                                                     }
                                                 }
                                             }
                                         } catch (NumberFormatException e) {
                                             Toast.makeText(Lock.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                         }

                                         if (lock) {
                                             Intent i = new Intent(Lock.this, MainActivity.class);
                                             startActivity(i);
                                             finish();
                                         } else {
                                             Toast.makeText(Lock.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                         }
                                     }
                                 }
        );

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteChar();
            }
        });
    }

    private void deleteChar() {
        if (password.getText().toString().length() > 0) {
            String input = password.getText().toString();
            input = input.substring(0, input.length() - 1);
            password.setText(input);
        }
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn1:
                addtoarray("1");
                break;
            case R.id.btn2:
                addtoarray("2");
                break;
            case R.id.btn3:
                addtoarray("3");
                break;
            case R.id.btn4:
                addtoarray("4");
                break;
            case R.id.btn5:
                addtoarray("5");
                break;
            case R.id.btn6:
                addtoarray("6");
                break;
            case R.id.btn7:
                addtoarray("7");
                break;
            case R.id.btn8:
                addtoarray("8");
                break;
            case R.id.btn9:
                addtoarray("9");
                break;
            case R.id.btn0:
                addtoarray("0");
                break;
            default:

        }
    }

    public void addtoarray(String numbers) {
        //register TextBox
        password.append(numbers);
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
