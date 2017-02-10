package modelo;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Jogador {

	@Id
	@GeneratedValue
	private Long id;
	private String nome;
	private String posicao;
	private Calendar dataDeNascimento = new GregorianCalendar();

	@ManyToOne
	private Time time;

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getPosicao() {
		return posicao;
	}

	public Calendar getDataDeNascimento() {
		return dataDeNascimento;
	}

	public Time getTime() {
		return time;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPosicao(String posicao) {
		this.posicao = posicao;
	}

	public void setDataDeNascimento(Calendar dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public void setTime(Time time) {
		this.time = time;
	}

}
