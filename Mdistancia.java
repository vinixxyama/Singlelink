import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Mdistancia{
	// public Mdistancia(String nome, double distanciaE, ArrayList<Mdistancia> vetdist){
	// 	this.nome = nome;
	// 	this.distanciaE = distanciaE;
	// 	this.vetdist = vetdist;
	// }
	public Mdistancia(String nome, double distanciaE){
		this.nome = nome;
		this.distanciaE = distanciaE;
	}
	public double getDist(){
		return distanciaE;
	}

	public String getNome(){
		return nome;
	}

	private String nome;
	private double distanciaE;
	//private ArrayList<Mdistancia> vetdist;
}