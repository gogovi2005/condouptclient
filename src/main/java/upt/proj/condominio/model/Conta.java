package upt.proj.condominio.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "tipo")
@JsonSubTypes({
    @JsonSubTypes.Type(value = User.class, name = "Morador"),
    @JsonSubTypes.Type(value = DonoPredio.class, name = "DonoPredio"),
    @JsonSubTypes.Type(value = Empresa.class, name = "Empresa")
})
@Entity
@Table(name = "Contas")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Tipo", discriminatorType = DiscriminatorType.STRING)
public class Conta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false, unique = true)
    private Integer id;

    @Column(name = "Username", nullable = false, unique = true)
    private String username;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Password", nullable = false)
    private String password;
    

    public Conta() {
    //JPA constructor 
    }

    public Conta(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
 
    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void settiposervice(String string) {
    }

    public void setZona(String string) {
    }

    public void setPreco(Double d) {
    }

    public void setIdade(Integer idade) {

    }

    public void addPredio(Predio pred) {
    }

    public Integer getIdade() {
        return null;
    }

    public String gettiposervice() {
        return null;
    }

    public String getZona() {
        return null;
    }

    public Float getPreco() {
        return null;
    }

    public void addGastos(Gastos gastos) {
    }

    public List<Gastos> getGastos() {
        return null;
    }

    public Integer getId() {
        return id;
    }

    public List<Predio> getPredios() {
        return null;
    }
}
