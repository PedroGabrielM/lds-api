package br.fai.lds.model;

import java.sql.Timestamp;

public class Jogo extends BasePojo {

	private String nome;
	private String desenvolvidoPor;
	private String genero;
	private String plataforma;
	private Timestamp dataLancamento;
	private String descricao;
	private String imagemPrincipal;

	public String getNome() {
		return nome;
	}

	public void setNome(final String nome) {
		this.nome = nome;
	}

	public String getDesenvolvidoPor() {
		return desenvolvidoPor;
	}

	public void setDesenvolvidoPor(final String desenvolvidoPor) {
		this.desenvolvidoPor = desenvolvidoPor;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(final String genero) {
		this.genero = genero;
	}

	public String getPlataforma() {
		return plataforma;
	}

	public void setPlataforma(final String plataforma) {
		this.plataforma = plataforma;
	}

	public Timestamp getDataLancamento() {
		return dataLancamento;
	}

	public void setDataLancamento(final Timestamp dataLancamento) {
		this.dataLancamento = dataLancamento;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(final String descricao) {
		this.descricao = descricao;
	}

	public String getImagemPrincipal() {
		return imagemPrincipal;
	}

	public void setImagemPrincipal(final String imagemPrincipal) {
		this.imagemPrincipal = imagemPrincipal;
	}

}
