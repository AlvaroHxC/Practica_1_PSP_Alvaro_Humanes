package fabrica_coches;

import java.io.IOException;
import java.nio.file.Path;

public class Coordinador {

    // Tareas de los tres trabajadores, pasos y pausas
    static String[] nombres = {"Montador_motor", "Montador_chasis", "Pintor"};
    static int[] pasos = {7, 4, 2};
    static int[] pausas = {550, 720, 1200};

    // Trabajadores como procesos independientes
    static Process lanzar(int i) throws IOException {

        String java = Path.of(System.getProperty("java.home"), "bin", "java").toString();

        ProcessBuilder constructor = new ProcessBuilder(
                java, "-cp",
                System.getProperty("java.class.path"),
                "fabrica_coches.Trabajador",
                nombres[i],
                String.valueOf(pasos[i]),
                String.valueOf(pausas[i])
        );


        Process proceso = constructor.inheritIO().start();

        // PID del trabajador
        System.out.println("Trabajador creado: " + nombres[i] + " | PID=" + proceso.pid());
        return proceso;
    }

    public static void main(String[] args) throws Exception {
        // Si no se indica modo, se utiliza el secuencial
        String modo = args.length == 0 ? "secuencial" : args[0];

        // Un modo desconocido muestra el uso y termina con codigo 2
        if (!modo.equals("secuencial") && !modo.equals("concurrente")) {
            System.err.println("Uso: java Coordinador secuencial|concurrente");
            System.exit(2);
        }

        System.out.println("PID del coordinador: " + ProcessHandle.current().pid());
        System.out.println("Procesadores logicos disponibles: "
                + Runtime.getRuntime().availableProcessors());
        System.out.println("Modo: " + modo);


        long inicio = System.nanoTime();

        if (modo.equals("secuencial")) {
            for (int i = 0; i < 3; i++) {

                Process proceso = lanzar(i);
                int codigo = proceso.waitFor();
                System.out.println(nombres[i] + " | Codigo de salida " + codigo);
            }
        } else {
            Process[] procesos = new Process[3];

            // Inicio de los tres
            for (int i = 0; i < 3; i++) {
                procesos[i] = lanzar(i);
            }


            for (int i = 0; i < 3; i++) {
                int codigo = procesos[i].waitFor();
                System.out.println(nombres[i] + " | Código de salida: " + codigo);
            }
        }


            double segundos = (System.nanoTime() - inicio) / 1e9;
        System.out.printf("Tiempo total: %.2f s%n", segundos);
    }
}
