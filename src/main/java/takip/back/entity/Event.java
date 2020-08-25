package takip.back.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@Transactional
@NoArgsConstructor
public class Event {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name="TITLE")
    private String title;
    @Column(name="DESCRIPTION", length = 1024)
    private String description;
    @Column(name="START_DATE")
    private LocalDateTime startDate;
    @Column(name="END_DATE")
    private LocalDateTime endDate;
    @Column(name="START_TIME")
    private LocalDateTime time;
    @Column(name="QUOTA")
    private Long quota;

    @Column(name = "LON")
    private Long lon;
    @Column(name = "LAT")
    private Long lat;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "EVENT_ORGANIZER")
    @JsonBackReference("ORGANIZER_REFERENCE")
    private Users organizer;



    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name = "event_id")
    private Set<ExtraQuestion> extraQuestions;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    private Set<Applicant> applicants;


    public Event addApplicant(Applicant a){
        if(applicants == null) applicants = new HashSet<>();
        applicants.add(a);
        return this;
    }
}
