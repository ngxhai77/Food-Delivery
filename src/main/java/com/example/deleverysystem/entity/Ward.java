package com.example.deleverysystem.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
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


    public Integer getWardId() {
        return wardId;
    }

    public void setWardId(Integer wardId) {
        this.wardId = wardId;
    }

    public String getWardName() {
        return wardName;
    }

    public void setWardName(String wardName) {
        this.wardName = wardName;
    }

    @Override
    public String toString() {
        return "Ward{" +
                "wardId=" + wardId +
                ", wardName='" + wardName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ward ward = (Ward) o;

        if (wardId != null ? !wardId.equals(ward.wardId) : ward.wardId != null) return false;
        return wardName != null ? wardName.equals(ward.wardName) : ward.wardName == null;
    }


    // add one to many relationship with restaurants

    // add one to many relationship with users



}
