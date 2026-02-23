package Banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteDriver {
    private final String sistemaConcessionaria;

    public SQLiteDriver(String banco){
        this.sistemaConcessionaria = banco;
    }
    
    public Connection iniciarConexao(){
        Connection conexao = null;
        try {
            String url = "jdbc:sqlite:" + this.sistemaConcessionaria;
            conexao = DriverManager.getConnection(url);

        } catch (SQLException sqe){
            System.out.println("Problemas na conexao com o banco. Erro: " + sqe.getMessage());
        }
        return conexao;
    }
}