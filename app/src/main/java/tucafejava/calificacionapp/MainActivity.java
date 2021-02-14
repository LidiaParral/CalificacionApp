/**
 * Tu Cafe Java
 * @author Ing. Dick Díaz Delgado
 * https://tucafejava.blogspot.com/
 * Ejercicio 02
 **/
package tucafejava.calificacionapp;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    EditText talumno;
    EditText tcalificacion;
    Button mostrar;
    Button limpiar;
    TextView resultado;

    AlertDialog.Builder dialog;

    String salumno;
    String scalificacion;
    String sresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultado = (TextView) findViewById(R.id.resultado);
        mostrar = (Button) findViewById(R.id.mostrar);
        limpiar = (Button) findViewById(R.id.limpiar);
        talumno = (EditText) findViewById(R.id.alumno);
        tcalificacion = (EditText) findViewById(R.id.calificacion);

        limpiar.setEnabled(false);

        mostrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salumno = talumno.getText().toString();
                scalificacion = tcalificacion.getText().toString();
                if(salumno.length() == 0){
                    dialog = new AlertDialog.Builder(MainActivity.this);
                    dialog.setTitle("Error");
                    dialog.setMessage("Ingresar el nombre del alumno");
                    dialog.setCancelable(false);
                    dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialogo, int id) {
                            dialogo.cancel();
                            talumno.requestFocus();
                        }
                    });
                    dialog.show();
                }else{
                    if(scalificacion.length() == 0){
                        dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("Error");
                        dialog.setMessage("Ingresar la calificación del alumno");
                        dialog.setCancelable(false);
                        dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialogo, int id) {
                                dialogo.cancel();
                                tcalificacion.requestFocus();
                            }
                        });
                        dialog.show();
                    }else{
                        double cal = Double.parseDouble(scalificacion);
                        sresultado = "";
                        if(cal>=0 && cal<=20){
                            if(cal <= 10.5){
                                sresultado = "Malo";
                            }else{
                                if(cal <= 14){
                                    sresultado = "Regular";
                                }else{
                                    if(cal <= 18){
                                        sresultado = "Bueno";
                                    }else{
                                        sresultado = "Excelente";
                                    }
                                }
                            }
                            limpiar.setEnabled(true);
                            mostrar.setEnabled(false);
                            resultado.setText("La condición del alumno " + salumno + " es: " + sresultado);
                        }else{
                            dialog = new AlertDialog.Builder(MainActivity.this);
                            dialog.setTitle("Error");
                            dialog.setMessage("La calificación no es la correcta, la nota es vigesimal (0 - 20)");
                            dialog.setCancelable(false);
                            dialog.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialogo, int id) {
                                    dialogo.cancel();
                                    tcalificacion.setText("");
                                    tcalificacion.requestFocus();
                                }
                            });
                            dialog.show();
                        }
                    }
                }
            }
        });

        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                limpiar_controles();
            }
        });
    }

    public void limpiar_controles(){
        limpiar.setEnabled(false);
        tcalificacion.setText("");
        talumno.setText("");
        resultado.setText("");
        talumno.requestFocus();
        mostrar.setEnabled(true);
    }
}
