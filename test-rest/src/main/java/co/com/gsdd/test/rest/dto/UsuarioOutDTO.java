package co.com.gsdd.test.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "usuarioOutDTO", propOrder = { "idUsuario",
		"numeroIdentificacion", "nombres", "apellidos", "login" })
@XmlRootElement
public class UsuarioOutDTO implements Serializable {

    private static final long serialVersionUID = 6062261611614478361L;
    protected long idUsuario;
	protected String numeroIdentificacion;
	protected String nombres;
	protected String apellidos;
	protected String login;

}
