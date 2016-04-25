package info.wwwood.calculadora_dpi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button BT_Calcular = (Button) findViewById((R.id.BT_Calcular));
        BT_Calcular.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS

    }

    @Override
    public void onClick(View v) {
        EditText ET_ResolucionVertical = (EditText) findViewById(R.id.ET_ResolucionVertical);
        EditText ET_ResolucionHorizontal = (EditText) findViewById(R.id.ET_ResolucionHorizontal);
        EditText ET_Pulgadas = (EditText) findViewById(R.id.ET_Pulgadas);
        TextView TV_DPI_Value=(TextView) findViewById(R.id.TV_DPI_Value);


        switch (v.getId()){
            case R.id.BT_Calcular:
                Double H= Double.parseDouble(ET_ResolucionVertical.getText().toString());
                Double W= Double.parseDouble(ET_ResolucionHorizontal.getText().toString());
                Double pulgadas=Double.parseDouble(ET_Pulgadas.getText().toString());
                Double diagonal= Math.sqrt(Math.pow(W, 2)+(Math.pow(H, 2)));
                Double Resultat=diagonal/pulgadas;
                TV_DPI_Value.setText(Resultat.toString());
                break;


        }
    }
}
