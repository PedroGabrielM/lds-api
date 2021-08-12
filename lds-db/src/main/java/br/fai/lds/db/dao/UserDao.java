package br.fai.lds.db.dao;

import java.util.List;

import br.fai.lds.model.Usuario;

public interface UserDao {

	List<Usuario> readAll();

	Usuario readById(Long id);

	Long create(Usuario entity);

	boolean update(Usuario entity);

	boolean delete(Long id);
}
