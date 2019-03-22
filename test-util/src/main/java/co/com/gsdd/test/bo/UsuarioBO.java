package co.com.gsdd.test.bo;

import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.com.gsdd.test.constants.NumericConstants;
import co.com.gsdd.test.entities.Usuario;


@Component(value = UsuarioBO.NOMBRE_BO)
@Transactional
public class UsuarioBO extends BO {
    
	public static final String NOMBRE_BO = "usuarioBO";
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioBO.class);

	public Usuario findById(String id) {
		return dao.find(Usuario.class, id);
	}

	public Usuario findByLoginAndPass(String login, String pass) {
		List<Usuario> u = dao.executeNamedQuery(
				Usuario.GET_USUARIO_BY_LOGIN_AND_CONTRASENA, login, pass);
		return (u != null && !u.isEmpty()) ? u
				.get(NumericConstants.ZERO) : null;
	}

	public void createUsuario(Usuario u) {
		LOGGER.info("Creating: " + u.toString());
		dao.persist(u);
	}

	public void updateUsuario(Usuario u) {
		LOGGER.info("Updating: " + u.toString());
		dao.update(u);
	}
	
	public void deleteUsuario(Usuario u) {
	    LOGGER.info("Deleting: " + u.toString());
        dao.physicalDelete(Usuario.class, u.getIdUsuario());
	}

	public List<Usuario> getUsuarioList() {
		List<Usuario> lu = dao.executeNamedQuery(Usuario.GET_ALL_USUARIO);
		Collections.sort(lu);
		return lu;
	}

}
