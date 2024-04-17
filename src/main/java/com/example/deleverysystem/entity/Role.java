package com.example.deleverysystem.entity;


import jakarta.persistence.*;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@Setter
@Entity
@Table(name = "roles")


public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Integer roleId ;

    @Column(name = "authority")
    private String authority ;


    public Role(){
        super();
    }

    public  Role(String authority){
       this.authority = authority;
    }

    public Role (Integer roleId  , String authority){
        this.roleId = roleId ;
        this.authority = authority ;
    }

    @Override
    public String getAuthority() {
        return this.authority;
    }

    public Integer getRoleId() {
        return roleId;
    }

}
