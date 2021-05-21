package uz.pdp.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.demo.entity.enums.AttachmentTypeEnum;
import uz.pdp.demo.entity.template.AbsEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Created by Pinup on 24.07.2019.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AttachmentType extends AbsEntity {
    private String contentTypes;
    private int width;
    private int height;
    @Column(unique = true)
    @Enumerated(value = EnumType.STRING)
    private AttachmentTypeEnum type;
    private Long size;

    public AttachmentType(String contentTypes, int width, int height, AttachmentTypeEnum type) {
        this.contentTypes = contentTypes;
        this.width = width;
        this.height = height;
        this.type = type;
    }

    public AttachmentType(String contentTypes, AttachmentTypeEnum type, Long size) {
        this.contentTypes = contentTypes;
        this.type = type;
        this.size = size;
    }
}
