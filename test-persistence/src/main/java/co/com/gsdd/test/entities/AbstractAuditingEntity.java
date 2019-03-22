package co.com.gsdd.test.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import co.com.gsdd.test.enums.EntityState;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper=false)
@MappedSuperclass
public abstract class AbstractAuditingEntity extends AbstractCommonEntity {

	private static final long serialVersionUID = 2453272339020707384L;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "createdDate")
    protected Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "lastModifiedDate")
    protected Date lastModifiedDate;

	public void defineState() {
		if (status == null) {
		    status = EntityState.ACTIVE;
		}
	}
	
    @PrePersist
    public void prePersist() {
        defineState();
        if (createdDate == null) {
            createdDate = new Date();
        }
    }

    @PreUpdate
    public void preUpdate() {
        defineState();
        lastModifiedDate = new Date();
    }
}
