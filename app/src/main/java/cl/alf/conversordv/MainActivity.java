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

    final String[] datos = new String[] {"USD", "EUR","CLP","NZD","JPY","BCV"};
    //Creacion del array con la denominacion de las monedas

    private Spinner monedaActualSP;
    private Spinner monedaCambioSP;
    private EditText valorCambioET;
    private TextView resultadoTV;
    //Se define la variable de cada control

    //se definen valores de factor conversion
    final private double factorClpEur = 0.0011;
    final private double factorClpUsd = 0.0013;
    final private double factorClpNzd = 0.0020;
    final private double factorClpJpy = 0.14;
    final private double factorClpBcv = 670.42;
    final private double factorEurUsd = 1.16;
    final private double factorEurNzd = 1.76;
    final private double factorEurJpy = 181.83;
    final private double factorEurBcv = 603457.76;
    final private double factorUsdNzd = 1.52;
    final private double factorUsdJpy = 104.66;
    final private double factorUsdBcv = 518434.50;
    final private double factorNzdJpy = 69.05;
    final private double factorNzdBcv = 342029.38;
    final private double factorJpyBcv = 4951.90;

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
            case "CLP":
                if(monedaCambio.equals("EUR")){
                    resultadoConversion = valorCambio * factorClpEur;
                }
                if (monedaCambio.equals("USD")){
                    resultadoConversion = valorCambio * factorClpUsd;
                }
                if(monedaCambio.equals("NZD")){
                    resultadoConversion = valorCambio * factorClpNzd;
                }
                if(monedaCambio.equals("JPY")){
                    resultadoConversion = valorCambio * factorClpJpy;
                }
                if(monedaCambio.equals("BCV")){
                    resultadoConversion = valorCambio * factorClpBcv;
                }
                break;
            case "EUR":
                if(monedaCambio.equals("CLP")){
                    resultadoConversion = valorCambio / factorClpEur;
                }
                if(monedaCambio.equals("USD")){
                    resultadoConversion = valorCambio * factorEurUsd;
                }
                if(monedaCambio.equals("NZD")){
                    resultadoConversion = valorCambio * factorEurNzd;
                }
                if(monedaCambio.equals("JPY")){
                    resultadoConversion = valorCambio * factorEurJpy;
                }
                if(monedaCambio.equals("BCV")){
                    resultadoConversion = valorCambio * factorEurBcv;
                }
                break;
            case "USD":
                if(monedaCambio.equals("CLP")){
                    resultadoConversion = valorCambio / factorClpUsd;
                }
                if(monedaCambio.equals("EUR")){
                    resultadoConversion = valorCambio / factorEurUsd;
                }
                if(monedaCambio.equals("NZD")){
                    resultadoConversion = valorCambio * factorUsdNzd;
                }
                if(monedaCambio.equals("JPY")){
                    resultadoConversion = valorCambio * factorUsdJpy;
                }
                if(monedaCambio.equals("BCV")){
                    resultadoConversion = valorCambio * factorUsdBcv;
                }
                break;
            case "NZD":
                if(monedaCambio.equals("CLP")){
                    resultadoConversion = valorCambio / factorClpNzd;
                }
                if(monedaCambio.equals("EUR")){
                    resultadoConversion = valorCambio / factorEurNzd;
                }
                if(monedaCambio.equals("USD")){
                    resultadoConversion = valorCambio / factorUsdNzd;
                }
                if(monedaCambio.equals("JPY")){
                    resultadoConversion = valorCambio * factorNzdJpy;
                }
                if(monedaCambio.equals("BCV")){
                    resultadoConversion = valorCambio * factorNzdBcv;
                }
                break;
            case "JPY":
                if(monedaCambio.equals("CLP")){
                    resultadoConversion = valorCambio / factorClpJpy;
                }
                if(monedaCambio.equals("EUR")){
                    resultadoConversion = valorCambio / factorEurJpy;
                }
                if(monedaCambio.equals("USD")){
                    resultadoConversion = valorCambio / factorUsdJpy;
                }
                if(monedaCambio.equals("NZD")){
                    resultadoConversion = valorCambio / factorNzdJpy;
                }
                if(monedaCambio.equals("BCV")){
                    resultadoConversion = valorCambio * factorJpyBcv;
                }
                break;
            case "BCV":
                if(monedaCambio.equals("CLP")){
                    resultadoConversion = valorCambio / factorClpBcv;
                }
                if(monedaCambio.equals("EUR")){
                    resultadoConversion = valorCambio / factorEurBcv;
                }
                if(monedaCambio.equals("USD")){
                    resultadoConversion = valorCambio / factorUsdBcv;
                }
                if(monedaCambio.equals("NZD")){
                    resultadoConversion = valorCambio / factorNzdBcv;
                }
                if(monedaCambio.equals("JPY")){
                    resultadoConversion = valorCambio / factorJpyBcv;
                }
                break;
        }

        return resultadoConversion;
    }
}