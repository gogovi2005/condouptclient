package upt.proj.condominio.model;

import jakarta.persistence.*;

@Entity
@Table(name = "Gastos")
public class Gastos {
	
	@Id
	@Column(name = "Id", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "Id_User", nullable = false)
	private User user;

	@Column(name = "Mes", nullable = false)
	private Integer mes;

	@Column(name = "Ano", nullable = false)
	private Integer ano;
	
	@Column(name = "Agua", nullable = false)
	private Float agua;

	@Column(name = "Gas", nullable = false)
	private Float gas;

	@Column(name = "Eletricidade", nullable = false)
	private Float eletricidade;
	
	public Gastos() {
		//JPA constructor
	}

	public Gastos(Integer mes, Integer ano, Float agua, Float gas, Float eletricidade) {
		this.mes = mes;
		this.ano = ano;
		this.agua = agua;
		this.gas = gas;
		this.eletricidade = eletricidade;
	}


	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getMes() {
		return mes;
	}

	public void setMes(Integer mes) {
		this.mes = mes;
	}

	public Integer getAno() {
		return ano;
	}

	public void setAno(Integer ano) {
		this.ano = ano;
	}

	public Float getAgua() {
		return agua;
	}

	public void setAgua(Float agua) {
		this.agua = agua;
	}

	public Float getGas() {
		return gas;
	}

	public void setGas(Float gas) {
		this.gas = gas;
	}

	public Float getEletricidade() {
		return eletricidade;
	}

	public void setEletricidade(Float eletricidade) {
		this.eletricidade = eletricidade;
	}
	
	@Override
	public String toString() {
		return "Mes: " + mes + "\nAno: " + ano + "\nAgua: " + agua + "\nGas: " + gas + "\nEletricidade: " + eletricidade;
	}
}
