package upt.proj.condominio.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.*;

@Entity
@DiscriminatorValue("Morador")

public class User extends Conta {
 
    @Column(name = "Idade", nullable = true)
    private int idade;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Gastos> gastos = new ArrayList<>();

   
    public User() {
        //JPA constructor 
    }

    public User(String username, String email, String password, int idade) {
        super(username, email, password);
        this.idade = idade;
    }
    

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    @Override
    public List<Gastos> getGastos() {
        return gastos;
    }

    @Override
    public void addGastos(Gastos gasto) {
        gastos.add(gasto);
        gasto.setUser(this);
    }

}

