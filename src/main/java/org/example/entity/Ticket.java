package org.example.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "ticket")
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "from_planet_id")
    private Planet fromPlanet;

    @ManyToOne
    @JoinColumn(name = "to_planet_id")
    private Planet toPlanet;

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", client=" + (client != null ? client.getName() : "null") +
                ", fromPlanet=" + (fromPlanet != null ? fromPlanet.getName() : "null") +
                ", toPlanet=" + (toPlanet != null ? toPlanet.getName() : "null") +
                '}';
    }
}
