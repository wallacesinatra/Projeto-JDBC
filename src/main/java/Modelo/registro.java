package Modelo;

import java.sql.Date;

public class registro {

	private Integer id;
	private String nome;
	private Integer idade;
	private String rg;
	private String cpf;
	private Date dataNasc;

	public registro(Integer id, String nome, Integer idade, String rg, String cpf, Date dataNasc) {
		super();
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.rg = rg;
		this.cpf = cpf;
		this.dataNasc = dataNasc;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNasc() {
		return dataNasc;
	}

	public void setDataNasc(Date dataNasc) {
		this.dataNasc = dataNasc;
	}

	@Override
	public String toString() {

		return String.format("%d  %s  %d  %s  %s  %s", this.id ,this.nome, this.idade, this.rg, this.cpf, this.dataNasc);
	}
}
