package br.com.bookschange.api.domain.models;

import br.com.bookschange.infrastructure.shared.models.BaseModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends BaseModel {

    @Column(nullable = false)
    private String label;

    @Column(nullable = false, unique = true)
    private String slug;

    @Column(nullable = false, unique = true)
    private String description;

    @OneToMany(mappedBy = "category")
    private List<BookCategory> bookCategories = new ArrayList<>();
}
