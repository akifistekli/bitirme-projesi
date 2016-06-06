package com.mobilapi.domain.order;

import com.mobilapi.domain.customer.Location;
import com.mobilapi.domain.customer.Account;
import com.mobilapi.domain.product.Product;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Account_Order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "order_products", joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")}
            , inverseJoinColumns = {@JoinColumn(name = "products_id", referencedColumnName = "id")})
    private List<Product> products;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id")
    private Account account;

    private DateTime createDate;

    private Long price;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "location_id")
    private Location location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public DateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(DateTime createDate) {
        this.createDate = createDate;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;

        Order order = (Order) o;

        return getId().equals(order.getId());

    }

    @Override
    public int hashCode() {
        return getId().hashCode();
    }

    @PrePersist
    public void setCreateDate() {
        this.createDate = new DateTime();
    }
}
