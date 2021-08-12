package br.fai.lds.model;

public class UsuarioJogo {

	private Long usuarioId;
	private Long jogoId;

	// lombok

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(final Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Long getJogoId() {
		return jogoId;
	}

	public void setJogoId(final Long jogoId) {
		this.jogoId = jogoId;
	}

}
