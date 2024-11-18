package upt.proj.condominio.model;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "Predios")
@JsonIgnoreProperties({"apartamentos"})
public class Predio {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(name = "Nome_Predio", nullable = false, unique = true)
	private String nomeP;

	@Column(name = "Zona", nullable = false)
	private String zona;

	@Column(name = "Num.Total_Apartamentos", nullable = false)
	private Integer ntotalapart;

	@Column(name = "Animais_Permitidos", nullable = false)
	private Boolean animaisP;

	@Column(name = "Total_Agua", nullable = false)
	private Integer totalAgua;

	@Column(name = "Total_Luz", nullable = false)
	private Integer totalLuz;

	@Column(name = "Total_Gas", nullable = false)
	private Integer totalGas;

	@OneToMany(mappedBy = "predio", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JsonManagedReference
	private List<Apartamento> apartamentos = new ArrayList<>();

	@ManyToOne
    @JoinColumn(name = "Id_Dono", nullable = false)
	@JsonBackReference
	private DonoPredio donoPred;

	
	public Predio() {
		//JPA constructor
	}

	public Predio(String nomeP, String zona, Boolean animaisP, Integer andaresNum,Integer npisos,Integer napartPiso) {
		
		this.nomeP = nomeP;
		this.zona = zona;
		this.animaisP = animaisP;
		this.ntotalapart = npisos*napartPiso;
		this.totalAgua = 0;
		this.totalLuz = 0;
		this.totalGas = 0;
		
	}

	public Integer getId() {
		return id;
	}

	public DonoPredio getDono() {
		return donoPred;
	}

	public String getNomeP() {
		return nomeP;
	}

	public String getZona() {
		return zona;
	}

	public Integer getNtotalapart() {
		return ntotalapart;
	}

	public Boolean getAnimaisP() {
		return animaisP;
	}

	public Integer getTotalAgua() {
		return totalAgua;
	}

	public Integer getTotalLuz() {
		return totalLuz;
	}

	public Integer getTotalGas() {
		return totalGas;
	}
	public void setAnimaisP(Boolean animaisP) {
		this.animaisP = animaisP;
	}

	public void setTotalAgua(Integer totalAgua) {
		this.totalAgua = totalAgua;
	}

	public void setTotalLuz(Integer totalLuz) {
		this.totalLuz = totalLuz;
	}

	public void setTotalNet(Integer totalNet) {
		this.totalGas = totalNet;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	public void setDono(DonoPredio dono) {
		this.donoPred = dono;
	}

	public void setNomeP(String nomeP) {
		this.nomeP = nomeP;
	}

	public void setZona(String zona) {
		this.zona = zona;
	}


	public void setNtotalapart(Integer ntotalapart) {
		this.ntotalapart = ntotalapart;
	}

	public void setTotalGas(Integer totalGas) {
		this.totalGas = totalGas;
	}

	public List<Apartamento> getApartamentos() {
		return apartamentos;
	}

	public void addApartamento(Apartamento apartamento) {
		apartamentos.add(apartamento);
		apartamento.setPredio(this);
	}

	@Override
	public String toString() {
		return "Nome: " + nomeP + "\nZona: " + zona + "\nTotal Apartamentos: " + ntotalapart + "\nAnimais Permitidos: " + animaisP + "\nTotal Agua: " + totalAgua + "\nTotal Luz: " + totalLuz + "\nTotal Gas: " + totalGas + "\nApartamentos associados: " + apartamentos.size();
	}
}