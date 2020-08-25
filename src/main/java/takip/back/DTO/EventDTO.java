package takip.back.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import takip.back.entity.ExtraQuestion;
import takip.back.entity.Users;

import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
public class EventDTO {
        public Long id;

        @Size(max=255, message = "title cannot be bigger than 255 characters")
        public String title;
        @Size(max=1500, message = "description cannor be bigger than 1500 characters")
        public String description;
        public LocalDateTime startDate;
        public LocalDateTime endDate;
        public LocalDateTime time;
        public Users organizer;
        public Set<ExtraQuestion> extraQuestions = new HashSet<ExtraQuestion>();
        @Min(value = 0, message = "quota must be bigger than 0")
        public Long quota;
        public Long lon;
        public Long lat;

        public EventDTO(
                @JsonProperty("id") Long id,
                @JsonProperty("title") String title,
                @JsonProperty("description") String description,
                @JsonProperty("startDate") LocalDateTime startDate,
                @JsonProperty("endDate") LocalDateTime endDate,
                @JsonProperty("time") LocalDateTime time,
                @JsonProperty("organizer") Users organizer,
                @JsonProperty("extraQuestions") Set<ExtraQuestion> extraQuestions,
                @JsonProperty("quota") Long quota,
                @JsonProperty("lon") Long lon,
                @JsonProperty("lat") Long lat
        ){
                this.id = id;
                this.title = title;
                this.description = description;
                this.startDate = startDate;
                this.endDate = endDate;
                this.time = time;
                this.organizer = organizer;
                this.extraQuestions = extraQuestions;
                this.quota = quota;
                this.lon = lon;
                this.lat = lat;
        }
}
