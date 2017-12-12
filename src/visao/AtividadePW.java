package visao;

import controle.ClienteDAO;
import controle.Conexao;
import controle.PedidoDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.RowSet;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import modelo.Cliente;
import modelo.Pedido;
import modelo.SaldoFilter;

public class AtividadePW {

    public static void main(String[] args) {
        
        ClienteDAO cDao = new ClienteDAO("jdbc:postgresql://localhost:5432/atividadepw","postgres","1234");
        
        Cliente c1 = new Cliente(1,"JOAO","111",500,true);
        Cliente c2 = new Cliente(2,"MARIA","222",500,true);
        Cliente c3 = new Cliente(3,"JOAQUIM","333",200,true);
        
        try{
            cDao.incluir(c1);
            cDao.incluir(c1);
            cDao.incluir(c1);
            cDao.listar();            
        }catch(SQLException ex){
            ex.getSQLState();
        }

        System.out.println("");
       
        PedidoDAO pDao = new PedidoDAO();
        
        Pedido p1 = new Pedido(1,new Date(2017-1900,10,2), 1, 50);
        Pedido p2 = new Pedido(2,new Date(2017-1900,01,23), 2, 80);
        Pedido p3 = new Pedido(3,new Date(2017-1900,8,12), 3, 80);
        
        try{
            pDao.incluir(p1);
            pDao.incluir(p2);
            pDao.incluir(p3);
            pDao.listar();
        }catch(SQLException ex){
            ex.getSQLState();
        }

        Conexao con = new Conexao();
        String sql;
        
        sql = "SELECT * FROM CLIENTE";
        RowSet rs = null;
        System.out.println("FilteredRowSet");
        System.out.println(sql);
        try {
            SaldoFilter filtro = new SaldoFilter(400,600);
            rs = con.execFilteredRowSet(sql, filtro);
            while(rs.next()){
                int id = rs.getInt("Id");
                String nome = rs.getString("Nome");
                String documento = rs.getString("Documento");
                float saldo = rs.getFloat("Saldo");
                boolean ativo = rs.getBoolean("Ativo");

                System.out.println("ID: " + id + ", NOME: " + nome + ", DOCUMENTO: "
                        + documento + ", SALDO: " + saldo + ", ATIVO: " + ativo);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("");
        
        sql = "SELECT * FROM PEDIDO";
        System.out.println("JdbcRowSet");
        System.out.println(sql);
        try {
            rs = con.execJdbcRowSet(sql);
            while(rs.next()){
                int id = rs.getInt("Id");
                Date data = rs.getDate("Data");
                int cliente = rs.getInt("Cliente");
                float valor = rs.getFloat("Valor");

                System.out.println("ID: " + id + ", DATA: " + data + ", CLIENTE: "
                        + cliente + ", VALOR: " + valor);                
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        System.out.println("");
        
        sql = "SELECT Id as Cliente, Nome, Saldo FROM CLIENTE";
        RowSet rs1 = null;        
        System.out.println("CachedRowSet 1");
        System.out.println(sql);
        try{
            rs1 = con.execCachedRowSet(sql);
            while(rs1.next()){                
                int cliente = rs1.getInt("Cliente");
                String nome = rs1.getString("Nome");
                float saldo = rs1.getFloat("Saldo");

                System.out.println("Cliente: " + cliente + ", NOME: " + nome+", SALDO: " + saldo);              
            }            
        } catch (SQLException ex){
            ex.getSQLState();
        }
        
        System.out.println("");
        
        sql = "SELECT Cliente, Id as CodPedido, Data, Valor FROM PEDIDO";
        RowSet rs2 = null;
        System.out.println("CachedRowSet 2");
        System.out.println(sql);
        try{
            rs2 = con.execCachedRowSet(sql);
            while(rs2.next()){                
                int cliente = rs2.getInt("Cliente");
                int codPedido = rs2.getInt("CodPedido");
                Date data = rs2.getDate("Data");
                float valor = rs2.getFloat("Valor");

                System.out.println("CLIENTE: "+ cliente +", CodPedido: " + codPedido +
                        ", DATA: " + data +  ", VALOR: " + valor);               
            }            
        } catch (SQLException ex){
            ex.getSQLState();
        }
        
        System.out.println("");
        
        RowSet rs3;
        System.out.println("JoinRowSet rs1 + rs2");
        try{
            rs3 = con.execJoinRowSet(rs1, rs2, "Cliente");
            while(rs3.next()){                
                int cliente = rs3.getInt("Cliente");
                String nome = rs3.getString("Nome");
                float saldo = rs3.getFloat("Saldo");
                int codPedido = rs3.getInt("CodPedido");
                Date data = rs3.getDate("Data");
                float valor = rs3.getFloat("Valor");

                System.out.println("CLIENTE: "+ cliente +", NOME: " + nome+", SALDO: " + saldo+
                        ", CodPedido: "+ codPedido +", DATA: "+ data +", VALOR: "+ valor);               
            }
        } catch (SQLException ex) {
            ex.getSQLState();
        }

        
    }
    
}
