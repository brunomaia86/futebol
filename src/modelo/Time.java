package modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Time {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String tecnico;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getTecnico() {
		return tecnico;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setTecnico(String tecnico) {
		this.tecnico = tecnico;
	}

}
