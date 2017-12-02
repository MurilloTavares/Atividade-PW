package controle;

import modelo.InterfaceDAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.Cliente;

public class ClienteDAO implements InterfaceDAO<Cliente> {

    Conexao conexao = new Conexao();
    Connection connection = conexao.getConn();

    private PreparedStatement setCliente(Cliente c, PreparedStatement stat) throws SQLException {
        try {
            stat.setInt(1, c.getId());
            stat.setString(2, c.getNome());
            stat.setString(3, c.getDocumento());
            stat.setFloat(4, c.getSaldo());
            stat.setBoolean(5, c.getAtivo());
            return stat;

        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void incluir(Cliente c) throws SQLException {
        String sql = "INSERT INTO Cliente(ID, Nome, Documento, Saldo, Ativo) Values(?,?,?,?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setCliente(c, stat);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void alterar(Cliente c, Cliente novo) throws SQLException {
        String sql = "UPDATE Cliente SET Id = ?, Nome = ?, Documento = ?, Saldo = ?, Ativo = ? "
                + "WHERE Id = ? ";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setCliente(novo, stat);
            stat.setInt(6, c.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void deletar(Cliente c) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE Id = ? and Nome = ? and Documento = ? and Saldo = ? and Ativo = ? ";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setCliente(c, stat);
            stat.executeUpdate();
            stat.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Cliente WHERE Id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            stat.setInt(1, id);
            stat.executeUpdate();
            stat.close();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void listar() throws SQLException {
        String sql = "SELECT * FROM Cliente";
        Statement stat = connection.createStatement();

        try {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                String nome = rs.getString("Nome");
                String documento = rs.getString("Documento");
                float saldo = rs.getFloat("Saldo");
                boolean ativo = rs.getBoolean("Ativo");

                System.out.println("ID: " + id + ", NOME: " + nome + ", DOCUMENTO: "
                        + documento + ", SALDO: " + saldo + ", ATIVO: " + ativo);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

}
