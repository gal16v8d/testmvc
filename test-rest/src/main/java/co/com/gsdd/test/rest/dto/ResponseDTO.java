package co.com.gsdd.test.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import co.com.gsdd.test.rest.constants.MessageConstants;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "respuestaDTO", propOrder = { "statusDTO" })
@XmlSeeAlso({ ListaUsuarioOutDTO.class })
public class ResponseDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    protected StatusDTO statusDTO;

    public ResponseDTO() {
        statusDTO = new StatusDTO(MessageConstants.SUCCESS_CODE,
                MessageConstants.SUCCESS);
    }

}
