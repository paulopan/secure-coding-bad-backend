package net.croz.owasp.badexample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Table(name = "product_order")
public class Order {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_order_id_generator")
    @SequenceGenerator(name = "product_order_id_generator", sequenceName = "product_order_id_seq",
        allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "buyer_id",
        foreignKey = @ForeignKey(name = "fk_product_order_user_buyer"))
    private UserBuyer buyer;

    @ManyToOne
    @JoinColumn(name = "product_id",
        foreignKey = @ForeignKey(name = "fk_product_order_product"))
    private Product product;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "quantity")
    private Integer quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserBuyer getBuyer() {
        return buyer;
    }

    public void setBuyer(UserBuyer buyer) {
        this.buyer = buyer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}