package ru.same.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Destination {
    //todo заменить имя таблицы на конфиг
    @Column(name = "data")
    @Id
    private String data;

    public Destination(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }
}
