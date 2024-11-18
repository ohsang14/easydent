package webproject.easydent.entities;

import jakarta.persistence.*;

@Entity
public class Category {

    @Column(name = "category_id")
    @Id
    String id;

    String categoryName;

    //병원코드 연결
    @ManyToOne
    @JoinColumn(name = "businessNum")
    private Dentistry dentistry;
}
