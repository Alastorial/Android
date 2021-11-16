package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private static final String TAG = "SecondActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.i(TAG, "onCreate");

        Intent intent = getIntent(); // Инициализируем класс
        String chat_id = intent.getStringExtra("chat_id");  // Создаем переменную chat_id и кладем передаваемые данные

        if (chat_id == null) {
            Log.w(TAG, "Incorrect intent extra, please specifi 'chat_id'");
            finish();
            return;
        }

        TextView textView = findViewById(R.id.chat_id);  // инициализируем кнопку в переменную textView
        textView.setText(chat_id);  // Получили значение из прошлой активити и дали тексту
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
    }
}