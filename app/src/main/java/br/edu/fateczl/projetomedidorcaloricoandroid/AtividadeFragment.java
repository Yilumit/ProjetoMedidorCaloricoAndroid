package br.edu.fateczl.projetomedidorcaloricoandroid;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.List;

import br.edu.fateczl.projetomedidorcaloricoandroid.controller.AtividadeCalculadaController;
import br.edu.fateczl.projetomedidorcaloricoandroid.controller.ExercicioPersonalizadoController;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeFisica;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.AtividadeCalculada;
import br.edu.fateczl.projetomedidorcaloricoandroid.model.ExercicioPersonalizado;
import br.edu.fateczl.projetomedidorcaloricoandroid.persistence.AtividadeCalculadaDao;
import br.edu.fateczl.projetomedidorcaloricoandroid.persistence.ExercicioPersonalizadoDao;


public class AtividadeFragment extends Fragment {

    private View view;
    private EditText etIdAtividade, etNomeAtividade, etMETAtividade, etDescricaoAtividade;
    private TextView tvSaidaAtividade;
//    private Spinner spTipoAtividade;
    private Button btnBuscarAtividade, btnInserirAtividade, btnModificarAtividade, btnExcluirAtividade, btnListarAtividade;
    private RadioButton rbPersonalizadaAtividade, rbCalculadaAtividade;
    private AtividadeCalculadaController aCont;
    private ExercicioPersonalizadoController exCont;
    private List<String> atividades;
    private List<AtividadeFisica> listaAtividades;

    public AtividadeFragment() {
        super();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_atividade, container, false);

        etIdAtividade = view.findViewById(R.id.etIdAtivdade);
        etNomeAtividade = view.findViewById(R.id.etNomeAtividade);
        etMETAtividade = view.findViewById(R.id.etMETAtividade);
        etDescricaoAtividade = view.findViewById(R.id.etDescricaoAtividade);
        tvSaidaAtividade = view.findViewById(R.id.tvSaidaAtividade);
        tvSaidaAtividade.setMovementMethod(new ScrollingMovementMethod());
        rbCalculadaAtividade = view.findViewById(R.id.rbCalculadaAtividade  );
        rbPersonalizadaAtividade = view.findViewById(R.id.rbPersonalizadaAtividade);
        rbPersonalizadaAtividade.setChecked(true);

        btnBuscarAtividade = view.findViewById(R.id.btnBuscarAtividade);
        btnInserirAtividade = view.findViewById(R.id.btnInserirAtividade);
        btnModificarAtividade = view.findViewById(R.id.btnModificarAtividade);
        btnExcluirAtividade = view.findViewById(R.id.btnExcluirAtividade);
        btnListarAtividade = view.findViewById(R.id.btnListarAtividade);

        aCont = new AtividadeCalculadaController(new AtividadeCalculadaDao(view.getContext()));
        exCont = new ExercicioPersonalizadoController(new ExercicioPersonalizadoDao(view.getContext()));

        rbCalculadaAtividade.setOnClickListener(v -> {
            btnInserirAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            btnModificarAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            btnExcluirAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            etDescricaoAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            etMETAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            etNomeAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            limpaCampos();
        });
        rbPersonalizadaAtividade.setOnClickListener(v -> {
            btnInserirAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            btnModificarAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            btnExcluirAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            etDescricaoAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            etMETAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            etNomeAtividade.setEnabled(!rbCalculadaAtividade.isChecked());
            limpaCampos();
        });

        btnListarAtividade.setOnClickListener(op -> listar());
        btnBuscarAtividade.setOnClickListener(op-> buscar());
        btnExcluirAtividade.setOnClickListener(op -> excluir());
        btnModificarAtividade.setOnClickListener(op -> modificar());
        btnInserirAtividade.setOnClickListener(op -> inserir());





