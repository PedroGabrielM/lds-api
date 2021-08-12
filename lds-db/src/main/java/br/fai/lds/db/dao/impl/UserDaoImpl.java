package br.fai.lds.db.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import br.fai.lds.db.connection.ConnectionFactory;
import br.fai.lds.db.dao.UserDao;
import br.fai.lds.model.Usuario;

/**
 *
 * @author Pedro Gabriel
 *
 */
public class UserDaoImpl implements UserDao {
	/**
	 *
	 *
	 * Lista todos os usuários da aplicaçao
	 *
	 *
	 */
	public List<Usuario> readAll() {

		final List<Usuario> users = new ArrayList<Usuario>();

		Connection connection = null;

		PreparedStatement preparedStatement = null;

		ResultSet resultSet = null;

		try {

			connection = ConnectionFactory.getConnection();

			final String sql = "SELECT * FROM usuario;";

			preparedStatement = connection.prepareStatement(sql);

			resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				final Usuario user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setNomeCompleto(resultSet.getString("nome_completo"));
				user.setNomeUsuario(resultSet.getString("nome_usuario"));
				user.setSenha(resultSet.getString("senha"));
				user.setEmail(resultSet.getString("email"));
				user.setDataNasc(resultSet.getTimestamp("data_nascimento"));
				user.setEstaAtivo(resultSet.getBoolean("esta_ativo"));
				user.setAvatar(resultSet.getBytes("avatar"));
				user.setCriadoEm(resultSet.getTimestamp("criado_em"));
				user.setTipo(resultSet.getString("tipo"));
				user.setUltimoAcesso(resultSet.getTimestamp("ultimo_acesso"));

				users.add(user);
			}

		} catch (final Exception e) {
			// TODO: handle exception
		} finally {

			ConnectionFactory.closeConnection();

		}

		return users;
	}

	/**
	 *
	 *
	 * Lista usuário pelo id
	 *
	 *
	 */

	public Usuario readById(final Long id) {
		Usuario user = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = ConnectionFactory.getConnection();

			preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE id = ?");
			preparedStatement.setLong(1, id);

			resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				user = new Usuario();
				user.setId(resultSet.getLong("id"));
				user.setNomeCompleto(resultSet.getString("nome_completo"));
				user.setNomeUsuario(resultSet.getString("nome_usuario"));
				user.setSenha(resultSet.getString("senha"));
				user.setEmail(resultSet.getString("email"));
				user.setDataNasc(resultSet.getTimestamp("data_nascimento"));
				user.setEstaAtivo(resultSet.getBoolean("esta_ativo"));
				user.setAvatar(resultSet.getBytes("avatar"));
				user.setCriadoEm(resultSet.getTimestamp("criado_em"));
				user.setTipo(resultSet.getString("tipo"));
				user.setUltimoAcesso(resultSet.getTimestamp("ultimo_acesso"));
			}

		} catch (final SQLException ex) {
		} finally {

			try {
				if (!resultSet.isClosed()) {
					resultSet.close();
				}

			} catch (final SQLException ex) {
			}

			try {
				if (!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (final SQLException ex) {
			}

			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (final SQLException ex) {
			}
		}

		return user;

	}

	/**
	 *
	 *
	 * Cria um usuário na aplicacao
	 *
	 *
	 */

	public Long create(final Usuario entity) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		final String sql = "INSERT INTO usuario"
				+ "    (nome_usuario, senha, nome_completo, email, tipo, esta_ativo, data_nascimento, ultimo_acesso, criado_em)"
				+ "VALUES(?, ?, ? ,? ,? ,? ,? ,? ,?); ";

		Long id = Long.valueOf(-1);

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, entity.getNomeUsuario());
			preparedStatement.setString(2, entity.getSenha());
			preparedStatement.setString(3, entity.getNomeCompleto());
			preparedStatement.setString(4, entity.getEmail().toLowerCase());
			preparedStatement.setBoolean(5, true);
			preparedStatement.setString(6, "USUARIO");
			preparedStatement.setTimestamp(7, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(8, new Timestamp(System.currentTimeMillis()));
			preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));
			preparedStatement.execute();

			resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				id = resultSet.getLong(1);
			}

			connection.commit();

		} catch (final Exception e) {
			System.out.print(e.getMessage());
		}

		return id;
	}

	/**
	 *
	 *
	 * Edita informaçoes do usuario na aplicacao
	 *
	 *
	 */

	public boolean update(final Usuario user) {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String sql = "UPDATE usuario SET nome_usuario = ?, email = ? WHERE id = ?";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setString(1, user.getNomeUsuario());
			preparedStatement.setString(2, user.getEmail().toLowerCase());
			preparedStatement.setLong(3, user.getId());
			preparedStatement.execute();
			connection.commit();

		} catch (final Exception e) {

			e.printStackTrace();

			try {
				connection.rollback();
			} catch (final Exception ex) {
			}

		} finally {

			try {
				if (!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (final Exception ex) {
			}

			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (final Exception ex) {
			}
		}

		return false;
	}

	/**
	 *
	 *
	 * Deleta um usuário na aplicaçao
	 *
	 *
	 */

	public boolean delete(final Long id) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		final String sql = "DELETE FROM usuario WHERE id = ?";

		try {
			connection = ConnectionFactory.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(sql);

			preparedStatement.setLong(1, id);
			preparedStatement.execute();
			connection.commit();

		} catch (final Exception e) {

			e.printStackTrace();

			try {
				connection.rollback();
			} catch (final Exception ex) {
			}

		} finally {

			try {
				if (!preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (final Exception ex) {
			}

			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (final Exception ex) {
			}
		}

		return false;
	}

}
