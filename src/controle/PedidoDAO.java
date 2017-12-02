package controle;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import modelo.InterfaceDAO;
import modelo.Pedido;

public class PedidoDAO implements InterfaceDAO<Pedido> {

    Conexao conexao = new Conexao();
    Connection connection = conexao.getConn();

    private PreparedStatement setPedido(Pedido p, PreparedStatement stat) throws SQLException {
        try {
            stat.setInt(1, p.getId());
            stat.setDate(2, p.getData());
            stat.setInt(3, p.getCliente());
            stat.setFloat(4, p.getValor());
            return stat;
        } catch (SQLException ex) {
            throw new SQLException(ex);
        }
    }

    @Override
    public void incluir(Pedido p) throws SQLException {
        String sql = "INSERT INTO PEDIDO(ID, Data, Cliente, valor) Values(?,?,?,?)";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setPedido(p, stat);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void alterar(Pedido p, Pedido novo) throws SQLException {
        String sql = "UPDATE Pedido SET Id = ?, Data = ?, Cliente = ?, Valor = ?"
                + "WHERE Id = ? ";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setPedido(novo, stat);
            stat.setInt(5, p.getId());
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void deletar(Pedido p) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE Id = ? and Data = ? and Cliente = ? and Valor = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            setPedido(p, stat);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    public void deletar(int id) throws SQLException {
        String sql = "DELETE FROM Pedido WHERE Id = ?";
        PreparedStatement stat = connection.prepareStatement(sql);

        try {
            stat.setInt(1, id);
            stat.executeUpdate();
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

    @Override
    public void listar() throws SQLException {
        String sql = "SELECT * FROM Pedido";
        Statement stat = connection.createStatement();

        try {
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                int id = rs.getInt("Id");
                Date data = rs.getDate("Data");
                int cliente = rs.getInt("Cliente");
                float valor = rs.getFloat("Valor");

                System.out.println("ID: " + id + ", DATA: " + data + ", CLIENTE: "
                        + cliente + ", VALOR: " + valor);
            }
        } catch (SQLException ex) {
            throw new SQLException(ex);
        } finally {
            stat.close();
        }
    }

}
