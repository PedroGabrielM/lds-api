package br.fai.lds.model;

public class ImagemJogo extends BasePojo {

	private Long jogoId;
	private String imagem;

	public Long getJogoId() {
		return jogoId;
	}

	public void setJogoId(final Long jogoId) {
		this.jogoId = jogoId;
	}

	public String getImagem() {
		return imagem;
	}

	public void setImagem(final String imagem) {
		this.imagem = imagem;
	}

}
