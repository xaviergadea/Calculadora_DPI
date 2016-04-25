package info.wwwood.calculadora_dpi;

import android.content.Context;
import android.graphics.Camera;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.OrientationEventListener;
import android.view.Surface;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ET_ResolucionVertical,ET_ResolucionHorizontal,ET_Pulgadas;
    private TextView TV_DPI_Value;
    private Button BT_Calcular;
    private Display display;

    private TextView main_LAND_TV_ResolucionVertical,main_LAND_TV_RV,main_LAND_TV_RH,main_LAND_TV_ResolucionHorizontal,main_LAND_TV_Pulgadas,main_LAND_TV_DPI_Value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        display=((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        
        if (display.getRotation()== Surface.ROTATION_0 || display.getRotation()== Surface.ROTATION_180){
            //MODO VERTICAL
            modoVertical();
        } else {
            modoHorizontal();
        }
    }

    @Override
    public void onClick(View v) {


        switch (v.getId()){
            case R.id.BT_Calcular:
                if( ET_ResolucionVertical.getText().toString().trim().equals("") || ET_ResolucionHorizontal.getText().toString().trim().equals("") || ET_Pulgadas.getText().toString().trim().equals("")) {
                    Toast.makeText(this,"Has d'omplir tots els camps!",Toast.LENGTH_LONG).show();
                } else {
                    Double H = Double.parseDouble(ET_ResolucionVertical.getText().toString());
                    Double W = Double.parseDouble(ET_ResolucionHorizontal.getText().toString());
                    Double pulgadas = Double.parseDouble(ET_Pulgadas.getText().toString());
                    //Double diagonal = Math.sqrt(Math.pow(W, 2) + (Math.pow(H, 2)));
                    Double diagonal=Math.hypot(H,W);
                    Double Resultat = (diagonal / pulgadas);
                    //TV_DPI_Value.setText(Resultat.toString());
                    TV_DPI_Value.setText(String.format("%.2f",Resultat));
                }
                break;


        }
    }
    double roundTwoDecimals(double d)
    {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }
    private void modoVertical(){
        ET_ResolucionVertical = (EditText) findViewById(R.id.ET_ResolucionVertical);
        ET_ResolucionHorizontal = (EditText) findViewById(R.id.ET_ResolucionHorizontal);
        ET_Pulgadas = (EditText) findViewById(R.id.ET_Pulgadas);
        TV_DPI_Value=(TextView) findViewById(R.id.TV_DPI_Value);

        BT_Calcular = (Button) findViewById((R.id.BT_Calcular));
        BT_Calcular.setOnClickListener(this); //IMPLEMENTEM CONTROLADORA DE CLICKS

    }
    private void modoHorizontal(){
        main_LAND_TV_ResolucionVertical = (TextView) findViewById(R.id.main_LAND_TV_ResolucionVertical);
        main_LAND_TV_ResolucionHorizontal = (TextView) findViewById(R.id.main_LAND_TV_ResolucionHorizontal);
        main_LAND_TV_Pulgadas = (TextView) findViewById(R.id.main_LAND_TV_Pulgadas);
        main_LAND_TV_DPI_Value=(TextView) findViewById(R.id.main_LAND_TV_DPI_Value);


        DisplayMetrics displayMetrics=new DisplayMetrics();

        display.getMetrics(displayMetrics);
        display.getRealMetrics(displayMetrics); //fa el mateix que getMetrics però real

        main_LAND_TV_ResolucionVertical.setText(String.valueOf(displayMetrics.heightPixels));
        main_LAND_TV_ResolucionHorizontal.setText(String.valueOf(displayMetrics.widthPixels));
        main_LAND_TV_DPI_Value.setText(String.valueOf(displayMetrics.densityDpi));

        main_LAND_TV_Pulgadas.setText(String.valueOf((Math.hypot(displayMetrics.heightPixels,displayMetrics.widthPixels)/displayMetrics.densityDpi)));





    }
}