        return view;
    }

    private void inserir() {
        if (!etIdAtividade.getText().toString().equals("") && !etNomeAtividade.getText().toString().equals("") && !etMETAtividade.getText().toString().equals("")){
            if (rbCalculadaAtividade.isChecked()) {
                AtividadeCalculada atividadeCalculada = montaAtividadeCalculada();
                try {
                    aCont.inserir(atividadeCalculada);
                    Toast.makeText(view.getContext(), "Inserido com Sucesso!", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                limpaCampos();
            } else {
                ExercicioPersonalizado exercicioPersonalizado = montaExercicioPersonalizado();
                try {
                    exCont.inserir(exercicioPersonalizado);
                    Toast.makeText(view.getContext(), "Inserido com Sucesso!", Toast.LENGTH_LONG).show();
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
                limpaCampos();
            }
        } else {
            Toast.makeText(view.getContext(), "Preencha todos os campos!", Toast.LENGTH_LONG).show();
        }
    }

    private void limpaCampos() {
        etIdAtividade.setText("");
        etNomeAtividade.setText("");
        etMETAtividade.setText("");
        etDescricaoAtividade.setText("");
        tvSaidaAtividade.setText("");
    }

    private void modificar() {
        if (etIdAtividade.getText().toString().equals("")){
            Toast.makeText(view.getContext(), "Selecione o codigo da atividade", Toast.LENGTH_LONG).show();
        } else {
            if (!etNomeAtividade.getText().toString().equals("") || !etMETAtividade.getText().toString().equals("")
                    || !etDescricaoAtividade.getText().toString().equals("")) {
                if (rbCalculadaAtividade.isChecked()) {
                    AtividadeCalculada atividadeCalculada = montaAtividadeCalculada();
                    try {
                        aCont.modificar(atividadeCalculada);
                        Toast.makeText(view.getContext(), "Atualizado com Sucesso!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                    } catch (SQLException e) {
                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                } else {
                    ExercicioPersonalizado exercicioPersonalizado = montaExercicioPersonalizado();
                    try {
                        exCont.modificar(exercicioPersonalizado);
                        Toast.makeText(view.getContext(), "Atualizado com Sucesso!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                    } catch (SQLException e) {
                        Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }
            } else {
                Toast.makeText(view.getContext(), "Modifique algo", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void excluir() {
        if (etIdAtividade.getText().toString().equals("")){
            Toast.makeText(view.getContext(), "Selecione o codigo da atividade", Toast.LENGTH_LONG).show();
        } else {
            if (rbCalculadaAtividade.isChecked()) {
                AtividadeCalculada atividadeCalculada = montaAtividadeCalculada();
                try {
                    atividadeCalculada = aCont.buscar(atividadeCalculada);
                    if (!atividadeCalculada.getNome().equals("")) {
                        aCont.excluir(atividadeCalculada);
                        Toast.makeText(view.getContext(), "Excluido com Sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(view.getContext(), "Atividade nao Encontrada!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                    }
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                ExercicioPersonalizado exercicioPersonalizado = montaExercicioPersonalizado();
                try {
                    exercicioPersonalizado = exCont.buscar(exercicioPersonalizado);
                    if (!exercicioPersonalizado.getNome().equals("")) {
                        exCont.excluir(exercicioPersonalizado);
                        Toast.makeText(view.getContext(), "Excluido com Sucesso!", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(view.getContext(), "Atividade nao Encontrada!", Toast.LENGTH_LONG).show();
                        limpaCampos();
                    }
                } catch (SQLException e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }

        }
    }

    private void buscar() {
        if (etIdAtividade.getText().toString().equals("")){
            Toast.makeText(view.getContext(), "Selecione o codigo da atividade", Toast.LENGTH_LONG).show();
        } else {
            if (rbCalculadaAtividade.isChecked()) {
                AtividadeCalculada atividadeCalculada = montaAtividadeCalculada();

                try {
                    atividadeCalculada = aCont.buscar(atividadeCalculada);
                    if (atividadeCalculada.getNome().isEmpty()){
                        Toast.makeText(view.getContext(), "Atividade nao Encontrada!", Toast.LENGTH_LONG).show();
                    } else {
                        preencheCampos(atividadeCalculada);
                    }
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            } else {
                ExercicioPersonalizado exercicioPersonalizado = montaExercicioPersonalizado();
                try {
                    exercicioPersonalizado = exCont.buscar(exercicioPersonalizado);
                    if (exercicioPersonalizado.getNome().isEmpty()){
                        Toast.makeText(view.getContext(), "Atividade nao Encontrada!", Toast.LENGTH_LONG).show();
                    } else  {
                        preencheCampos(exercicioPersonalizado);
                    }
                } catch (Exception e) {
                    Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    private void listar() {
        if (rbCalculadaAtividade.isChecked()){
            try {
                List<AtividadeCalculada> atividadeCalculadas = aCont.listar();
                StringBuffer buffer = new StringBuffer();
                for (AtividadeFisica a : atividadeCalculadas) {
                    buffer.append(a.toString()).append("\n");
                }
                tvSaidaAtividade.setText(buffer.toString());
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        } else {
            try {
                List<ExercicioPersonalizado> exercicioPersonalizados = exCont.listar();
                if (!exercicioPersonalizados.isEmpty()){
                    StringBuffer buffer = new StringBuffer();
                    for (AtividadeFisica a : exercicioPersonalizados) {
                        buffer.append(a.toString() + "\n");
                    }
                    tvSaidaAtividade.setText(buffer.toString());
                } else {
                    limpaCampos();
                    Toast.makeText(view.getContext(), "Esta vazio!", Toast.LENGTH_LONG).show();
                }
            } catch (SQLException e) {
                Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
    }

    private void preencheCampos(AtividadeFisica atividadeFisica) {
        etIdAtividade.setText(String.valueOf(atividadeFisica.getCodigo()));
        etNomeAtividade.setText(String.valueOf(atividadeFisica.getNome()));
        etMETAtividade.setText(String.valueOf(atividadeFisica.getMet()));
        etDescricaoAtividade.setText(String.valueOf(atividadeFisica.getDescricao()));
        if (atividadeFisica.getTipo().equals("Exercicio Calculado")){
            rbCalculadaAtividade.setChecked(true);
        } else {
            rbPersonalizadaAtividade.setChecked(true);
        }
    }

    private ExercicioPersonalizado montaExercicioPersonalizado() {
        ExercicioPersonalizado exercicioPersonalizado = new ExercicioPersonalizado();
        try {
            exercicioPersonalizado.setCodigo(parseInt(etIdAtividade.getText().toString()));
            exercicioPersonalizado.setNome(etNomeAtividade.getText().toString());
            exercicioPersonalizado.setMet(parseFloat(etMETAtividade.getText().toString()));
            exercicioPersonalizado.setDescricao(String.valueOf(etDescricaoAtividade.getText()));
            exercicioPersonalizado.setTipo("Exercicio Personalizado");
        }  catch (Exception e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }

        return exercicioPersonalizado;
    }

    private AtividadeCalculada montaAtividadeCalculada(){
        AtividadeCalculada atividadeCalculada = new AtividadeCalculada();
        try {
            atividadeCalculada.setCodigo(parseInt(etIdAtividade.getText().toString()));
            atividadeCalculada.setNome(etNomeAtividade.getText().toString());
            atividadeCalculada.setMet(parseFloat(etMETAtividade.getText().toString()));
            atividadeCalculada.setDescricao(String.valueOf(etDescricaoAtividade.getText()));
            atividadeCalculada.setTipo("Exercicio Calculado");

        }  catch (Exception e) {
            Toast.makeText(view.getContext(), e.getMessage(), Toast.LENGTH_LONG).show();
        }
        return atividadeCalculada;

    }
    int parseInt(String strNumber) {
        if (strNumber != null && !strNumber.isEmpty()) {
            try {
                return Integer.parseInt(strNumber);
            } catch(NumberFormatException e) {
                return -1;
            }
        }
        else return 0;
    }
    float parseFloat(String strNumber) {
        if (strNumber != null && !strNumber.isEmpty()) {
            try {
                return Float.parseFloat(strNumber);
            } catch(NumberFormatException e) {
                return -1;
            }
        }
        else return 0;
    }

}