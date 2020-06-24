package com.site.app.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Feedback {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY, generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long id;
    private String name;
    private String email;
    private String message;

    protected Feedback() {
    }

    public Feedback(String name, String email, String message) {
        this.name = name;
        this.email = email;
        this.message = message;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return String.format(
                "Feedback[id=%d, name='%s', email='%s']",
                id, name, email
        );
    }
}
