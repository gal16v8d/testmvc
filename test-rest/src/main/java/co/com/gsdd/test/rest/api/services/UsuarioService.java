package co.com.gsdd.test.rest.api.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import co.com.gsdd.test.bo.FactoryBO;
import co.com.gsdd.test.bo.UsuarioBO;
import co.com.gsdd.test.entities.Usuario;
import co.com.gsdd.test.rest.constants.MessageConstants;
import co.com.gsdd.test.rest.constants.UrlConstants;
import co.com.gsdd.test.rest.dto.ListaUsuarioOutDTO;
import co.com.gsdd.test.rest.dto.UsuarioOutDTO;
import co.com.gsdd.test.rest.exceptions.TechnicalException;

@Controller
@RequestMapping(value = UrlConstants.URL_USU)
@Secured(UrlConstants.SERVICE_ROL)
public class UsuarioService {

    private static final Logger LOGGER =
            LoggerFactory.getLogger(UsuarioService.class);

    @RequestMapping(value = "listarUsuarios", method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ListaUsuarioOutDTO listarUsuarios() {
        ListaUsuarioOutDTO outDTO = new ListaUsuarioOutDTO();
        try {
            // invocation log
            LOGGER.info(
                    new StringBuilder().append(MessageConstants.SERVICE_CALL)
                            .append("listarUsuarios").toString());

            outDTO = getDataForUsuarioList();
        } catch (TechnicalException e) {
            setErrorResponse(outDTO, e);
        } catch (Exception e) {
            setErrorResponse(outDTO, e);
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(MessageConstants.SERVICE_RESPONSE
                + (outDTO != null ? outDTO.getStatusDTO().getCode() : null));
        return outDTO;
    }

    private void setErrorResponse(ListaUsuarioOutDTO outDTO, Exception e) {
        outDTO.getStatusDTO().setCode(MessageConstants.ERROR_CODE);
        outDTO.getStatusDTO().setMessage(e.getMessage());
    }

    private ListaUsuarioOutDTO getDataForUsuarioList()
            throws TechnicalException {
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
