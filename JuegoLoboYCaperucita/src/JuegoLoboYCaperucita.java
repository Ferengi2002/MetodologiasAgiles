import java.util.ArrayList;import java.util.Arrays;import java.util.Scanner;
public class JuegoLoboYCaperucita {
    static ArrayList<String> rioIzquierda = new ArrayList<>(Arrays.asList("Caperucita", "Lobo", "Uva"));
    static ArrayList<String> rioDerecha = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);
    static boolean booleanRioIzquierda = true, booleanRioDerecha = true;
    static boolean juegoEnCurso = true;
    public static void main(String[] args) {
        System.out.println("Caperucita Lobo Uva" + "\\_Vikingo_/--------" + "");
        while (juegoEnCurso) {realizarJugada(); verificarVictoria();        }
    }
    private static void realizarJugada() {
        System.out.println("\n1. MOVER CAPERUCITA\n2. MOVER UVA\n3. MOVER LOBO\n0. SALIR");
        int opcion = scanner.nextInt();
        switch (opcion) {
            case 1, 2, 3 -> mover(opcion == 1 ? "Caperucita" : (opcion == 2 ? "Uva" : "Lobo"));
            case 0 -> System.exit(0);
            default -> System.out.println("Ingresa una opción válida");
        }
    }
    private static void mover(String actor) {
        if (desplegarSegundoMenu()) cruzarRioHaciaDerecha(actor);else cruzarRioHaciaIzquierda(actor);
    }
    private static boolean desplegarSegundoMenu() {
        System.out.println("Seleccionar 1 si quieres ---MOVER A LA DERECHA --- \nSeleccionar -1 si quieres ---MOVER A LA IZQUIERDA---\n");
        return scanner.nextInt() >= 1;
    }
    public static void cruzarRioHaciaIzquierda(String actor) {
        if (verificarEstado(rioDerecha, actor)) {rioIzquierda.add(rioDerecha.remove(rioDerecha.indexOf(actor)));
            barcoMovimiento(-1, actor.charAt(0));
        } else System.out.println("No se puede realizar movimiento");
        booleanRioDerecha = verificarJugada(rioDerecha);
    }
    public static void cruzarRioHaciaDerecha(String actor) {
        if (rioIzquierda.contains(actor)) { // Verificar que el actor esté en la orilla izquierda
            rioDerecha.add(rioIzquierda.remove(rioIzquierda.indexOf(actor)));
            barcoMovimiento(1, actor.charAt(0));
        } else {System.out.println("El actor ya está en la orilla derecha o no existe en la izquierda.");}
        booleanRioIzquierda = verificarJugada(rioIzquierda);
    }

    public static boolean verificarEstado(ArrayList<String> actores, String actor) {return actores.contains(actor);}
    public static boolean verificarJugada(ArrayList<String> actores) {
        if (actores.size() == 2) {
            if ((actores.contains("Caperucita") && actores.contains("Uva")) || (actores.contains("Caperucita") && actores.contains("Lobo"))) {
                System.out.println("Algo malo ha sucedido, has perdido!");return false;}
        }return true;
    }

    public static void verificarVictoria() {
        if (rioDerecha.size() == 3) {System.out.println("¡Felicidades! Todos han cruzado el río con éxito. Has ganado.");
            juegoEnCurso = false;
        }
    }
    public static void barcoMovimiento(int desplazamiento, char actor) {
        ArrayList<String> animacionHaciaDerecha = new ArrayList<>(Arrays.asList("\\", "Vikingo", String.valueOf(actor), "/", "_", "_", "_", "_", "_", "_", "_", "_"));
        ArrayList<String> animacionHaciaIzquierda = new ArrayList<>(Arrays.asList("_", "_", "_", "_", "_", "_", "_", "_", "\\", "Vikingo", String.valueOf(actor), "/"));
        StringBuilder rioIzquierda = new StringBuilder(), animacion = new StringBuilder(), rioDerecha = new StringBuilder();
        JuegoLoboYCaperucita.rioIzquierda.forEach(e -> rioIzquierda.append(e).append(" "));
        JuegoLoboYCaperucita.rioDerecha.forEach(e -> rioDerecha.append(e).append(" "));
        pausa();
        ArrayList<String> animacionActual = (desplazamiento == 1) ? animacionHaciaDerecha : animacionHaciaIzquierda;
        for (int i = 0; i < 9; i++) {
            animacionActual.forEach(e -> animacion.append(e).append(" "));
            if (desplazamiento == 1) {animacionHaciaDerecha.remove(animacionHaciaDerecha.size() - 1);
                animacionHaciaDerecha.add(0, "_");
            } else {animacionHaciaIzquierda.remove(0);
                animacionHaciaIzquierda.add("_");
            }System.out.print(rioIzquierda + " " + animacion + " " + rioDerecha);
            animacion.setLength(0);
            pausa();
            System.out.print("\r");
        }System.out.println();
    }
    private static void pausa() { try {Thread.sleep(250); } catch (InterruptedException e) { e.printStackTrace();}}
}
