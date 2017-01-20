package model;

import model.Equipo;

import java.time.LocalDate;

public class Jugador {

    private String nombre;
    private LocalDate fechan;
    private int numCanastas;
    private int numRebotes;
    private int numAsistencias;
    private String posicion;
    private Equipo equipo;

    public Jugador() {
    }

    public Jugador(String nombre, LocalDate fechan, int numCanastas, int numRebotes, int numAsistencias, String posicion, Equipo equipo) {
        this.nombre = nombre;
        this.fechan = fechan;
        this.numCanastas = numCanastas;
        this.numRebotes = numRebotes;
        this.numAsistencias = numAsistencias;
        this.posicion = posicion;
        this.equipo = equipo;
    }

    public String getNombre() {return nombre;}

    public void setNombre(String nombre) {this.nombre = nombre;}

    public LocalDate getFechan() {return fechan;}

    public void setFechan(LocalDate fechan) {this.fechan = fechan;}

    public int getNumCanastas() {return numCanastas;}

    public void setNumCanastas(int numCanastas) {this.numCanastas = numCanastas;}

    public int getNumRebotes() {return numRebotes;}

    public void setNumRebotes(int numRebotes) {this.numRebotes = numRebotes;}

    public int getNumAsistencias() {return numAsistencias;}

    public void setNumAsistencias(int numAsistencias) {this.numAsistencias = numAsistencias;}

    public String getPosicion() {return posicion;}

    public void setPosicion(String posicion) {this.posicion = posicion;}

    public Equipo getEquipo() {return equipo;}

    public void setEquipo(Equipo equipo) {this.equipo = equipo;}

    @Override
    public String toString() {
        return "model.Jugador{" +
                "nombre='" + nombre + '\'' +
                ", fechan=" + fechan +
                ", numCanastas=" + numCanastas +
                ", numRebotes=" + numRebotes +
                ", numAsistencias=" + numAsistencias +
                ", posicion='" + posicion + '\'' +
                ", equipo=" + equipo +
                '}';
    }
}
