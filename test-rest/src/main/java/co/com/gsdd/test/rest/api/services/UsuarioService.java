package co.com.gsdd.test.rest.api.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.gsdd.test.bo.FactoryBO;
import co.com.gsdd.test.bo.UsuarioBO;
import co.com.gsdd.test.entities.Usuario;
import co.com.gsdd.test.rest.constants.MessageConstants;
import co.com.gsdd.test.rest.constants.UrlConstants;
import co.com.gsdd.test.rest.dto.ListaUsuarioOutDTO;
import co.com.gsdd.test.rest.dto.UsuarioOutDTO;
import co.com.gsdd.test.rest.exceptions.TechnicalException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping(value = UrlConstants.URL_USU)
@Secured(UrlConstants.SERVICE_ROL)
public class UsuarioService {

	@PostMapping(value = "listarUsuarios", produces = { MediaType.APPLICATION_XML_VALUE,
			MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public ListaUsuarioOutDTO listarUsuarios() {
		ListaUsuarioOutDTO outDTO = new ListaUsuarioOutDTO();
		try {
			// invocation log
			log.info(new StringBuilder().append(MessageConstants.SERVICE_CALL).append("listarUsuarios").toString());
			outDTO = getDataForUsuarioList();
		} catch (TechnicalException e) {
			setErrorResponse(outDTO, e);
		} catch (Exception e) {
			setErrorResponse(outDTO, e);
			log.error(e.getMessage(), e);
		}
		log.info(MessageConstants.SERVICE_RESPONSE + MessageConstants.PARAM,
				(outDTO != null ? outDTO.getStatusDTO().getCode() : null));
		return outDTO;
	}

	private void setErrorResponse(ListaUsuarioOutDTO outDTO, Exception e) {
		outDTO.getStatusDTO().setCode(MessageConstants.ERROR_CODE);
		outDTO.getStatusDTO().setMessage(e.getMessage());
	}

	private ListaUsuarioOutDTO getDataForUsuarioList() throws TechnicalException {
		UsuarioBO uBO = FactoryBO.getBean(UsuarioBO.class, UsuarioBO.NOMBRE_BO);
		List<Usuario> lu = uBO.getUsuarioList();

		if (lu != null && !lu.isEmpty()) {
			ListaUsuarioOutDTO outDTO = new ListaUsuarioOutDTO();
			List<UsuarioOutDTO> listaUsuarios = new ArrayList<>();
			UsuarioOutDTO uDTO = null;

			for (Usuario u : lu) {
				uDTO = new UsuarioOutDTO();
				uDTO.setIdUsuario(u.getIdUsuario());
				uDTO.setNumeroIdentificacion(u.getNumeroIdentificacion());
				uDTO.setNombres(u.getNombres());
				uDTO.setApellidos(u.getApellidos());
				uDTO.setLogin(u.getLogin());
				listaUsuarios.add(uDTO);
			}
			outDTO.getUsuarios().addAll(listaUsuarios);
			outDTO.getStatusDTO().setCode(MessageConstants.SUCCESS_CODE);
			return outDTO;
		} else {
			throw new TechnicalException(MessageConstants.MSJ_NO_RESULTS);
		}
	}

}
