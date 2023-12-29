package dev.tugba.taskapp.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name="Tasks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="body")
    private String body;

    @Column(name="title")
    private String title;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name= "user_id", referencedColumnName = "id")
    private User user;
}
