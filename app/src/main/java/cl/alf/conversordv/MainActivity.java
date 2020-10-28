package cl.alf.conversordv;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    final String[] datos = new String[] {"USD", "EUR","CLP"};

    private Spinner monedaActualSP;
    private Spinner monedaCambioSP;
    private EditText valorCambioET;
    private TextView resultadoTV;

    final private double factorUsdEur = 0.87;
    final private double factorClpUsd = 0.54;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,datos);

        monedaActualSP = (Spinner) findViewById(R.id.monedaActualSP);

        monedaActualSP.setAdapter(adaptador);
    }

    public void clickConvertir(View v){
        monedaActualSP = (Spinner) findViewById(R.id.monedaActualSP);
        monedaCambioSP = (Spinner) findViewById(R.id.monedaCambioSP);
        valorCambioET = (EditText) findViewById(R.id.valorCambioET);
        resultadoTV = (TextView) findViewById(R.id.resultadoTV);

        String monedaActual = monedaActualSP.getSelectedItem().toString();
        String monedaCambio = monedaCambioSP.getSelectedItem().toString();

        double valorCambio = Double.parseDouble(valorCambioET.getText().toString());

        double resultado = procesarConversion(monedaActual,monedaCambio,valorCambio);

        if(resultado>0){
            resultadoTV.setText(String.format("Por %5.2f %s, El valor de cambio es: %5.2f %s", valorCambio,monedaActual,resultado,monedaCambio));
            valorCambioET.setText("");
        }else{
            resultadoTV.setText(String.format("Usted recibirá"));
            Toast.makeText(MainActivity.this, "Las opciones elegidas no tienen un factor de conversión", Toast.LENGTH_SHORT).show();
        }
    }

    private double procesarConversion(String monedaActual, String monedaCambio, double valorCambio){

        double resultadoConversion =0;
        switch (monedaActual){
            case "USD":
                if(monedaCambio.equals("EUR")){
                    resultadoConversion = valorCambio * factorUsdEur;
                }
                if (monedaCambio.equals("CLP")){
                    resultadoConversion = valorCambio * factorClpUsd;
                }
                break;
            case "EUR":
                if(monedaCambio.equals("USD")){
                    resultadoConversion = valorCambio / factorUsdEur;
                }
                break;
            case "CLP":
                if(monedaCambio.equals("USD")){
                    resultadoConversion = valorCambio / factorClpUsd;
                }
                break;
        }

        return 0;
    }
}