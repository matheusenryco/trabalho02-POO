package Banco;

import Model.Funcionario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioBD {
    private final SQLiteDriver sqLiteDriver;
    private final Connection conexao;

    private void criarTabela(){
        String sql = "CREATE TABLE IF NOT EXISTS Funcionarios (" +
                "   numMatricula int PRIMARY KEY NOT NULL," +
                "   nome text NOT NULL," +
                "   qualificacao text NOT NULL," +
                "   descricaoFuncao text NOT NULL," +
                "   cargaHorariaSemanal int NOT NULL" +
                ");";

        try (Statement stmt = this.conexao.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela: " + e.getMessage());
        }
    }

    public FuncionarioBD(){
        this.sqLiteDriver = new SQLiteDriver("Concessionaria");
        this.conexao = sqLiteDriver.iniciarConexao();
        this.criarTabela();
    }

    public void cadastrar(Funcionario funcionario) throws Exception {
        String sql = "insert into Funcionarios (numMatricula, nome, qualificacao, descricaoFuncao, cargaHorariaSemanal) values (?,?,?,?,?)";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setInt(1, funcionario.getNumMatricula());
            ps.setString(2, funcionario.getNome());
            ps.setString(3, funcionario.getQualificacao());
            ps.setString(4, funcionario.getDescricaoFuncao());
            ps.setInt(5, funcionario.getCargaHorariaSemanal());

            ps.executeUpdate();
        } catch (SQLException erro){
            throw new Exception("Erro ao cadastrar funcionário: " + erro.getMessage());
        }
    }

    public void alterar(Funcionario funcionario) throws Exception {
        String sql = "update Funcionarios set "
                + " nome = ?, qualificacao = ?, descricaoFuncao = ?, cargaHorariaSemanal = ? "
                + " where numMatricula = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setString(1, funcionario.getNome());
            ps.setString(2, funcionario.getQualificacao());
            ps.setString(3, funcionario.getDescricaoFuncao());
            ps.setInt(4, funcionario.getCargaHorariaSemanal());
            ps.setInt(5, funcionario.getNumMatricula());

            ps.executeUpdate();
        } catch (SQLException erro){
            throw new Exception("Erro ao alterar funcionário: " + erro.getMessage());
        }
    }

    public void remover(Funcionario funcionario) throws Exception {
        String sql = "delete from Funcionarios where numMatricula = ?";

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setInt(1, funcionario.getNumMatricula());
            ps.executeUpdate();
        } catch (SQLException erro){
            throw new Exception("Erro ao remover funcionário: " + erro.getMessage());
        }
    }

    public Funcionario buscarPorMatricula(int numMatricula) throws Exception {
        String sql = "select * from Funcionarios where numMatricula = ?";
        Funcionario funcionario = null;

        try (PreparedStatement ps = this.conexao.prepareStatement(sql)) {
            ps.setInt(1, numMatricula);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    funcionario = new Funcionario();
                    funcionario.setNumMatricula(rs.getInt("numMatricula"));
                    funcionario.setNome(rs.getString("nome"));
                    funcionario.setQualificacao(rs.getString("qualificacao"));
                    funcionario.setDescricaoFuncao(rs.getString("descricaoFuncao"));
                    funcionario.setCargaHorariaSemanal(rs.getInt("cargaHorariaSemanal"));
                }
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao buscar funcionário: " + e.getMessage());
        }

        return funcionario;
    }

    public List<Funcionario> listarTodos() throws Exception {
        String sql = "select * from Funcionarios";
        List<Funcionario> funcionarios = new ArrayList<>();

        try (Statement stmt = this.conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setNumMatricula(rs.getInt("numMatricula"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setQualificacao(rs.getString("qualificacao"));
                funcionario.setDescricaoFuncao(rs.getString("descricaoFuncao"));
                funcionario.setCargaHorariaSemanal(rs.getInt("cargaHorariaSemanal"));
                funcionarios.add(funcionario);
            }
        } catch (SQLException e) {
            throw new Exception("Erro ao listar funcionários: " + e.getMessage());
        }

        return funcionarios;
    }
}