package com.example.deleverysystem.entity;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity

@Data
@Table(name = "ward")
public class Ward {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ward_id")
    private Integer wardId;

    @Column(name = "wardName")
    private String wardName ;


    public Ward(){
        super();
    }

    public Ward( String wardName){
        super();

        this.wardName = wardName ;
    }


}
