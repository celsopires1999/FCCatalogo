package com.fullcycle.FCCatalogo.domain.entity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class CastMemberTests {
    
    @Test
    public void throwIllegalArgumentExceptionWhenNameIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new CastMember(null));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlank() {
        assertThrows(IllegalArgumentException.class, () -> new CastMember(""));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsNullAndTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new CastMember(null, null));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenNameIsBlankAndTypeIsNull() {
        assertThrows(IllegalArgumentException.class, () -> new CastMember("", null));
    }

    @Test
    public void throwIllegalArgumentExceptionWhenTypeIsNull() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new CastMember("CastMember 1", null));
        assertEquals("type is marked not null but is null", exception.getMessage()); 
        }
    
    @Test
    public void createCastMemberWithNameTest() {
        final CastMember entity = new CastMember("CastMember");
        assertEquals("CastMember", entity.getName());
        assertNotNull(entity);
        assertEquals("CastMember", entity.getName());
        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertNull(entity.getType());
    }

    @Test
    @EnumSource(CastMemberType.class)
    public void createCastMemberWithNameAndType() {
        final CastMember entity = new CastMember("Cast Member 1", CastMemberType.TYPE1);
        assertNotNull(entity);
        assertEquals("Cast Member 1", entity.getName());
        assertTrue(entity.isValidUUID(entity.getId().toString()));
        assertTrue(CastMemberType.valueOf(entity.getType()));
        assertEquals(CastMemberType.TYPE1, entity.getType());
    }

    @Test
    public void throwIllegalArgumentExceptionWhenTypeIsNotValid() throws IllegalArgumentException {
        assertThrows(IllegalArgumentException.class, () -> {
            CastMember entity = mock(CastMember.class);
            entity.setName("Cast Member 1");
            doThrow(IllegalArgumentException.class).when(entity).setType(CastMemberType.TYPE2);
            entity.setType(CastMemberType.TYPE2);
        });
    }
}
