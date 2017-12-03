package controle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.JdbcRowSet;
import javax.sql.rowset.RowSetFactory;
import javax.sql.rowset.RowSetProvider;

public class Conexao {

    String url;
    String user;
    String pass;
    Connection conn;
    RowSetFactory factory;

    public Conexao() {
        url = "jdbc:postgresql://localhost:5432/atividadepw";
        user = "postgres";
        pass = "1234";

        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

            factory = RowSetProvider.newFactory();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro na Conexao.");
        }
    }
    
    public Conexao(String url, String user, String pass) {
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(url, user, pass);

            factory = RowSetProvider.newFactory();
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Erro na Conexao.");
        }
    }

    public int executaUpdate(String sql) throws SQLException {
        try {
            Statement stat = conn.createStatement();
            int result = stat.executeUpdate(sql);

            stat.close();
            conn.close();

            return result;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public ResultSet executaConsulta(String sql) throws SQLException {
        try {
            Statement stat = conn.createStatement();
            ResultSet result = stat.executeQuery(sql);
            conn.close();
            return result;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void executaRowSet(RowSet rs, String sql) throws SQLException {
        try {
            rs.setUrl(url);
            rs.setUsername(user);
            rs.setPassword(pass);

            rs.setCommand(sql);
            rs.execute();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void jdbcRowSet(String sql) throws SQLException {
        JdbcRowSet jrs;
        try {
            jrs = factory.createJdbcRowSet();
            executaRowSet(jrs, sql);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    public void cachedRowSet(String sql) throws SQLException {
        CachedRowSet crs;
        try {
            crs = factory.createCachedRowSet();
            executaRowSet(crs, sql);
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }
    
    

    public Connection getConn() {
        return conn;
    }
}
