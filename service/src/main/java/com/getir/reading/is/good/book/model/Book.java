package com.getir.reading.is.good.book.model;

import com.getir.reading.is.good.common.model.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseEntity {

    private int numberOfStock = 0;

    private String name;

    private double price;
}
