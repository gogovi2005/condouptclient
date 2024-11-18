package upt.proj.condominio.model;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("Empresa")
public class Empresa extends Conta{

    @Column(name = "Tipo_de_Servicos", nullable = true)
    private String tiposervice;

    @Column(name = "Zona", nullable = true)
    private String zona;

    @Column(name = "Preco", nullable = true)
    private Float preco;


    public Empresa() {
        //JPA constructor 
    }

    public Empresa(String username, String email, String password, String tiposervice, String zona, Float preco) {
        super(username, email, password);
        this.tiposervice = tiposervice;
        this.zona = zona;
        this.preco = preco;
    }

    @Override
    public String gettiposervice() {
        return tiposervice;
    }

    public void settiposervice(String tiposervice) {
        this.tiposervice = tiposervice;
    }

    @Override
    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    @Override
    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

}
