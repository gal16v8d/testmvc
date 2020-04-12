package co.com.gsdd.test.bo;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import co.com.gsdd.test.constants.NumericConstants;
import co.com.gsdd.test.entities.Usuario;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component(value = UsuarioBO.NOMBRE_BO)
@Transactional
public class UsuarioBO extends BO {
    
	public static final String NOMBRE_BO = "usuarioBO";

	public Usuario findById(String id) {
		return getDao().find(Usuario.class, id);
	}

	public Usuario findByLoginAndPass(String login, String pass) {
		List<Usuario> users = getDao().executeNamedQuery(
				Usuario.GET_USUARIO_BY_LOGIN_AND_CONTRASENA, login, pass);
		return (users != null && !users.isEmpty()) ? users
				.get(NumericConstants.ZERO) : null;
	}

	public void createUsuario(Usuario user) {
		log.info("Creating: {}", user);
		getDao().persist(user);
	}

	public void updateUsuario(Usuario user) {
		log.info("Updating: {}", user);
		getDao().update(user);
	}
	
	public void deleteUsuario(Usuario user) {
	    log.info("Deleting: {}", user);
	    getDao().physicalDelete(Usuario.class, user.getIdUsuario());
	}

	public List<Usuario> getUsuarioList() {
		List<Usuario> lu = getDao().executeNamedQuery(Usuario.GET_ALL_USUARIO);
		Collections.sort(lu);
		return lu;
	}

}
