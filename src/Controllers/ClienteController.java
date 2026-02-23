package Controllers;

import Banco.ClienteBD;
import Model.Cliente;
import java.util.List;

public class ClienteController {
    private final ClienteBD clienteBD;
    
    public ClienteController(){
        this.clienteBD = new ClienteBD();
    }
    
    public void cadastrar(Cliente cliente) throws Exception {
        this.clienteBD.cadastrar(cliente);        
    }

    public void alterar(Cliente cliente) throws Exception {
        this.clienteBD.alterar(cliente);
    }

    public void remover(Cliente cliente) throws Exception {
        this.clienteBD.remover(cliente);
    }
    
    public Cliente buscarPorCpf(String cpf) throws Exception {
        return this.clienteBD.buscarPorCpf(cpf);
    }
        
    public List<Cliente> listarTodos() throws Exception {
        return this.clienteBD.listarTodos();
    }
}