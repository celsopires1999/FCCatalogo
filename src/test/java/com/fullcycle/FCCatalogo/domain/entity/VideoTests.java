package com.fullcycle.FCCatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Calendar;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class VideoTests {
    @Test
    public void throwIllegalArgumentExceptionWhenTitleIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Video((String) null, null, null, (Boolean) null ));
        assertEquals("title is marked not null but is null", exception.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenTitleIsBlank() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Video("", null, null, (Boolean) null ));
        assertEquals("title is marked not blank but is blank", exception.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenYearLaunchedIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Video("Video 1", null, null, (Boolean) null ));
        assertEquals("yearLaunched is marked not null but is null", exception.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenYearLaunchedIsLessThan1700() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Video("Video 1", null, 1699, (Boolean) null ));
        assertEquals("yearLaunched must be greater than 1700", exception.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenYearLaunchedIsGreatherThanCurrentYear() {
        int nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Video("Video 1", null, nextYear, (Boolean) null ));
        assertEquals("yearLauched is greather than current year", exception.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenCategoriesIsNull() {
        final Video entity = new Video("Video 1", "Description 1", 2021, 90.0f);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> entity.setCategories(null));
        assertEquals("categories is marked not null but is null", exception.getMessage()); 
    } 

    @Test
    public void throwIllegalArgumentExceptionWhenCategoryIsNull() {
        final Video entity = new Video("Video 1", "Description 1", 2021, 90.0f);

        IllegalArgumentException exceptionAddCategory = assertThrows(IllegalArgumentException.class, () -> entity.addCategory(null));
        assertEquals("category is marked not null but is null", exceptionAddCategory.getMessage()); 

        IllegalArgumentException exceptionRemoveCategory = assertThrows(IllegalArgumentException.class, () -> entity.removeCategory(null));
        assertEquals("category is marked not null but is null", exceptionRemoveCategory.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenGenresIsNull() {
        final Video entity = new Video("Video 1", "Description 1", 2021, 90.0f);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> entity.setGenres(null));
        assertEquals("genres is marked not null but is null", exception.getMessage()); 
    } 

    @Test
    public void throwIllegalArgumentExceptionWhenGenreIsNull() {
        final Video entity = new Video("Video 1", "Description 1", 2021, 90.0f);

        IllegalArgumentException exceptionAddGenre = assertThrows(IllegalArgumentException.class, () -> entity.addGenre(null));
        assertEquals("genre is marked not null but is null", exceptionAddGenre.getMessage()); 

        IllegalArgumentException exceptionRemoveGenre = assertThrows(IllegalArgumentException.class, () -> entity.removeGenre(null));
        assertEquals("genre is marked not null but is null", exceptionRemoveGenre.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenCastMembersIsNull() {
        final Video entity = new Video("Video 1", "Description 1", 2021, 90.0f);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> entity.setCastMembers(null));
        assertEquals("castMembers is marked not null but is null", exception.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenCastMemberIsNull() {
        final Video entity = new Video("Video 1", "Description 1", 2021, 90.0f);

        IllegalArgumentException exceptionAddCastMember = assertThrows(IllegalArgumentException.class, () -> entity.addCastMember(null));
        assertEquals("member is marked not null but is null", exceptionAddCastMember.getMessage()); 

        IllegalArgumentException exceptionRemoveCastMember = assertThrows(IllegalArgumentException.class, () -> entity.removeCastMember(null));
        assertEquals("member is marked not null but is null", exceptionRemoveCastMember.getMessage()); 
    }

    @Test
    public void throwIllegalArgumentExceptionWhenVideoFilesIsNull() {
        final Video entity = new Video("Video 1", "Description 1", 2021, 90.0f);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> entity.setVideoFiles(null));
        assertEquals("files is marked not null but is null", exception.getMessage()); 
    }

    @Test
    public void createVideoWithTitleAndYearLaunchedTest() {
        final Video entity = new Video("Video 1", "Description 1", 2009, false);

        assertNotNull(entity);
        assertEquals("Video 1", entity.getTitle());
        assertEquals(2009, entity.getYearLaunched());

        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertEquals("Description 1", entity.getDescription());
        assertFalse(entity.getOpened());
    }

    @Test
    public void createVideoWithTitleAndYearLaunchedAndRatingAndDurationTest() {
        final Video entity = new Video("Video 1", "Description 1", 2009, false, "Good", 90.0f);

        assertNotNull(entity);
        assertEquals("Video 1", entity.getTitle());
        assertEquals(2009, entity.getYearLaunched());
        assertEquals("Good", entity.getRating());
        assertEquals(90.0f, entity.getDuration());

        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertEquals("Description 1", entity.getDescription());
        assertFalse(entity.getOpened());
    }

    @Test
    public void createVideoWithoutFilesTest() {
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");
        final Genre genre1 = new Genre("Genre 1");
        final Genre genre2 = new Genre("Genre 2");
        final CastMember castMember1 = new CastMember("Cast Member 1");
        final CastMember castMember2 = new CastMember("Cast Member 2");
        final List<Category> categories = new ArrayList<Category>();
        final List<Genre> genres = new ArrayList<Genre>();
        final List<CastMember> castMembers = new ArrayList<CastMember>();

        assertNotNull(category1);
        assertNotNull(category2);
        categories.add(category1);
        categories.add(category2);

        assertNotNull(genre1);
        assertNotNull(genre2);
        genres.add(genre1);
        genres.add(genre2);

        assertNotNull(castMember1);
        assertNotNull(castMember2);
        castMembers.add(castMember1);
        castMembers.add(castMember2);

        final Video entity = new Video("Video 1", "Description 1", 2009, false, "Good", 90.0f, categories, genres, castMembers);

        assertNotNull(entity);
        assertEquals("Video 1", entity.getTitle());
        assertEquals(2009, entity.getYearLaunched());
        assertEquals("Good", entity.getRating());
        assertEquals(90.0f, entity.getDuration());

        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertEquals("Description 1", entity.getDescription());
        assertFalse(entity.getOpened());

        assertNotNull(entity.getCategories());
        assertNotNull(entity.getGenres());
        assertNotNull(entity.getCastMembers());

        assertEquals(2, entity.getCategories().size());
        assertEquals(2, entity.getGenres().size());
        assertEquals(2, entity.getCastMembers().size());

        assertEquals("Category 1", entity.getCategories().get(0).getName());
        assertEquals("Category 2", entity.getCategories().get(1).getName());

        assertEquals("Genre 1", entity.getGenres().get(0).getName());
        assertEquals("Genre 2", entity.getGenres().get(1).getName());

        assertEquals("Cast Member 1", entity.getCastMembers().get(0).getName());
        assertEquals("Cast Member 2", entity.getCastMembers().get(1).getName());
    }

    @Test
    public void createVideoWithoutFilesWithAddGenresTest() {
        final Genre genre1 = new Genre("Genre 1");
        final Genre genre2 = new Genre("Genre 2");

        assertNotNull(genre1);
        assertNotNull(genre2);

        final Video entity = new Video("Video 1", "Description 1", 2009, false);

        entity.addGenre(genre1);
        entity.addGenre(genre2);

        assertNotNull(entity);
        assertEquals("Video 1", entity.getTitle());
        assertEquals("Description 1", entity.getDescription());
        assertEquals(2009, entity.getYearLaunched());
        assertFalse(entity.getOpened());
        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertEquals(2, entity.getGenres().size()); 
    }

    @Test
    public void createVideoWithoutFilesWithAddCategoriesTest() {
        final Category category1 = new Category("Category 1");
        final Category category2 = new Category("Category 2");

        assertNotNull(category1);
        assertNotNull(category2);

        final Video entity = new Video("Video 1", "Description 1", 2009, false);

        entity.addCategory(category1);
        entity.addCategory(category2);

        assertNotNull(entity);
        assertEquals("Video 1", entity.getTitle());
        assertEquals("Description 1", entity.getDescription());
        assertEquals(2009, entity.getYearLaunched());
        assertFalse(entity.getOpened());
        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertEquals(2, entity.getCategories().size()); 
    }

    @Test
    public void createVideoWithoutFilesWithAddCastMembersTest() {
        final CastMember castMember1 = new CastMember("Cast Member 1");
        final CastMember castMember2 = new CastMember("Cast Member 2");

        assertNotNull(castMember1);
        assertNotNull(castMember2);

        final Video entity = new Video("Video 1", "Description 1", 2009, false);

        entity.addCastMember(castMember1);
        entity.addCastMember(castMember2);

        assertNotNull(entity);
        assertEquals("Video 1", entity.getTitle());
        assertEquals("Description 1", entity.getDescription());
        assertEquals(2009, entity.getYearLaunched());
        assertFalse(entity.getOpened());
        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertEquals(2, entity.getCastMembers().size());     
    }
}
