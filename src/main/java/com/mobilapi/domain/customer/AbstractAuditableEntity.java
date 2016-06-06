package com.mobilapi.domain.customer;


import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;

@SuppressWarnings("serial")
@MappedSuperclass
public class AbstractAuditableEntity extends AbstractPersistable<Long> {

    @Version
    @Column(name = "version")
    private Long version;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime createdDate;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime lastModifiedDate;

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public DateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(DateTime createdDate) {
        this.createdDate = createdDate;
    }

    public DateTime getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(DateTime lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    @PrePersist
    public void onCreate() {
        this.createdDate = new DateTime();
    }

    @PreUpdate
    public void onUpdate() {
        this.lastModifiedDate = new DateTime();
    }

}
