package hellojpa;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
@Getter @Setter
public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period() { // 기본 생성자 필수
    }

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}