package com.fullcycle.FCCatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GenreTests {
    
    @Test
    public void createGenreWithNameTest() {
        final Genre entity = new Genre("Genre 1");
        assertNotNull(entity);
        assertEquals(entity.getName(), "Genre 1");
        assertTrue(entity.isValidUUID(entity.getId().toString()));

        assertThrows(IllegalArgumentException.class, () -> new Genre((String) null));
        assertThrows(IllegalArgumentException.class, () -> new Genre((String) null, null));
        assertThrows(IllegalArgumentException.class, () -> new Genre(""));
    }

    @Test
    public void createGenreWithNameAndCategoryTest() {
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        final List<Category> categories = new ArrayList<Category>();
        categories.add(category1);
        categories.add(category2);

        final Genre entity = new Genre("Genre 1", categories);

        assertNotNull(categories);
        assertNotNull(entity);
        assertEquals("Genre 1", entity.getName());
        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertEquals("Category 1", entity.getCategories().get(0).getName());
        assertEquals("Category 2", entity.getCategories().get(1).getName());
        assertTrue(entity.getCategories().get(0).isValidUUID(entity.getCategories().get(0).getId().toString()));
        assertTrue(entity.getCategories().get(1).isValidUUID(entity.getCategories().get(1).getId().toString()));
        assertEquals(2, entity.getCategories().size());
    }

    @Test
    public void assertExceptionOnCategoriesWithNullTest() {
        final Genre entity = new Genre("Genre 1");
        assertThrows(IllegalArgumentException.class, () -> entity.setCategories(null));
        assertThrows(IllegalArgumentException.class, () -> entity.addCategory(null));
        assertThrows(IllegalArgumentException.class, () -> entity.removeCategory(null));
    }

    @Test
    public void addCategorytoGenreTest() {
        final Genre entity = new Genre("Genre 1");
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        assertNotNull(entity);
        assertEquals(entity.getName(), "Genre 1");
        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertNotNull(entity.getCategories());

        entity.addCategory(category1);
        entity.addCategory(category2);
        assertEquals(2, entity.getCategories().size());
    }

    @Test
    public void removeCategoryFromGenreTest() {
        final Genre entity = new Genre("Genre 1");

        assertNotNull(entity);
        assertNotNull(entity.getCategories());

        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        entity.addCategory(category1);
        entity.addCategory(category2);

        assertEquals(2, entity.getCategories().size());
        entity.removeCategory(category1);
        assertEquals(1, entity.getCategories().size());
        entity.removeCategory(category2);
        assertEquals(0, entity.getCategories().size());
    }
}
