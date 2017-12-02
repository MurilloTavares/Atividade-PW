package modelo;

public class Cliente {
    
    private int id;
    private String nome;
    private String documento;
    private float saldo;
    private boolean ativo;

    public Cliente(int id, String nome, String documento, float saldo, boolean ativo) {
        this.id = id;
        this.nome = nome;
        this.documento = documento;
        this.saldo = saldo;
        this.ativo = ativo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    public boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

}
