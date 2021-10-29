package it.euris.academy.cinema_dgs.utils;

import it.euris.academy.cinema_dgs.data.enums.Generi;

import java.time.LocalDate;

public class Utilities {

    public static String fromLongToString(Long value){
        return value==null ? null : value.toString();
    }

    public static Long fromStringToLong(String value){
        return value==null ? null : Long.parseLong(value);
    }

    public static String fromIntegerToString(Integer value){
        return value==null ? null : value.toString();
    }

    public static Integer fromStringToInteger(String value){
        return value==null ? null : Integer.parseInt(value);
    }

    public static String fromDoubleToString(Double value){
        return value==null ? null : value.toString();
    }

    public static Double fromStringToDouble(String value){
        return value==null ? null : Double.parseDouble(value);
    }

    public static Boolean fromStringToBoolean(String value){
        return value==null ? null : Boolean.parseBoolean(value);
    }

    public static String fromBooleanToString(Boolean value){
        return value==null ? null : value.toString();
    }

    public static String fromGeneriToString(Generi value){
        return value==null ? null : value.name();
    }

    public static Generi fromStringToGeneri(String value){
        return value == null ? null : Generi.valueOf(value.toUpperCase());
    }

    public static String fromLocalDateToString(LocalDate value){
        return value == null ? null : value.toString();
    }

    public static LocalDate fromStringToLocalDate(String value){
        return value == null ? null : LocalDate.parse(value);
    }
}
