package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private EditText firstValueEdit;
    private EditText secondValueEdit;

    private Button plusButton;
    private Button minusButton;

    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCalculator();

        // Переход на другое активити
        Button goToSecondActivity = findViewById(R.id.go_to_second_activity);
        goToSecondActivity.setOnClickListener(v -> {
            Intent startSecondActivity = new Intent(this, SecondActivity.class);
            startSecondActivity.putExtra("chat_id", "some_chat_identifier");  // Передаем некоторое значение во второе активити
            startActivity(startSecondActivity);
        });

        // Кнопка перехода по ссылке
        Button openUri = findViewById(R.id.open_uri);  // инициализировали кнопку
        openUri.setOnClickListener(v -> {  // Привязали действие
            Uri uri = Uri.parse("https://vk.com/rtuitlab");  // создали адрес
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);  // создаем экземпляр перехода по ссылке

            ComponentName componentName = intent.resolveActivity(getPackageManager()); // Проверяем, есть ли компонент, который сможет открыть активити
            Log.d(TAG, componentName.toString());
            if (componentName != null) {
                if (!componentName.toString().contains("ResolverActivity")) {

                    Intent chooserIntent = Intent.createChooser(intent, "Select app");// создаем запрос в системе на открытие приложения
                    startActivity(chooserIntent); // предлагаем принудительный выбор
                } else {
                    startActivity(intent);  // принимает поля intent
                }

            } else {
                Log.w(TAG, "Sorry");
            }
        });

        Button shareText = findViewById(R.id.share_text);  // Получаем кнопку
        EditText shareEditText = findViewById(R.id.share_edit_text);  // Получаем текстовое поле
        shareText.setOnClickListener(v -> {  // При нажатии
            Intent intent = new Intent(Intent.ACTION_SEND); // отправить что-то (назначаем действие)
            intent.putExtra(Intent.EXTRA_TEXT, shareEditText.getText().toString());  // назначаем, что отправить
            intent.setType("text/plain"); // Mine type указываем тип данных (хороший тон)

            Intent share = Intent.createChooser(intent, "Share");  // можем изменить модуль "поделиться"
            startActivity(share); // стартуем запрос на поделиться

//            Intent.createChooser(intent, "Share");

//            startActivity(intent); // стартуем запрос на поделиться
        });

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

    private void setupCalculator() {
        firstValueEdit = findViewById(R.id.first_value_edit);
        secondValueEdit = findViewById(R.id.second_value_edit);

        plusButton = findViewById(R.id.plus_button);
        minusButton = findViewById(R.id.minus_button);

        resultText = findViewById(R.id.result_text);

        CalculationAction action = new CalculationAction() {
            @Override
            public int calculate(int firstArgument, int secondArgument) {
                return firstArgument + secondArgument;
            }
        };
        plusButton.setOnClickListener(v -> {
            calculateResults(action);
        });

        minusButton.setOnClickListener(v -> {  // Судя по всему, при попадении лямбды в calculateResults
            // она определяется как метод calculate и уже спокойно отрабатывает по нему
            calculateResults((a, b) -> a - b);
        });
    }

    private void calculateResults(CalculationAction action) {  // Передаем лямбду в action
        try {
            System.out.println(action);
            int firstArgument = Integer.parseInt(firstValueEdit.getText().toString());
            int secondArgument = Integer.parseInt(secondValueEdit.getText().toString());
            int result = action.calculate(firstArgument, secondArgument);  // Передаем в лямбду переменные
            resultText.setText("" + result);
        } catch (NumberFormatException numberFormatException) {
            Log.w(TAG, "can't calculate result", numberFormatException);
            Toast.makeText(this, "Please type correct values", Toast.LENGTH_LONG).show();
        }
    }
    private interface CalculationAction {
        int calculate(int firstArgument, int secondArgument);
    }


}