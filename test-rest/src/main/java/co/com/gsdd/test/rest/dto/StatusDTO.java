package co.com.gsdd.test.rest.dto;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "statusDTO", propOrder = { "code", "message" })
public class StatusDTO implements Serializable {

    private static final long serialVersionUID = 4054932872540801528L;
    protected String code;
	protected String message;
	
	//For jaxb mapping
	public StatusDTO() {
	    super();
	}

	public StatusDTO(String code, String message) {
	    super();
		this.code = code;
		this.message = message;
	}

	@Override
	public String toString() {
		return "{" + code + ": " + message + "}";
	}
}
