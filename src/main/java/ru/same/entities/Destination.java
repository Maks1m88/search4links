package ru.same.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "destination_table", schema = "destination_schema")
public class Destination {
    //todo заменить имя таблицы на конфиг
    @Column(name = "destination_column")
    @Id
    private String data;

    public Destination(String data) {
        this.data = data;
    }

    public Destination() {
    }

    public String getData() {
        return data;
    }
}
