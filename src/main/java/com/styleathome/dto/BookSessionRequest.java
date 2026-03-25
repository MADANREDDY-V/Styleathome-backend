package com.styleathome.dto;

import com.styleathome.entity.SessionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class BookSessionRequest {
    // A booking needs a service OR a related fashion product (e.g. styling consultation for a specific item)
    private Long serviceOfferingId;
    private Long relatedProductId;

    @NotNull
    private LocalDate sessionDate;

    @NotNull
    private LocalTime startTime;

    @NotNull
    private SessionType sessionType;
}
