package Banco;

import Model.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteBD {
    private final SQLiteDriver sqLiteDriver;
    private final Connection conexao;

    private void criarTabela(){
        String sql = "CREATE TABLE IF NOT EXISTS Clientes (" +
                "   cpf text PRIMARY KEY NOT NULL," +
                "   nome text NOT NULL," +
                "   telefone text NOT NULL," +
                "   email text NOT NULL," +
                "   rg text NOT NULL," +
                "   UNIQUE(rg)" +
                ");";

        try (Statement stmt = this.conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public ClienteBD(){
        this.sqLiteDriver = new SQLiteDriver("Concessionaria");
        this.conexao = sqLiteDriver.iniciarConexao();
        this.criarTabela();
    }

    public void cadastrar(Cliente cliente) throws Exception {
        String sql = "INSERT INTO Clientes (cpf, nome, telefone, email, rg) values (?,?,?,?,?)";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, cliente.getCpf());
            ps.setString(2, cliente.getNome());
            ps.setString(3, cliente.getTelefone());
            ps.setString(4, cliente.getEmail());
            ps.setString(5, cliente.getRg());

            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao cadastrar cliente: " + erro.getMessage());
        }
    }

    public void alterar(Cliente cliente) throws Exception {
        String sql = "update Clientes set nome = ?, telefone = ?, email = ?, rg = ? where cpf = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, cliente.getNome());
            ps.setString(2, cliente.getTelefone());
            ps.setString(3, cliente.getEmail());
            ps.setString(4, cliente.getRg());
            ps.setString(5, cliente.getCpf());

            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao alterar cliente: " + erro.getMessage());
        }
    }

    public void remover(Cliente cliente) throws Exception {
        String sql = "delete from Clientes where cpf = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, cliente.getCpf());
            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao remover cliente: " + erro.getMessage());
        }
    }

    public Cliente buscarPorCpf(String cpf) throws Exception {
        String sql = "select * from Clientes where cpf = ?";
        Cliente cliente = null;

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, cpf);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    cliente = new Cliente();
                    cliente.setCpf(rs.getString("cpf"));
                    cliente.setNome(rs.getString("nome"));
                    cliente.setTelefone(rs.getString("telefone"));
                    cliente.setEmail(rs.getString("email"));
                    cliente.setRg(rs.getString("rg"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar cliente: " + e.getMessage());
        }
        return cliente;
    }

    public List<Cliente> listarTodos() throws Exception {
        String sql = "select * from Clientes";
        List<Cliente> clientes = new ArrayList<>();

        try (Statement stmt = this.conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(rs.getString("cpf"));
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setRg(rs.getString("rg"));
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar clientes: " + e.getMessage());
        }
        return clientes;
    }
}