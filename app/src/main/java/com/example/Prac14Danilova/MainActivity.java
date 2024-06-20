package com.example.Prac14Ptushkin;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView resultField;
    EditText numberField;
    TextView operationField;
    Double operand = null;
    String lastOperation = "=";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultField = findViewById(R.id.resultField);
        numberField = findViewById(R.id.numberField);
        operationField = findViewById(R.id.operationField);

        findViewById(R.id.imageButton10).setOnClickListener((view)->onOperationClick("+"));
        findViewById(R.id.imageButton14).setOnClickListener((view)->onOperationClick("-"));
        findViewById(R.id.imageButton18).setOnClickListener((view)->onOperationClick("*"));
        findViewById(R.id.imageButton17).setOnClickListener((view)->onOperationClick("/"));
        findViewById(R.id.imageButton3).setOnClickListener((view)->onOperationClick("="));

        findViewById(R.id.imageButton21).setOnClickListener((view)->onNumberClick("0"));
        findViewById(R.id.imageButton4).setOnClickListener((view)->onNumberClick("1"));
        findViewById(R.id.imageButton5).setOnClickListener((view)->onNumberClick("2"));
        findViewById(R.id.imageButton6).setOnClickListener((view)->onNumberClick("3"));
        findViewById(R.id.imageButton7).setOnClickListener((view)->onNumberClick("4"));
        findViewById(R.id.imageButton8).setOnClickListener((view)->onNumberClick("5"));
        findViewById(R.id.imageButton9).setOnClickListener((view)->onNumberClick("6"));
        findViewById(R.id.imageButton11).setOnClickListener((view)->onNumberClick("7"));
        findViewById(R.id.imageButton12).setOnClickListener((view)->onNumberClick("8"));
        findViewById(R.id.imageButton13).setOnClickListener((view)->onNumberClick("9"));
        findViewById(R.id.imageButton2).setOnClickListener((view)->onNumberClick(","));
        findViewById(R.id.imageButton19).setOnClickListener((view)->resultField.setText(""));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("OPERATION", lastOperation);
        if(operand!=null)
            outState.putDouble("OPERAND", operand);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        lastOperation = savedInstanceState.getString("OPERATION");
        operand= savedInstanceState.getDouble("OPERAND");
        resultField.setText(operand.toString());
        operationField.setText(lastOperation);
    }

    public void onNumberClick(String number){
        numberField.append(number);
        if(lastOperation.equals("=") && operand!=null){
            operand = null;
        }
    }

    public void onOperationClick(String op){

        String number = numberField.getText().toString();

        if(number.length()>0){
            number = number.replace(',', '.');
            try{
                performOperation(Double.valueOf(number), op);
            }catch (NumberFormatException ex){
                numberField.setText("");
            }
        }
        lastOperation = op;
        operationField.setText(lastOperation);
    }

    private void performOperation(Double number, String operation){


        if(operand ==null){
            operand = number;
        }
        else{
            if(lastOperation.equals("=")){
                lastOperation = operation;
            }
            switch(lastOperation){
                case "=":
                    operand =number;
                    break;
                case "/":
                    if(number==0){
                        operand =0.0;
                    }
                    else{
                        operand /=number;
                    }
                    break;
                case "*":
                    operand *=number;
                    break;
                case "+":
                    operand +=number;
                    break;
                case "-":
                    operand -=number;
                    break;
            }
        }
        resultField.setText(operand.toString().replace('.', ','));
        numberField.setText("");
    }
}