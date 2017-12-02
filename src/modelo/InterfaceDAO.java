package modelo;

import java.sql.SQLException;

public interface InterfaceDAO<E> {
    
    public void incluir(E e) throws SQLException;
    public void alterar(E e, E novo) throws SQLException;
    public void deletar(E e) throws SQLException;
    public void listar() throws SQLException;
    
}
