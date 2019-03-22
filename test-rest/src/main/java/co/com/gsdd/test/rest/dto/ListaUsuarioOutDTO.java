package co.com.gsdd.test.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "listaUsuarioOutDTO", propOrder = { "usuarios" })
@XmlRootElement
public class ListaUsuarioOutDTO extends ResponseDTO {

    private static final long serialVersionUID = 6987589715812138476L;
    
    @XmlElement(nillable = true)
	protected List<UsuarioOutDTO> usuarios;
	
	public ListaUsuarioOutDTO() {
	    usuarios = new ArrayList<>();
	}

}
