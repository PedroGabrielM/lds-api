package br.fai.lds.model;

import java.sql.Timestamp;

public class Comentario extends BasePojo {

	private Long jogoId;
	private Long usuarioId;
	private Timestamp enviadoEm;
	private String comentario;

	public Long getJogoId() {
		return jogoId;
	}

	public void setJogoId(final Long jogoId) {
		this.jogoId = jogoId;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(final Long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public Timestamp getEnviadoEm() {
		return enviadoEm;
	}

	public void setEnviadoEm(final Timestamp enviadoEm) {
		this.enviadoEm = enviadoEm;
	}

	public String getComentario() {
		return comentario;
	}

	public void setComentario(final String comentario) {
		this.comentario = comentario;
	}

}
