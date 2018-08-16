package ru.same.entities;

import javax.persistence.*;

@Entity
//todo заменить имя таблицы на конфиг
@Table(name = "source")
public class Source {
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    //todo заменить имя таблицы на конфиг
    @Lob
    @Column(name = "data")
    private String data;

    public Source(String data) {
        this.data = data;
    }

    public Long getId() {
        return id;
    }

    public String getData() {
        return data;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setData(String data) {
        this.data = data;
    }
}
