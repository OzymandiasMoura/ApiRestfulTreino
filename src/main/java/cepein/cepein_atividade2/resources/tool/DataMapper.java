package cepein.cepein_atividade2.resources.tool;

import java.time.LocalDate;

import static java.lang.Integer.parseInt;

public class DataMapper
{
    public static LocalDate stringToDate(String dataString)
    {

        int day = parseInt(dataString.substring(0, 2));
        int month = parseInt(dataString.substring(3, 5));
        int year = parseInt(dataString.substring(6));
        LocalDate data = LocalDate.of(year, month, day);

        return data;
    }

    public String dateToString(LocalDate data)
    {
        return data.toString();
    }
}
