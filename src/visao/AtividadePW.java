package visao;

import controle.ClienteDAO;
import controle.Conexao;
import controle.PedidoDAO;
import java.sql.Date;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.rowset.CachedRowSet;
import javax.sql.rowset.FilteredRowSet;
import javax.sql.rowset.JdbcRowSet;
import modelo.Cliente;
import modelo.Pedido;
import modelo.SaldoFilter;

public class AtividadePW {

    public static void main(String[] args) {
        
//        ClienteDAO cDao = new ClienteDAO("jdbc:postgresql://localhost:5432/atividadepw","postgres","1234");
//        
//        Cliente c1 = new Cliente(1,"JOAO","111",500,true);
//        Cliente c2 = new Cliente(2,"MARIA","222",500,true);
//        Cliente c3 = new Cliente(3,"JOAQUIM","333",200,true);
//        
//        try{
//            cDao.incluir(c1);
//            cDao.incluir(c1);
//            cDao.incluir(c1);
//            cDao.listar();            
//        }catch(SQLException ex){
//            System.out.println("Erro Cliente DAO");
//        }
//
//        System.out.println("");
//       
//        PedidoDAO pDao = new PedidoDAO();
//        
//        Pedido p1 = new Pedido(1,new Date(2017-1900,10,2), 1, 50);
//        Pedido p2 = new Pedido(2,new Date(2017-1900,01,23), 2, 80);
//        Pedido p3 = new Pedido(3,new Date(2017-1900,8,12), 3, 80);
//        
//        try{
//            pDao.incluir(p1);
//            pDao.incluir(p2);
//            pDao.incluir(p3);
//            pDao.listar();
//        }catch(SQLException ex){
//            System.out.println("Erro Pedido DAO");
//        }

        Conexao con = new Conexao();
        
        String sql = "SELECT * FROM CLIENTE";
        
        try {
            SaldoFilter filtro = new SaldoFilter(400,600);
            FilteredRowSet rs = con.execFilteredRowSet(sql, filtro);
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
            System.out.println("Erro JDBCROWSET");
        }

        
    }
    
}
