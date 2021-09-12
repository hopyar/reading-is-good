package com.getir.reading.is.good.customer.model;

import com.getir.reading.is.good.common.model.BaseEntity;
import com.getir.reading.is.good.order.model.Order;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity {

    @Column(unique = true)
    private String email;

    @OneToMany(mappedBy = "customer")
    private List<Order> orders = new ArrayList<>();
}
