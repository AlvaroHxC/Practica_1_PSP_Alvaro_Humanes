package fabrica_coches;

public class Trabajador {
    public static void main(String[] args) {

        if (args.length != 3) {
            System.err.println("Uso: java Trabajador <nombre> <pasos> <pausaMs>");
            System.exit(2);
        }

        String nombre = args[0];
        int pasos = Integer.parseInt(args[1]);
        long pausa = Long.parseLong(args[2]);

        for (int paso = 1; paso <= pasos; paso++) {
            System.out.printf("%s | PID=%d | paso=%d%n",
                    nombre,
                    ProcessHandle.current().pid(),
                    paso
            );

            try {
                Thread.sleep(pausa);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println(nombre + " interrumpido");
                System.exit(1);
            }
        }
    }
}
