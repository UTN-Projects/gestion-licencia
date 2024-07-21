import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import utn.metodos_agiles.controller.LicenciaController;
import utn.metodos_agiles.db.DBManager;
import utn.metodos_agiles.model.entidades.*;

import java.io.File;
import java.sql.Date;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class GestionLicenciaTest {

    private final static String facturaPath = "./factura40121348.pdf";
    private final static String licenciaPath = "./licencia40121348.pdf";

    @Mock
    DBManager dbManager;

    @InjectMocks
    LicenciaController licenciaController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        File factura = new File(facturaPath);
        factura.delete();

        File licencia = new File(licenciaPath);
        licencia.delete();
    }

    @Test
    public void emitirLicenciaTest() {

        Titular titular = Titular.builder()
                .nombre("Sofia")
                .apellido("Martinez")
                .dni(40121348)
                .calle("Urquiza")
                .nroCasa(1234)
                .grupoSanguineo(GrupoSaguineo.A)
                .rh(RH.NEGATIVO)
                .fechaNacimiento(new Date(1995,10,10))
                .esDonante(true)
                .build();

        String observacion = "";

        Mockito.doNothing().when(dbManager).guardarLicencia(Mockito.any());

        licenciaController.emitirLicencia(titular, ClaseLicencia.A, observacion);

        File factura = new File(facturaPath);
        assertTrue(factura.exists());

        File licencia = new File(licenciaPath);
        assertTrue(licencia.exists());
    }

    @Test
    public void generarDuplicadoTest() {
        Mockito.doNothing().when(dbManager).guardarLicencia(Mockito.any());

        Titular titular = Titular.builder()
                .nombre("Sofia")
                .apellido("Martinez")
                .dni(40121348)
                .calle("Urquiza")
                .nroCasa(1234)
                .grupoSanguineo(GrupoSaguineo.A)
                .rh(RH.NEGATIVO)
                .fechaNacimiento(new Date(1995,10,10))
                .esDonante(true)
                .build();

        Licencia licencia = Licencia.builder()
                .titular(titular)
                .tipo(TipoLicencia.ORIGINAL)
                .clase(ClaseLicencia.A)
                .fechaVencimiento(Date.valueOf(LocalDate.now()))
                .fechaEmision(Date.valueOf(LocalDate.now()))
                .fechaNacTitular(Date.valueOf(LocalDate.now()))
                .grupoSangTitular(GrupoSaguineo.A)
                .build();

        licenciaController.copiarLicencia(licencia);

        File factura = new File(facturaPath);
        assertTrue(factura.exists());

        File licenciaFile = new File(licenciaPath);
        assertTrue(licenciaFile.exists());
    }

    @Test
    public void renovarLicenciaTest() {
        Titular titular = Titular.builder()
                .nombre("Sofia")
                .apellido("Martinez")
                .dni(40121348)
                .calle("Urquiza")
                .nroCasa(1234)
                .grupoSanguineo(GrupoSaguineo.A)
                .rh(RH.NEGATIVO)
                .fechaNacimiento(new Date(1995,10,10))
                .esDonante(true)
                .build();

        Mockito.doNothing().when(dbManager).guardarLicencia(Mockito.any());
        Mockito.when(dbManager.buscarPorDni(40121348)).thenReturn(titular);

        Licencia licencia = Licencia.builder()
                .titular(titular)
                .tipo(TipoLicencia.ORIGINAL)
                .clase(ClaseLicencia.A)
                .fechaVencimiento(Date.valueOf(LocalDate.now()))
                .fechaEmision(Date.valueOf(LocalDate.now()))
                .fechaNacTitular(Date.valueOf(LocalDate.now()))
                .grupoSangTitular(GrupoSaguineo.A)
                .build();

        String observacion = "";

        licenciaController.renovarLicencia(licencia, observacion);
        File factura = new File(facturaPath);
        assertTrue(factura.exists());

        File licenciaFile = new File(licenciaPath);
        assertTrue(licenciaFile.exists());
    }
}
