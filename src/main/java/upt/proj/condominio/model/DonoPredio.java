package upt.proj.condominio.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;
import upt.proj.condominio.model.Predio;

@Entity
@DiscriminatorValue("DonoPredio")
@JsonIgnoreProperties({"predios"})
public class DonoPredio extends Conta {

    @Column(name = "Idade", nullable = true)
    private Integer idade;

    @OneToMany(mappedBy = "donoPred", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference
    private List<Predio> predios = new ArrayList<>();

    public DonoPredio() {
        //JPA constructor 
    }

    public DonoPredio(String username, String email, String password, Integer idade) {
        super(username, email, password);
        this.idade = idade;
    }

    @Override
    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public List<Predio> getPredios() {
        return predios;
    }

    public void addPredio(Predio predio) {
        predios.add(predio);
        predio.setDono(this);
    }
}
