package net.croz.owasp.badexample.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(name = "product")
public class Product {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_generator")
    @SequenceGenerator(name = "product_id_generator", sequenceName = "product_id_seq", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "image_name")
    private String imageName;

    @OneToMany
    @JoinColumn(name = "product_id",
        foreignKey = @ForeignKey(name = "fk_product_comment_product"))
    private List<ProductComment> comments;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private UserSeller seller;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String productImage) {
        this.imageName = productImage;
    }

    public List<ProductComment> getComments() {
        if (this.comments == null) {
            return Collections.emptyList();
        } else {
            return new ArrayList<>(this.comments);
        }

    }

    public void setComments(List<ProductComment> comments) {
        if (this.comments == null) {
            this.comments = new ArrayList<>(comments);
        } else {
            this.comments.clear();
            this.comments.addAll(comments);
        }
    }

    public UserSeller getSeller() {
        return seller;
    }

    public void setSeller(UserSeller seller) {
        this.seller = seller;
    }

}
