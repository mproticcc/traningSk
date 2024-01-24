package com.Notifications.dto;

import java.time.LocalDateTime;
import java.util.Date;

public class NotifikacijaFilterDto  {

    private String tip;
    private LocalDateTime pocetakPerioda;
    private LocalDateTime krajPerioda;

    public String getTip() {
        return tip;
    }

    public void setTip(String tip) {
        this.tip = tip;
    }


    public LocalDateTime getPocetakPerioda() {
        return pocetakPerioda;
    }

    public void setPocetakPerioda(LocalDateTime pocetakPerioda) {
        this.pocetakPerioda = pocetakPerioda;
    }

    public LocalDateTime getKrajPerioda() {
        return krajPerioda;
    }

    public void setKrajPerioda(LocalDateTime krajPerioda) {
        this.krajPerioda = krajPerioda;
    }
}
