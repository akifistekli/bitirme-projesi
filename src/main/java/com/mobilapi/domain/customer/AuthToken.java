package com.mobilapi.domain.customer;



import javax.persistence.*;

@Entity
@Table(name = "auth_token")
public class AuthToken extends AbstractAuditableEntity {

    private static final long serialVersionUID = -9001508296580395084L;

    private String token;

    private String series;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_auth_token", nullable = false)
    private Account account;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}

