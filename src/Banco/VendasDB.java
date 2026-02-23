package Banco;

import Model.Vendas;
import Model.Cliente;
import Model.Funcionario;
import Model.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VendasDB {
    private final SQLiteDriver sqLiteDriver;
    private final Connection conexao;

    private void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS Vendas (" +
                "   id INTEGER PRIMARY KEY," +
                "   data text NOT NULL," +
                "   valor REAL NOT NULL," +
                "   cpf_cliente text NOT NULL," +
                "   numMatricula_funcionario INTEGER NOT NULL," +
                "   chassi_veiculo text NOT NULL," +
                "   FOREIGN KEY (cpf_cliente) REFERENCES Clientes(cpf)," +
                "   FOREIGN KEY (numMatricula_funcionario) REFERENCES Funcionarios(numMatricula)," +
                "   FOREIGN KEY (chassi_veiculo) REFERENCES Veiculo(chassi)" +
                ");";

        try (Statement stmt = this.conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela Vendas: " + e.getMessage());
        }
    }

    public VendasDB() {
        this.sqLiteDriver = new SQLiteDriver("Concessionaria");
        this.conexao = sqLiteDriver.iniciarConexao();
        this.criarTabela();
    }

    public void cadastrar(Vendas venda) throws Exception {
        String sql = "insert into Vendas (data, valor, cpf_cliente, numMatricula_funcionario, chassi_veiculo) values (?,?,?,?,?)";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, venda.getData());
            ps.setDouble(2, venda.getValor());
            ps.setString(3, venda.getCliente().getCpf());
            ps.setInt(4, venda.getFuncionario().getNumMatricula());
            ps.setString(5, venda.getVeiculo().getChassi());

            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao cadastrar venda: " + erro.getMessage());
        }
    }

    public void alterar(Vendas venda, int id) throws Exception {
        String sql = "update Vendas set "
                + " data = ?, valor = ?, cpf_cliente = ?, numMatricula_funcionario = ?, chassi_veiculo = ? "
                + " where id = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, venda.getData());
            ps.setDouble(2, venda.getValor());
            ps.setString(3, venda.getCliente().getCpf());
            ps.setInt(4, venda.getFuncionario().getNumMatricula());
            ps.setString(5, venda.getVeiculo().getChassi());
            ps.setInt(6, id);

            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao alterar venda: " + erro.getMessage());
        }
    }

    public void remover(int id) throws Exception {
        String sql = "delete from Vendas where id = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao remover venda: " + erro.getMessage());
        }
    }

    public List<Vendas> listarTodos() throws Exception {
        String sql = "select * from Vendas";
        List<Vendas> vendas = new ArrayList<>();

        try (Statement stmt = this.conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Vendas venda = new Vendas();
                venda.setId(rs.getInt("id"));
                venda.setData(rs.getString("data"));
                venda.setValor(rs.getDouble("valor"));

                Cliente c = new Cliente();
                c.setCpf(rs.getString("cpf_cliente"));
                venda.setCliente(c);

                Funcionario f = new Funcionario();
                f.setNumMatricula(rs.getInt("numMatricula_funcionario"));
                venda.setFuncionario(f);

                Veiculo v = new Veiculo();
                v.setChassi(rs.getString("chassi_veiculo"));
                venda.setVeiculo(v);

                vendas.add(venda);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar vendas: " + e.getMessage());
        }

        return vendas;
    }

    public Vendas buscarPorCpf(String cpf) throws Exception {
        String sql = "select * from Vendas where cpf_cliente = ?";
        Vendas venda = null;

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, cpf);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    venda = new Vendas();
                    venda.setId(rs.getInt("id"));
                    venda.setData(rs.getString("data"));
                    venda.setValor(rs.getDouble("valor"));

                    Cliente cliente = new Cliente();
                    cliente.setCpf(rs.getString("cpf_cliente"));
                    venda.setCliente(cliente);

                    Funcionario funcionario = new Funcionario();
                    funcionario.setNumMatricula(rs.getInt("numMatricula_funcionario"));
                    venda.setFuncionario(funcionario);

                    Veiculo veiculo = new Veiculo();
                    veiculo.setChassi(rs.getString("chassi_veiculo"));
                    venda.setVeiculo(veiculo);
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar venda por CPF: " + e.getMessage());
        }
        return venda;
    }
}