package takip.back.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

@Getter
@Builder
@NoArgsConstructor
public class UsersDTO {

    @Size(max=255, message = "name cannot be bigger than 255 characters")
    public String name;
    @Email
    public String email;
    public String TCKno;
    public String secretKey;

    public UsersDTO(
            @JsonProperty("name") String name,
            @JsonProperty("email")String email,
            @JsonProperty("TCKno")String TCKno,
            @JsonProperty("secretKey") String secretKey
    ){
        this.name = name;
        this.email = email;
        this.TCKno = TCKno;
        this.secretKey = secretKey;
    }

    @AssertTrue(message = "TC Kimlik no hatali")
    public boolean isTCKnoValid(){
        String tmp = this.TCKno;

        if (tmp.length() == 11) {
            int totalOdd = 0;

            int totalEven = 0;

            for (int i = 0; i < 9; i++) {
                int val = Integer.valueOf(tmp.substring(i, i + 1));

                if (i % 2 == 0) {
                    totalOdd += val;
                } else {
                    totalEven += val;
                }
            }

            int total = totalOdd + totalEven + Integer.valueOf(tmp.substring(9, 10));

            int lastDigit = total % 10;

            if (tmp.substring(10).equals(String.valueOf(lastDigit))) {
                int check = (totalOdd * 7 - totalEven) % 10;

                if (tmp.substring(9, 10).equals(String.valueOf(check))) {
                    return true;
                }
            }
        }
        return false;
    }
}
