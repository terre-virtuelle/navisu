/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bzh.terrevirtuelle.navisu.domain.ship.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;


/**
 * NaVisu
 *
 * @date 26 mai 2015
 * @author Serge Morvan
 */
@Converter(autoApply = true)
public class LocalDateTimePersistenceConverter implements AttributeConverter<java.time.LocalDateTime, java.sql.Timestamp> {

    @Override
    public java.sql.Timestamp convertToDatabaseColumn(java.time.LocalDateTime attribute) {
        return attribute == null ? null : java.sql.Timestamp.valueOf(attribute);
    }

    @Override
    public java.time.LocalDateTime convertToEntityAttribute(java.sql.Timestamp dbData) {
        return dbData == null ? null : dbData.toLocalDateTime();
    }
}
