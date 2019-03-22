package co.com.gsdd.test.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@Entity
@Table(name = "usuario")
@NamedQueries({
		@NamedQuery(name = Usuario.GET_USUARIO_BY_LOGIN_AND_CONTRASENA, query = "SELECT u FROM Usuario u WHERE u.login = ?1 AND u.contrasena = ?2"),
		@NamedQuery(name = Usuario.GET_ALL_USUARIO, query = "SELECT u FROM Usuario u") })
public class Usuario extends AbstractAuditingEntity implements
		Comparable<Usuario> {

	private static final long serialVersionUID = 1L;
	/**
	 * Get usuario by login and contrasena
	 * @param ?1: login de usuario
	 * @param ?2: contraseña del usuario
	 */
	public static final String GET_USUARIO_BY_LOGIN_AND_CONTRASENA = "Usuario.getUsuarioByLoginAndContrasena";

	/**
	 * Get usuario list.
	 */
	public static final String GET_ALL_USUARIO = "Usuario.getAllUsuario";

	@Id
	@Column(name = "idusuario")
	private Long idUsuario;

	@Column(length = 32, name = "numeroidentificacion")
	private String numeroIdentificacion;

	@Column(length = 64, name = "nombres")
	private String nombres;

	@Column(length = 64, name = "apellidos")
	private String apellidos;

	@Column(length = 32, unique = true, name = "login")
	private String login;

	@Column(length = 32, name = "contrasena")
	private String contrasena;

	public Usuario() {
		super();
		idUsuario = System.nanoTime();
	}

	@Override
	public int compareTo(Usuario o) {
		return this.nombres.compareTo(o.nombres);
	}

}
