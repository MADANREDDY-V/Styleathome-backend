package com.styleathome.dto;

import com.styleathome.entity.BookingStatus;
import lombok.Data;

@Data
public class AdminBookingStatusRequest {
    private BookingStatus status;
}

