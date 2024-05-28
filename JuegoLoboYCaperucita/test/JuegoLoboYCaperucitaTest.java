import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class JuegoLoboYCaperucitaTest {
    private JuegoLoboYCaperucita juego;

    @Before
    public void setUp() {
        juego = new JuegoLoboYCaperucita();
        JuegoLoboYCaperucita.rioIzquierda = new ArrayList<>(Arrays.asList("Caperucita", "Lobo", "Uva"));
        JuegoLoboYCaperucita.rioDerecha = new ArrayList<>();
        JuegoLoboYCaperucita.juegoEnCurso = true;
    }

    @Test
    public void testMoverCaperucitaHaciaDerecha() {
        juego.cruzarRioHaciaDerecha("Caperucita");
        assertTrue("Caperucita debería estar en el río derecho", JuegoLoboYCaperucita.rioDerecha.contains("Caperucita"));
        assertFalse("El río izquierdo no debería contener a Caperucita", JuegoLoboYCaperucita.rioIzquierda.contains("Caperucita"));
    }

    @Test
    public void testMoverCaperucitaHaciaIzquierda() {
        JuegoLoboYCaperucita.rioDerecha.add("Caperucita");
        juego.cruzarRioHaciaIzquierda("Caperucita");
        assertTrue("Caperucita debería estar de vuelta en el río izquierdo", JuegoLoboYCaperucita.rioIzquierda.contains("Caperucita"));
        assertFalse("El río derecho no debería contener a Caperucita", JuegoLoboYCaperucita.rioDerecha.contains("Caperucita"));
    }

    @Test
    public void testMovimientoInvalido() {
        juego.cruzarRioHaciaDerecha("Caperucita");
        juego.cruzarRioHaciaDerecha("Caperucita"); // Intentar mover nuevamente a Caperucita
        int count = Collections.frequency(JuegoLoboYCaperucita.rioDerecha, "Caperucita");
        assertEquals("Caperucita solo debe estar una vez en el río derecho", 1, count);
    }

    @Test
    public void testVerificarDerrotaPorCombinacionPeligrosa() {
        JuegoLoboYCaperucita.rioIzquierda.remove("Uva"); // Dejar a Caperucita y al Lobo solos
        assertFalse("Debería indicar una jugada peligrosa", juego.verificarJugada(JuegoLoboYCaperucita.rioIzquierda));
    }

    @Test
    public void testGanarJuego() {
        JuegoLoboYCaperucita.rioDerecha.addAll(Arrays.asList("Caperucita", "Lobo", "Uva"));
        JuegoLoboYCaperucita.rioIzquierda.clear();
        juego.verificarVictoria();
        assertFalse("El juego debería terminar con una victoria", JuegoLoboYCaperucita.juegoEnCurso);
    }

}
