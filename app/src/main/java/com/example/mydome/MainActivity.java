package com.example.mydome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText editText1,editText,editText3;
    private EditText editText2;
    private String biao = "no";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Toast.makeText(MainActivity.this,"11111获取到焦点",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"11111失去到焦点",Toast.LENGTH_SHORT).show();
                }
            }
        });
        editText2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                   Toast.makeText(MainActivity.this,"22222获取到焦点",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"222222失去到焦点",Toast.LENGTH_SHORT).show();
                }
            }
        });
        editText3.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Toast.makeText(MainActivity.this,"33333获取到焦点",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"3333333失去到焦点",Toast.LENGTH_SHORT).show();
                }
            }
        });

        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    Toast.makeText(MainActivity.this,"获取到焦点",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"失去到焦点",Toast.LENGTH_SHORT).show();
                }
            }
        });
        editText.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(editText1.hasFocus()||editText2.hasFocus()||editText3.hasFocus()){
                    biao = "editText";
                }
                return false;
            }
        });
        editText1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(editText.hasFocus()||editText2.hasFocus()||editText3.hasFocus()){
                    biao = "editText";
                }
                return false;
            }
        });
        editText2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(editText.hasFocus()||editText1.hasFocus()||editText3.hasFocus()){
                    biao = "editText2";
                }
                return false;
            }
        });
        editText3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(editText.hasFocus()||editText1.hasFocus()||editText2.hasFocus()){
                    biao = "editText3";
                }
                return false;
            }
        });

        KeyboardStateObserver.getKeyboardStateObserver(this).
                setKeyboardVisibilityListener(new KeyboardStateObserver.OnKeyboardVisibilityListener() {
                    @Override
                    public void onKeyboardShow() {
                        biao = "no";
                    }

                    @Override
                    public void onKeyboardHide() {
                        if(editText.hasFocus()&&!biao.equals("editText")){
                            editText.clearFocus();
                        }
                        if(editText1.hasFocus()&&!biao.equals("editText1")){
                            editText1.clearFocus();
                        }
                        if(editText2.hasFocus()&&!biao.equals("editText2")){
                            editText2.clearFocus();
                        }
                        if(editText3.hasFocus()&&!biao.equals("editText3")){
                            editText3.clearFocus();
                        }
                    }
                });
    }
}
