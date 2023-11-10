package net.croz.owasp.badexample.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
@Table(name = "product_comment")
public class ProductComment {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_comment_id_generator")
    @SequenceGenerator(name = "product_comment_id_generator", sequenceName = "product_comment_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "text")
    private String text;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @ManyToOne
    @JoinColumn(name = "product_id",
        foreignKey = @ForeignKey(name = "fk_product_comment_product"))
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    @JsonIgnore
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
