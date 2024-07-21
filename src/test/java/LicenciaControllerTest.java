import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import utn.metodos_agiles.controller.LicenciaController;
import utn.metodos_agiles.model.entidades.ClaseLicencia;
import utn.metodos_agiles.model.entidades.Licencia;
import utn.metodos_agiles.model.entidades.Titular;

public class LicenciaControllerTest {
    @Test

    public void testCalcularVigencia() {
        Titular titular = new Titular();
        titular.setFechaNacimiento(Date.valueOf(LocalDate.of(2004, 1, 1)));
        titular.setDni(12312312);
        Date resultado = LicenciaController.getInstance().calcularVigencia(titular);

        LocalDate esperado = LocalDate.now();
        //la vigencia para un menor de 21 anios para su primera licencia es de un anio
        esperado = esperado.plusYears(1);

        Assertions.assertEquals(esperado, resultado.toLocalDate());
    }

    @Test
    public void testCalcularCostoLicencia() {
        Licencia licencia = new Licencia();
        licencia.setFechaVencimiento(Date.valueOf(LocalDate.now().plusYears(5)));
        licencia.setFechaEmision(Date.valueOf(LocalDate.now()));
        licencia.setClase(ClaseLicencia.A);
        //es el costo de la emision de una licencia clase A por 5 años
        int esperado = 48;

        int resultado = LicenciaController.getInstance().calcularCostoLicencia(licencia);

        Assertions.assertEquals(esperado, resultado);
    }
}
