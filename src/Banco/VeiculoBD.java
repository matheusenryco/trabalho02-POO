package Banco;

import Model.Veiculo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VeiculoBD {
    private final SQLiteDriver sqLiteDriver;
    private final Connection conexao;

    private void criarTabela() {
        String sql = "CREATE TABLE IF NOT EXISTS Veiculo (" +
                "   chassi text PRIMARY KEY NOT NULL," +
                "   nome text NOT NULL," +
                "   cor text NOT NULL," +
                "   numMarchas int NOT NULL," +
                "   numPortas int NOT NULL," +
                "   marca text NOT NULL," +
                "   ano int NOT NULL" +
                ");";

        try (Statement stmt = this.conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela Veiculo: " + e.getMessage());
        }
    }

    public VeiculoBD() {
        this.sqLiteDriver = new SQLiteDriver("Concessionaria");
        this.conexao = sqLiteDriver.iniciarConexao();
        this.criarTabela();
    }

    public void cadastrar(Veiculo veiculo) throws Exception {
        String sql = "insert into Veiculo (chassi, nome, cor, numMarchas, numPortas, marca, ano) values (?,?,?,?,?,?,?)";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, veiculo.getChassi());
            ps.setString(2, veiculo.getNome());
            ps.setString(3, veiculo.getCor());
            ps.setInt(4, veiculo.getNumMarchas());
            ps.setInt(5, veiculo.getNumPortas());
            ps.setString(6, veiculo.getMarca());
            ps.setInt(7, veiculo.getAno());

            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao cadastrar veículo: " + erro.getMessage());
        }
    }

    public void alterar(Veiculo veiculo) throws Exception {
        String sql = "update Veiculo set nome = ?, cor = ?, numMarchas = ?, numPortas = ?, marca = ?, ano = ? where chassi = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, veiculo.getNome());
            ps.setString(2, veiculo.getCor());
            ps.setInt(3, veiculo.getNumMarchas());
            ps.setInt(4, veiculo.getNumPortas());
            ps.setString(5, veiculo.getMarca());
            ps.setInt(6, veiculo.getAno());
            ps.setString(7, veiculo.getChassi());

            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao alterar veículo: " + erro.getMessage());
        }
    }

    public void remover(Veiculo veiculo) throws Exception {
        String sql = "delete from Veiculo where chassi = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, veiculo.getChassi());
            ps.executeUpdate();
        } catch (SQLException erro) {
            throw new Exception("Erro ao remover veículo: " + erro.getMessage());
        }
    }

    public List<Veiculo> listarTodos() throws Exception {
        String sql = "select * from Veiculo";
        List<Veiculo> veiculos = new ArrayList<>();

        try (Statement stmt = this.conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Veiculo veiculo = new Veiculo();
                veiculo.setChassi(rs.getString("chassi"));
                veiculo.setNome(rs.getString("nome"));
                veiculo.setCor(rs.getString("cor"));
                veiculo.setNumMarchas(rs.getInt("numMarchas"));
                veiculo.setNumPortas(rs.getInt("numPortas"));
                veiculo.setMarca(rs.getString("marca"));
                veiculo.setAno(rs.getInt("ano"));
                veiculos.add(veiculo);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar veículos: " + e.getMessage());
        }

        return veiculos;
    }

    public Veiculo buscarPorChassi(String chassi) throws Exception {
        String sql = "select * from Veiculo where chassi = ?";
        Veiculo veiculo = null;

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, chassi);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    veiculo = new Veiculo();
                    veiculo.setChassi(rs.getString("chassi"));
                    veiculo.setNome(rs.getString("nome"));
                    veiculo.setCor(rs.getString("cor"));
                    veiculo.setNumMarchas(rs.getInt("numMarchas"));
                    veiculo.setNumPortas(rs.getInt("numPortas"));
                    veiculo.setMarca(rs.getString("marca"));
                    veiculo.setAno(rs.getInt("ano"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar veículo: " + e.getMessage());
        }
        return veiculo;
    }
}