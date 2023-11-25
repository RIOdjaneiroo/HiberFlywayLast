package org.example.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "planet")
public class Planet {
    @Id
    @Column(name = "id", length = 10)
    private String id;

    @Column(name = "name", nullable = false, length = 500)
    private String name;
}
