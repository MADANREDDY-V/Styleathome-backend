package com.styleathome.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TimeSlot {
    private Long id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private boolean isBooked;

    public TimeSlot(Long id, LocalDateTime startTime, LocalDateTime endTime, boolean isBooked) {
        this.id = id;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBooked = isBooked;
    }
}
