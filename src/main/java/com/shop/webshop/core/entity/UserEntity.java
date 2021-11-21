package com.shop.webshop.core.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
public class UserEntity extends BaseEntity {
    @Column(name = "username")
    private String userName;

    @Column(name = "password")
    private String password;

    @Column(name = "fullname")
    private String fullName;

    @Column
    private String email;

    @Column
    private String phone;

    @Column
    private Integer status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rolecode")
    private RoleEntity role;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CartEntity> carts;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<CheckoutEntity> checkouts;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public RoleEntity getRole() {
        return role;
    }

    public void setRole(RoleEntity role) {
        this.role = role;
    }

    public List<CartEntity> getCarts() {
        return carts;
    }

    public void setCarts(List<CartEntity> carts) {
        this.carts = carts;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<CheckoutEntity> getCheckouts() {
        return checkouts;
    }

    public void setCheckouts(List<CheckoutEntity> checkouts) {
        this.checkouts = checkouts;
    }
}
