package challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Respuesta {

    String respuesta;
    boolean esCorrecta;

    public Respuesta(String respuesta, boolean esCorrecta) {
        this.respuesta = respuesta;
        this.esCorrecta = esCorrecta;
    }

}

class Pregunta {

    private final String pregunta;
    private final List<Respuesta> respuestas;

    private static final String letras = "abcde";

    public Pregunta(String pregunta, List<Respuesta> respuestas) {
        this.pregunta = pregunta;
        this.respuestas = respuestas;
    }

    public Pregunta(String pregunta) {
        this.pregunta = pregunta;
        this.respuestas = new ArrayList<>();
    }

    public void agregarRespuesta(Respuesta r) {
        this.respuestas.add(r);
    }

    public boolean respuestaCorrecta(char respuesta) {
        int indice = letras.indexOf(respuesta);
        if (indice == -1) {
            return false;
        }
        return this.respuestas.get(indice).esCorrecta;
    }

    public boolean preguntar(int numero) {
        System.out.println(numero + ". " + this.pregunta);
        int indice = 0;
        for (Respuesta r : this.respuestas) {
            System.out.printf("%c) %s\n", letras.charAt(indice), r.respuesta);
            indice++;
        }
        System.out.println(indice * 20);
        System.out.println("Elige: ");
        Scanner sc = new Scanner(System.in);
        char respuesta = sc.nextLine().charAt(0);
        return this.respuestaCorrecta(respuesta);
    }
}

class Cuestionario {

    private final List<Pregunta> preguntas;
    private int aciertos;

    public Cuestionario() {
        this.preguntas = new ArrayList<>();
    }

    public void agregarPregunta(Pregunta p) {
        this.preguntas.add(p);
    }

    public void preguntar() {
        int numero = 1;
        for (Pregunta p : this.preguntas) {
            boolean acierta = p.preguntar(numero);
            numero++;
            if (acierta) {
                System.out.println("Correcto");
                this.aciertos++;
            } else {
                System.out.println("Incorrecto");
                break;
            }
        }
    }

    public void imprimirResultados() {
        System.out.printf("Puntaje: %d ", this.aciertos * 10);
    }
}

public class Challenge {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cuestionario c = new Cuestionario();
        c.agregarPregunta(new Pregunta("1 + 1", List.of(
                new Respuesta("3", false),
                new Respuesta("0", false),
                new Respuesta("2", true),
                new Respuesta("1", false),
                new Respuesta("11", false)
        )
        )
        );
        c.agregarPregunta(new Pregunta("5 x 2", List.of(
                new Respuesta("7", false),
                new Respuesta("10", true),
                new Respuesta("52", false),
                new Respuesta("3", false),
                new Respuesta("25", false)
        )));
        c.agregarPregunta(new Pregunta("10 x 20", List.of(
                new Respuesta("1020", false),
                new Respuesta("200", true),
                new Respuesta("2000", false),
                new Respuesta("210", false),
                new Respuesta("120", false)
        )));

        c.agregarPregunta(new Pregunta("100 x 25", List.of(
                new Respuesta("1025", false),
                new Respuesta("2500", true),
                new Respuesta("2000", false),
                new Respuesta("2100", false),
                new Respuesta("1234", false)
        )));

        c.preguntar();
        c.imprimirResultados();
    }

}
