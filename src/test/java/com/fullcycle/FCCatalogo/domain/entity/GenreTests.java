package com.fullcycle.FCCatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class GenreTests {
    
    @Test
    public void createGenre() {
        final Genre entity = new Genre("Genre 1");
        assertNotNull(entity);
        assertEquals(entity.getName(), "Genre 1");
        assertTrue(entity.isValidUUID(entity.getId().toString()));

        final Category category = new Category("Category 1");
        entity.addCategory(category);
        entity.removeCategory(category);
    }

}
