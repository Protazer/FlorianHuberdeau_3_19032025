package com.chatop.chaTop.utils;


import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.time.LocalDate;

public class Helpers {

    @Bean
    public String formatDate(LocalDate date) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        return formatter.format(date);
    }
}
