package ru.same.entities;

import javax.persistence.*;

@Entity
@Table(name = "source_table", schema = "source_schema")
public class Source {
    @Column(name = "source_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Lob
    @Column(name = "source_column")
    private String data;

    public Source(String data) {
        this.data = data;
    }

    public Source() {
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
