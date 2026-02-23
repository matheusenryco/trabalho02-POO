package Controllers;

import Banco.VendasDB;
import Model.Vendas;
import java.util.List;

public class VendasController {
    private final VendasDB vendasDB;

    public VendasController(){
        this.vendasDB = new VendasDB();
    }

    public void cadastrar(Vendas venda) throws Exception {
        this.vendasDB.cadastrar(venda);
    }

    public void alterar(Vendas venda, int id) throws Exception {
        this.vendasDB.alterar(venda, id);
    }

    public void remover(int id) throws Exception {
        this.vendasDB.remover(id);
    }

    public List<Vendas> listarTodos() throws Exception {
        return this.vendasDB.listarTodos();
    }

    public Vendas buscarPorCpf(String cpf) throws Exception {
        return this.vendasDB.buscarPorCpf(cpf);
    }
}