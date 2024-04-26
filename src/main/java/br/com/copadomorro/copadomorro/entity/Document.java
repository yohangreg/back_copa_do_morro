package br.com.copadomorro.copadomorro.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "Document")
public class Document {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;



}
