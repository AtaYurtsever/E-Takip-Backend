package takip.back.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class ExtraQuestion {

    @Id
    @GeneratedValue
    private Long eid;

    private String id;
    private String text;
}
