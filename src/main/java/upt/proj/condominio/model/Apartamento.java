package upt.proj.condominio.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "apartamentos")
public class Apartamento {
	

	@Id
	@Column(name = "ID", nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "nApartamento", nullable = false)
	private Integer nApartamento;

	@Column(name = "Tamanho", nullable = false)
	private Integer tamanho;

	@Column(name = "Garagem", nullable = false)
	private Boolean garagem;

	@Column(name = "nResidentes", nullable = false)
	private Integer nResidentes;

	@Column(name = "WC", nullable = false)
	private Integer wc;

	@Column(name = "Andar", nullable = false)
	private Integer andar;

	@Column(name = "Fracao", nullable = false)
	private Character fracao;

	@Column(name = "Username_Ocupante", nullable = false)
	private String ocupante;

	@Column(name = "Agua", nullable = false)
	private Double agua;

	@Column(name = "Gas", nullable = false)
	private Double gas;

	@Column(name = "Eletricidade", nullable = false)
	private Double eletricidade;

	@ManyToOne
	@JoinColumn(name = "ID_Predio", nullable = false)
	@JsonBackReference
	private Predio predio;

	public Apartamento() {
		//JPA constructor
	}

	public Apartamento(Integer tamanho, Boolean garagem, Integer nResidentes, Integer wc, Integer andar, Character fracao,String ocupante, Integer nApartamento) {
		this.tamanho = tamanho;
		this.garagem = garagem;
		this.nResidentes = nResidentes;
		this.wc = wc;
		this.andar = andar;
		this.fracao = fracao;
		this.ocupante = ocupante;
		this.nApartamento = nApartamento;
		this.agua = 0.0;
		this.gas = 0.0;
		this.eletricidade = 0.0;
	}


	public Integer getId() {
		return id;
	}
	
	public Integer getnApartamento() {
		return nApartamento;
	}

	public Integer getTamanho() {
		return tamanho;
	}

	public Boolean isGaragem() {
		return garagem;
	}

	public Integer getnResidentes() {
		return nResidentes;
	}

	public Predio getPredio() {
		return predio;
	}

	public Integer getWc() {
		return wc;
	}

	public Integer getAndar() {
		return andar;
	}

	public Character getFracao() {
		return fracao;
	}

	public String getOcupante() {
		return ocupante;
	}

	public Double getAgua() {
		return agua;
	}

	public Double geteletricidade() {
		return eletricidade;
	}

	public Double getGas() {
		return gas;
	}

	public void setnResidentes(Integer nResidentes) {
		this.nResidentes = nResidentes;
	}

	public void setTamanho(Integer tamanho) {
		this.tamanho = tamanho;
	}

	public void setGaragem(Boolean garagem) {
		this.garagem = garagem;
	}

	public void setWc(Integer wc) {
		this.wc = wc;
	}

	public void setOcupante(User ocupante) {
		this.ocupante = ocupante.getUsername();
	}

	public void setAgua(Double agua) {
		this.agua = agua;
	}

	public void setGas(Double gas) {
		this.gas = gas;
	}

	public void seteletricidade(Double eletricidade) {
		this.eletricidade = eletricidade;
	}

	public void setPredio(Predio predio) {
		this.predio = predio;
	}

	public void setAndar(Integer andar) {
		this.andar = andar;
	}

	public void setFracao(Character fracao) {
		this.fracao = fracao;
	}

	public void setnApartamento(Integer nApartamento) {
		this.nApartamento = nApartamento;
	}

	@Override
	public String toString() {
		return "Tamanho: " + tamanho + "\nGaragem: " + garagem + "\nNº de Residentes: " + nResidentes + "\nWC: " + wc + "\nAndar: " + andar + "\nFracao: " + fracao + "\nOcupante: " + ocupante + "\nNº do Apartamento: " + nApartamento;
	}

}
