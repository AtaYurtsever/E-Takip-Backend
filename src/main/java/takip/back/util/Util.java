package takip.back.util;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import takip.back.repository.EventRepository;
import takip.back.repository.UserRepository;

@Component
@RequiredArgsConstructor
public class Util {
    final EventRepository er;
    final UserRepository  ur;

    public void populateDatabase(){
//        var events = new ArrayList<Event>();
//        var u = new Users(null,"nick","a@b.com","11101","",new HashSet<>());
//        var u2 = new Users(null,"ata","c@db.com","22201","333",new HashSet<>());
//        var people = new HashSet<Users>();
//        people.add(u);
//        people.add(u2);
//
//        var e1 = u.createEvent("aaa","bbb", LocalDateTime.parse("2008-07-11T00:00"), LocalDateTime.parse("2008-07-11T00:00"),null, new HashSet<>(), 10L);
//        var e2 = u2.createEvent("ccc","ddd", LocalDateTime.parse("2020-08-11T00:00"), LocalDateTime.parse("2020-09-11T00:00"),null, new HashSet<>(),10L);
//
//
//        events.add(e1);
//        events.add(e2);


//        ur.saveAll(people);
//        er.saveAll(events);
    }
}
