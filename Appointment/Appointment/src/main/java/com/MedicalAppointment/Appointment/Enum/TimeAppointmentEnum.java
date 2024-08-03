package com.MedicalAppointment.Appointment.Enum;

public enum TimeAppointmentEnum {
    TIME_08_00("08:00"),
    TIME_09_00("09:00"),
    TIME_10_00("10:00"),
    TIME_11_00("11:00"),
    TIME_13_00("13:00"),
    TIME_14_00("14:00"),
    TIME_15_00("15:00"),
    TIME_16_00("16:00"),
    TIME_17_00("17:00");

    private String time;

    TimeAppointmentEnum(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }
    }
