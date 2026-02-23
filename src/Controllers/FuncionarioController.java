package Controllers;

import Banco.FuncionarioBD;
import Model.Funcionario;
import java.util.List;

public class FuncionarioController {
    private final FuncionarioBD funcionarioBD;
    
    public FuncionarioController(){
        this.funcionarioBD = new FuncionarioBD();
    }
    
    public void cadastrar(Funcionario funcionario) throws Exception {
        this.funcionarioBD.cadastrar(funcionario);        
    }

    public void alterar(Funcionario funcionario) throws Exception {
        this.funcionarioBD.alterar(funcionario);
    }

    public void remover(Funcionario funcionario) throws Exception {
        this.funcionarioBD.remover(funcionario);
    }
    
    public Funcionario buscarPorMatricula(int numMatricula) throws Exception {
        return this.funcionarioBD.buscarPorMatricula(numMatricula);
    }
        
    public List<Funcionario> listarTodos() throws Exception {
        return this.funcionarioBD.listarTodos();
    }
}