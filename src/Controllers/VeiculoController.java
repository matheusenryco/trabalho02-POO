package Controllers;

import Banco.VeiculoBD;
import Model.Veiculo;
import java.util.List;

public class VeiculoController {
    private final VeiculoBD veiculoBD;

    public VeiculoController(){
        this.veiculoBD = new VeiculoBD();
    }
    
    public void cadastrar(Veiculo veiculo) throws Exception {
        this.veiculoBD.cadastrar(veiculo);
    }

    public void alterar(Veiculo veiculo) throws Exception {
        this.veiculoBD.alterar(veiculo);
    }

    public void remover(Veiculo veiculo) throws Exception {
        this.veiculoBD.remover(veiculo);
    }
    
    public List<Veiculo> listarTodos() throws Exception {
        return this.veiculoBD.listarTodos();
    }

    public Veiculo buscarPorChassi(String chassi) throws Exception {
        return this.veiculoBD.buscarPorChassi(chassi);
    }
}