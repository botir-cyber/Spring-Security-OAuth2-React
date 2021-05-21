package uz.pdp.demo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import uz.pdp.demo.entity.template.AbsEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class AttachmentContent extends AbsEntity {

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private Attachment attachment;

    private byte[] content;
}
