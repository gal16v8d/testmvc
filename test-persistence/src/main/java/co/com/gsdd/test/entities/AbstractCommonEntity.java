package co.com.gsdd.test.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import co.com.gsdd.test.enums.EntityState;
import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractCommonEntity implements Serializable {

	private static final long serialVersionUID = 1256501001903199589L;

	@Version
	@Column(name = "version", nullable = false)
	protected Long version;

	@Enumerated(EnumType.STRING)
	@Column(name = "status", length = 16, nullable = false)
	protected EntityState status;

	public AbstractCommonEntity() {
	    status = EntityState.ACTIVE;
	}

}
