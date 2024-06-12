package utn.metodos_agiles.util;

import java.util.Date;

public class VigenciaCalculator {
    private static Date calcularVigencia(int edad, int nroLicTit) {
        Date vig = new Date();
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
        vig.setYear(vig.getYear() + mas);
        return vig;
    }
}
