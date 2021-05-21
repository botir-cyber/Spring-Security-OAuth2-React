package uz.pdp.demo.entity.template;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@Data
@MappedSuperclass
public abstract class AbsNameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nameUz;
    private String nameKr;
//
//    private String nameRu;
//
//    private String nameEn;
//    public AbsNameEntity(String nameUz, String nameRu, String nameEn) {
//    }
}
