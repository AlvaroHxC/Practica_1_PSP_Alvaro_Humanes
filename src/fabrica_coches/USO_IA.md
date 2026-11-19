## Uso de Inteligencia Artificial:

Durante el desarrollo he usado Claude como herramienta de consulta, principalmente en lo que se refiere a la ejecución secuencial y concurrente.

La he utilizado como apoyo para comprender conceptos que aún no tengo asimilados, buscar soluciones referentes a algunos aspectos del código. Las pruebas se han realizado en IntelliJ.

### 1. Consultas realizadas

#### Diferencias entre ejecución secuencial y concurrente:

Prompt: ¿Cómo se pueden implementar varios modos de ejecución en Java(secuencial y concurrente) para comparar los tiempos de finalización de varias tareas?

- Información recibida: Utilización de **waitFor()** y diferencias entre iniciar procesos uno a continuación de otro o iniciarlos antes de esperar su finalización.
- Uso en el proyecto: implementación de los **modos secuencial y concurrente** en la clase **Coordinador**.


#### Simulación de tiempos de trabajo

Prompt: ¿Cómo calculo las pausas entre las tareas sin mantener ocupado el procesador?

- Información recibida: método **Thread.sleep()**, que suspende temporalmente la ejecución del hilo durante un periodo determinado.
- Uso en el proyecto: simulación de los tiempos de trabajo de cada trabajador mediante pausas.

#### Medición del tiempo de ejecución

Prompt: ¿Cómo puedo medir el tiempo que tarda un programa en completar varias tareas?

- Información utilizada: método **System.nanoTime()** para obtener medidas de tiempo y calcular la duración de una ejecución.
- Aplicación en el proyecto: cálculo y visualización del tiempo total empleado por los tres trabajadores.

#### Error ClassNotFuondException: Trabajador
Prompt: ¿A que se debe este error? Error: Could not find or load main class Trabajador Caused by: java.lang.ClassNotFoundException: Trabajador (a continuación pegué el código de la clase Trabajador)
- Información recibida: Tus archivos están dentro de un paquete. Mira la primera línea de Coordinador.java y Trabajador.java: si empieza por package algo el nombre completo de la clase es algo.Trabajador, y el coordinador la está buscando sin el prefijo.
- Aplicación en el proyecto: Seguí su indcación al respecto de que, al usar un paquete, hay que escribir el nombre completo de la clase hija en el ProcessBuilder.


### 2. Comprobación de la validez de la información

La validez de la información se ha comprobado compilando y ejecutando el programa, observando los resultados en consola y verificando el comportamiento de los procesos.


**Prueba 1: Secuencial**

- Se ejecuta el programa indicando el modo secuencial.
- Se comprueba que los tres trabajadores se inician uno después de otro.
- Verificar que un trabajador no comienza hasta que termina el anterior.
- Comprobar que se muestran los PID y los códigos de salida.

**Prueba 2: Concurrente**

- Ejecutar el programa indicando el modo concurrente.
- Se comprueba que se crean los tres procesos antes de esperar su finalización.
- Se observa que las salidas de los trabajadores pueden aparecer intercaladas.
- Por último, se compara el tiempo total con el obtenido en la ejecución secuencial.

**Prueba 3: Comparación de tiempos**

- Se ejecuta el programa en ambos modos varias veces.
- Comparar los tiempos obtenidos.
- Comprobar que en la ejecución concurrente el tiempo total normalmente es inferior al de la ejecución secuencial.



Además, he usado la IA para organizar y estructurar el código y comprender el orden de ejecución de los procesos.
