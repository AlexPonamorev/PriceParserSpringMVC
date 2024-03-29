package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "brands")
public class Brand {
    public Brand() {
    }

    @Id
    @GenericGenerator(name = "generator", strategy = "increment")
    @GeneratedValue(generator = "generator")
    private Long brand_id;


    public Long getBrand_id() {
        return brand_id;
    }

    public void setBrand_id(Long brand_id) {
        this.brand_id = brand_id;
    }

    private String nameKey;

    public String getNameKey() {
        return nameKey;
    }

    public void setNameKey(String nameKey) {
        this.nameKey = nameKey;
    }

    private String nameValue;

    public String getNameValue() {
        return nameValue;
    }

    public void setNameValue(String nameValue) {
        this.nameValue = nameValue;
    }

    @OneToMany(mappedBy = "brand", fetch = FetchType.LAZY)
    private List<Model> modelsList = new ArrayList<>();


    public List<Model> getModelsList() {
        return modelsList;
    }

    public void setModelsList(List<Model> modelsList) {
        this.modelsList = modelsList;
    }
}
