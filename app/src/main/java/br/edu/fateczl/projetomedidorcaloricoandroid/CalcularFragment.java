package br.edu.fateczl.projetomedidorcaloricoandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.edu.fateczl.projetomedidorcaloricoandroid.controller.AtividadeCalculadaController;
import br.edu.fateczl.projetomedidorcaloricoandroid.controller.ExercicioPersonalizadoController;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeCalculada;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeFisica;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.ExercicioPersonalizado;
import br.edu.fateczl.projetomedidorcaloricoandroid.persistence.AtividadeCalculadaDao;
import br.edu.fateczl.projetomedidorcaloricoandroid.persistence.ExercicioPersonalizadoDao;


public class CalcularFragment extends Fragment {
    private View view;
    private EditText etTempoCalcular, etPesoCalcular;
    private TextView tvSaidaCalcular;
    private Button btnCalcularCaloria;
    private Spinner spAtividadesCalcular;

    private AtividadeCalculadaController aCont;
    private ExercicioPersonalizadoController exCont;

    public CalcularFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_calcular, container, false);
        etTempoCalcular = view.findViewById(R.id.etTempoCalcular);
        etPesoCalcular = view.findViewById(R.id.etPesoCalcular);
        tvSaidaCalcular = view.findViewById(R.id.tvSaidaCalcular);
        spAtividadesCalcular = view.findViewById(R.id.spAtividadesCalcular);
        btnCalcularCaloria = view.findViewById(R.id.btnCalcularCaloria);

        aCont = new AtividadeCalculadaController(new AtividadeCalculadaDao(view.getContext()));
        exCont = new ExercicioPersonalizadoController(new ExercicioPersonalizadoDao(view.getContext()));
        preencheSpinner();

        btnCalcularCaloria.setOnClickListener(op -> calcularCaloria());

        return view;
    }

    private void calcularCaloria() {
        if (spAtividadesCalcular.getSelectedItemPosition() > 0) {
            if (etTempoCalcular.getText().toString().isEmpty() || etPesoCalcular.getText().toString().isEmpty()){
                Toast.makeText(view.getContext(), "Preencha os campos", Toast.LENGTH_LONG).show();
            } else {
                int tempo = Integer.parseInt(etTempoCalcular.getText().toString());
                double peso = Double.parseDouble(etTempoCalcular.getText().toString());
                AtividadeFisica at = (AtividadeFisica) spAtividadesCalcular.getSelectedItem();

                double gastoCAlorico = (at.getMet() * 3.5d * peso);
                gastoCAlorico /= 1000;
                gastoCAlorico *= 5;
                gastoCAlorico *= tempo;

                BigDecimal gc = BigDecimal.valueOf(gastoCAlorico).setScale(2, RoundingMode.HALF_UP);

                String saida = getText(R.string.saida_calorias) + " " + gc + " Kcal";
                tvSaidaCalcular.setText(saida);
            }
        } else {
            Toast.makeText(view.getContext(), "Escolha uma atividade", Toast.LENGTH_LONG).show();
        }

    }

    private void preencheSpinner() {
        AtividadeFisica at = new AtividadeCalculada();
        at.setCodigo(0);
        at.setNome("Selecione uma atividade:");
        at.setMet(0);
        at.setDescricao("");
        at.setTipo("");

        try {
            List<AtividadeFisica> lista = new ArrayList<>();
            List<AtividadeCalculada> aLista = aCont.listar();
            List<ExercicioPersonalizado> exLista = exCont.listar();
            lista.addAll(aLista);
            lista.addAll(exLista);
            lista.add(0, (AtividadeCalculada) at);


            ArrayAdapter ad = new ArrayAdapter(view.getContext(),
                    android.R.layout.simple_spinner_item, lista);

            ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            spAtividadesCalcular.setAdapter(ad);

        } catch (SQLException e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}