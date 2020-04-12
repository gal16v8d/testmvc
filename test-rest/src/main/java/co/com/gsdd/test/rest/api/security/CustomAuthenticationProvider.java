package co.com.gsdd.test.rest.api.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import co.com.gsdd.test.bo.FactoryBO;
import co.com.gsdd.test.bo.UsuarioBO;
import co.com.gsdd.test.constants.GeneralConstants;
import co.com.gsdd.test.entities.Usuario;
import co.com.gsdd.test.enums.EntityState;
import co.com.gsdd.test.rest.constants.MessageConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * Entrypoint for access web services.
 * 
 * @author Great System Development Dynamic [GSDD] <br>
 *         Alexander Galvis Grisales <br>
 *         alex.galvis.sistemas@gmail.com <br>
 *
 */
@Slf4j
@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {
	/**
	 * Auth for access secured web services.
	 * 
	 * @param authentication
	 * @return valid session id.
	 */
	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {

		String login = authentication.getName();
		String contrasena = authentication.getCredentials().toString();
		log.info(new StringBuilder()
				.append(MessageConstants.SERVICE_CALL)
				.append("autenticacion")
				.append(MessageConstants.SERVICE_PARAM)
				.append(GeneralConstants.OPEN_BRACKET)
				.append("login")
				.append(GeneralConstants.EQUAL)
				.append(MessageConstants.PARAM)
				.append(GeneralConstants.COMMA)
				.append("pass")
				.append(GeneralConstants.EQUAL)
				.append(MessageConstants.PARAM)
				.append(GeneralConstants.CLOSE_BRACKET).toString(), login, contrasena);

		UsuarioBO uBO = FactoryBO.getBean(UsuarioBO.class, UsuarioBO.NOMBRE_BO);
		Usuario usuario = uBO.findByLoginAndPass(login, contrasena);
		
		log.info("Usuario: {}", usuario);
		if (usuario != null) {
			if (EntityState.ACTIVE.equals(usuario.getStatus())) {
				String role = "ROLE_ADMIN";
				List<GrantedAuthority> grantedAuths = new ArrayList<>();
				grantedAuths.add(new SimpleGrantedAuthority(role));
				return new UsernamePasswordAuthenticationToken(
						login, contrasena, grantedAuths);
			}
			
			// usuario is INACTIVE
			throw new DisabledException(String.format(
					MessageConstants.USUARIO_INACTIVE, login));
		}
		// auth failed
		throw new BadCredentialsException(String.format(
				MessageConstants.AUTH_FAILED, login));
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return authentication.equals(UsernamePasswordAuthenticationToken.class);
	}
}