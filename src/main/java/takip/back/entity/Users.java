package takip.back.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Transactional
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Users {

    @Id
    @GeneratedValue
    public Long id;

    @Column(name="NAME", unique = true)
    public String name;
    @Column(name="EMAIL", unique = true)
    public String email;
    @Column(name="TCKNO", unique = true)
    public String TCKno;
    @Column(name = "SECRETKEY")
    public String secretKey;


//    public Users apply(Event e){
//        applications.add(e);
//        var x =e.getApplicants();
//        x.add(this);
//        e.setApplicants(x);
//        return this;
//    }

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL,mappedBy = "organizer")
    @JsonManagedReference("ORGANIZER_REFERENCE")
    public Set<Event> organizedEvents;

    public Event createEvent(
            String title,
            String description,
            LocalDateTime start,
            LocalDateTime end,
            LocalDateTime time,
            Set<ExtraQuestion> extraQuestions,
            Long quota,
            Long lon,
            Long lat
    ){
        var event = new Event(
                null,
                title,
                description,
                start,
                end,
                time,
                quota,
                lon,
                lat,
                this,
                extraQuestions,
                new HashSet<Applicant>());
        organizedEvents.add(event);
        return event;
    }

    public Event updateEvent(
            Long id,
            String title,
            String description,
            LocalDateTime start,
            LocalDateTime end,
            LocalDateTime time,
            Set<ExtraQuestion> extraQuestions,
            Long quota,
            Long lon,
            Long lat
    ){
        for(var t:organizedEvents){
            if(t.id == id){
                t.setTitle(title);
                t.setDescription(description);
                t.setStartDate(start);
                t.setEndDate(end);
                t.setTime(time);
                t.setQuota(quota);
                t.setExtraQuestions(extraQuestions);
                t.setLon(lon);
                t.setLat(lat);
                return t;
            }
        }
        System.err.println("Update Event: Event not found");
        return null;
    }

    public String toString(){
        return "User with id: "+id+" and name: "+name;
    }
}
