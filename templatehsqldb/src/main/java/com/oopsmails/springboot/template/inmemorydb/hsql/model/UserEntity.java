package com.oopsmails.springboot.template.inmemorydb.hsql.model;

import static javax.persistence.CascadeType.ALL;

import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UserEntity {

    @Id
//	@GeneratedValue(strategy = GenerationType.AUTO)
//	@Column(name="ID", nullable = false)
    @Column(name = "userid", nullable = false)
    private Integer userId;

    @Column(name = "username", nullable = false)
    private String userName;

    @Column(name = "useremail")
    private String userEmail;

    @Column(name = "address")
    private String address;

    //	@OneToMany(cascade=ALL, mappedBy="userEntity")
//	@JsonIgnore
    @OneToMany(cascade = ALL, fetch = FetchType.LAZY)
    @Embedded
    @JoinColumn(name = "userid", referencedColumnName = "userid", insertable = false, updatable = false)
    private Set<CartEntity> carts = new HashSet<>();

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(Set<CartEntity> carts) {
        this.carts = carts;
    }
}
