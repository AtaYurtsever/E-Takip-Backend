package takip.back.entity;

import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.security.cert.Extension;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Applicant {

    @Id
    @GeneratedValue
    public Long id;

    public String name;
    public String surname;
    public String email;
    @Column(name = "TCKNO",unique = true)
    public String TCKno;
    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "applicant_id")
    public Set<ExtraQuestion> extraQuestions;
    public LocalDateTime applicationDate;
}
