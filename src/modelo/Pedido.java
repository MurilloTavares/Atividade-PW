package modelo;

import java.sql.Date;

public class Pedido {
    private int id;
    private Date data;
    private int cliente;
    private float valor;

    public Pedido(int id, Date data, int cliente, float valor) {
        this.id = id;
        this.data = data;
        this.cliente = cliente;
        this.valor = valor;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date date) {
        this.data = date;
    }

    public int getCliente() {
        return cliente;
    }

    public void setCliente(int cliente) {
        this.cliente = cliente;
    }

    public float getValor() {
        return valor;
    }

    public void setValor(float valor) {
        this.valor = valor;
    }
    
}
