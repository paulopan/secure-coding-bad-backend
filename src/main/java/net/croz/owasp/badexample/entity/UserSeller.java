package net.croz.owasp.badexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "user_seller")
@PrimaryKeyJoinColumn(name = "id")
public class UserSeller extends AuthUser {

    @Column(name = "oib")
    private String oib;

    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Product> products;

    public String getOib() {
        return oib;
    }

    public void setOib(String oib) {
        this.oib = oib;
    }

    @JsonIgnore
    public List<Product> getProducts() {
        if (this.products == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(this.products);
        }

    }

    public void setProduct(List<Product> products) {
        if (this.products == null) {
            this.products = new ArrayList<>(products);
        } else {
            this.products.clear();
            this.products.addAll(products);
        }

    }

}
