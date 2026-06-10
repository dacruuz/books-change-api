package br.com.bookschange.api.domain.models;

import br.com.bookschange.api.domain.enums.CurrentCondition;
import br.com.bookschange.infrastructure.shared.models.BaseModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "books")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Book extends BaseModel {

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String author;

    @Column(nullable = false)
    private String publisher;

    @Column
    private String resume;

    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookCategory> bookCategories = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private CurrentCondition currentCondition;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    public void addCategories(List<Category> categories) {
        categories.forEach(this::addCategory);
    }

    public void addCategory(Category category) {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setBook(this);
        bookCategory.setCategory(category);

        this.bookCategories.add(bookCategory);
    }

    public void removeCategory(Category category) {
        this.bookCategories.removeIf(
                bc -> bc.getCategory().getUuid().equals(category.getUuid())
        );
    }

    public void replaceCategories(List<Category> categories) {
        removeObsoleteCategories(categories);
        addMissingCategories(categories);
    }

    private void removeObsoleteCategories(List<Category> categories) {
        this.bookCategories.removeIf(bookCategory ->
                categories.stream()
                        .noneMatch(category ->
                                category.getUuid().equals(bookCategory.getCategory().getUuid())));
    }

    private void addMissingCategories(List<Category> categories) {
        categories.forEach(category -> {
            boolean exists = this.bookCategories.stream()
                    .anyMatch(bookCategory ->
                            bookCategory.getCategory().getUuid().equals(category.getUuid()));

            if (!exists) {
                addCategory(category);
            }
        });
    }
}
