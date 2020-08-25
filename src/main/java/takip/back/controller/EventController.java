package takip.back.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import takip.back.DTO.EventDTO;
import takip.back.DTO.UsersDTO;
import takip.back.entity.Applicant;
import takip.back.mail.Mailer;
import takip.back.mapper.EventMapper;
import takip.back.mapper.UsersMapper;
import takip.back.repository.EventRepository;
import takip.back.repository.UserRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:3000")
@Validated
@RequestMapping(value = "events")
public class EventController {

    final EventRepository er;
    final UserRepository ur;
    final EventMapper em;
    final UsersMapper um;
    final Mailer mailer;

    @PostMapping
    public ResponseEntity<String> postEvent(@RequestBody EventDTO e){
        var name  = e.organizer.name;
        
        var user = ur.findByName(name).get();
        if(!user.secretKey.equals(e.organizer.secretKey)) {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Kullanıcı adı ya da şifre hatalı");

        }
        else {

            var event = user.createEvent(
                    e.title,
                    e.description,
                    e.startDate,
                    e.endDate,
                    e.time,
                    e.extraQuestions,
                    e.quota,
                    e.lon,
                    e.lat
            );

            er.save(event);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Etkinlik yaratımı başarılı");

        }
    }

    @PostMapping("/{eventNo}")
    public ResponseEntity<String> applyEvent(@PathVariable Long eventNo, @RequestBody Applicant a){
        var event = er.findById(eventNo).get();
        if(event.getApplicants().size() >= event.getQuota())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Etkinlik kotası dolmuş");
        for(var appl: event.getApplicants()) {
            System.out.println(appl.getTCKno()+ " vs "+a.getTCKno());
            if (appl.TCKno.equals(a.getTCKno()))
                return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Bu kimlik numarası kullanılmış");

        }
        a.setApplicationDate(LocalDateTime.now());
        event.addApplicant(a);
        er.save(event);
        mailer.sendSimpleMessage(a.email);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Başvuru başarılı");
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody UsersDTO u){
        if(ur.existsByName(u.name))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Bu isim alınmış farklı bir isim deneyin");
        if(ur.existsByTCKno(u.TCKno))
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("Bu kimlik numarasi alınmış");

        ur.save(um.mapToEntity(u));
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("Kullanıcı yaratıldı");
    }

    @PatchMapping("/{eventNo}")
    public ResponseEntity<String> patchEvent(@PathVariable Long eventNo, @RequestBody EventDTO e){
        var name  = e.organizer.name;

        var user = ur.findByName(name).get();
        if(!user.secretKey.equals(e.organizer.secretKey)) {
            return ResponseEntity.status(HttpStatus.NETWORK_AUTHENTICATION_REQUIRED).body("Şifre hatalı");
        }
        else {
            var event = user.updateEvent(
                    eventNo,
                    e.title,
                    e.description,
                    e.startDate,
                    e.endDate,
                    e.time,
                    e.extraQuestions,
                    e.quota,
                    e.lon,
                    e.lat
            );
            er.save(event);
            return ResponseEntity.status(HttpStatus.ACCEPTED).body("Login Sucessfull");
        }
    }

    @PostMapping("/{eventNo}/applicants")
    public List<Applicant> getApplicants(@PathVariable Long eventNo, @RequestBody UsersDTO u){
        if(!ur.findByName(u.name).get().secretKey.equals(u.secretKey))
            return null;
        else{
            return new ArrayList<>(er.findById(eventNo).get().getApplicants());
        }
    }


    @DeleteMapping("/{eventNo}")
    public void deleteEvent(@PathVariable Long eventNo){
        System.out.println("Deleting "+eventNo);
        er.deleteById(eventNo);
    }

    @GetMapping("/name/{organizer}")
    public List<EventDTO> getOrganizerEvents(@PathVariable String organizer){
        return em.mapToDTO(er.getWithId(ur.findByName(organizer).get().id));
    }

    @GetMapping("/all")
    public List<EventDTO> getAll(){
        return  em.mapToDTO(er.findAll());
    }

    @GetMapping("/{eventNo}")
    public EventDTO getEvent(@PathVariable Long eventNo){
        System.out.println((er.findById(eventNo).get().getStartDate()));
        return em.mapToDTO(er.findById(eventNo).get());
    }

    @GetMapping
    public List<EventDTO> getEvents(){
        return em.mapToDTO(er.getAllAfterToday());
    }




}
