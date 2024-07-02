package utn.metodos_agiles.util;

import java.time.LocalDate;
import java.sql.Date;

public class VigenciaCalculator {
    public static Date calcularVigencia(int edad, int nroLicTit) {
        LocalDate vig = LocalDate.now();
        int mas = 0;
        if(edad < 21) {
            if(nroLicTit == 1) {
                mas = 1;
            } else mas = 3;
        } else if(edad <= 46) {
            mas = 5;
        } else if(edad <= 60) {
            mas = 4;
        } else if(edad <= 70) {
            mas = 3;
        } else if(edad > 70) {
            mas = 1;
        }

        return Date.valueOf(vig.plusYears(mas));
    }
}
