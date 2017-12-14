import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Centroide{
	private String nome;
	private Double x;
	private Double y;

	public Centroide(String nome, Double x, Double y){
		this.nome = nome;
		this.x = x;
		this.y = y;
	}
	public Double getx(){
		return x;
	}
	public Double gety(){
		return y;
	}
	public String getNome(){
		return this.nome;
	}
}