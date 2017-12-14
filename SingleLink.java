import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.IOException;
import java.lang.Object;
import java.util.Scanner;
import java.util.Random;

public class SingleLink{
	public static void main(String[] args) throws IOException{
		int k;
		int cont = 0;
		Random numger = new Random();
		ArrayList<Centroide> cent = new ArrayList<>();
		Mdistancia[][] m = null;
		Mdistancia[][] m2 = null;
    	String nome = "c2ds1-2sp.txt";
    	//FileWriter arq2 = new FileWriter("resultado.txt");
		//PrintWriter gravarArq = new PrintWriter(arq2);
    	try {
	      	FileReader arq = new FileReader(nome);
	 	    BufferedReader lerArq = new BufferedReader(arq);
	 	    
	 
		    String linha = lerArq.readLine(); // lê a primeira linha
		// a variável "linha" recebe o valor "null" quando o processo
		// de repetição atingir o final do arquivo texto
		    linha = lerArq.readLine();
		    while (linha != null) {
		    	String [] s = linha.split("\t");
		    	/*for(int i=0;i<3;i++){
		    		System.out.println(i+ " " +s[i]+" ");
		    	}*/
		    	cent.add(new Centroide(s[0], Double.parseDouble(s[1]), Double.parseDouble(s[2])));
		        linha = lerArq.readLine(); // lê da segunda até a última linha
		    }
	    	arq.close();
	    }catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	    System.out.println();
	    System.out.println("Defina K:");
	    Scanner ler = new Scanner(System.in);
	    k = ler.nextInt();
	    //cria matriz da quantidade de arquivos
	    m = new Mdistancia[cent.size()-cont][cent.size()-cont];
	    Double resultx;
	    Double resulty;
	   	Double resultado;
	    int aux = 0;
	    for(int i = 0;i<4;i++){
	    	for(int j = 0;j<4;j++){
	    		resultx = Math.pow((cent.get(j).getx() - cent.get(i).getx()),2);
	    		resulty = Math.pow((cent.get(j).gety() - cent.get(i).gety()),2);
    			resultado = Math.sqrt(resultx+resulty);
	    		m[i][j] = new Mdistancia(cent.get(i).getNome(), resultado);
	    	}
	    }
	    Double menor = -1.0;
	    int posi=0, posy=0;
	    //pega a menor distancia entre os objetos para criar um cluster
	    for(int i = 0;i<4-cont;i++){
	    	for(int j = 0;j<4-cont;j++){
	    		if(menor == -1.0 && m[i][j].getDist() != 0){
	    			menor = m[i][j].getDist();
	    			posi = i;
	    			posy = j;
	    		}else if(m[i][j].getDist() < menor && m[i][j].getDist() != 0){
	    			menor = m[i][j].getDist();
	    			posi = i;
	    			posy = j;
	    		}
	    	}
	    }
	    //loop para quando amtriz so for 2x2
	    while(4-cont > 2){
		    //matriz diminuida
		    cont++;
		    if(4-cont > 2){
			    m2 = new Mdistancia[4-cont][4-cont];
			    //passa a antiga matriz com o novo cluster para a nova
			    for(int i = 0;i<4-cont;i++){
			    	for(int j = 0;j<4-cont;j++){
			    		if((i != posi && i != posy ) || (j != posi && j != posy)){
			    			m2[i][j] = m[i][j];
			    		}
			    		if(i == 4-cont-1 && j != 4-cont-1){
			    			StringBuilder stringBuilder = new StringBuilder();
			    			stringBuilder.append(m[posi][posi].getNome());
			    			stringBuilder.append("-");
			    			stringBuilder.append(m[posy][posy].getNome());
			    			String nomenovo = stringBuilder.toString();
			    			Double novores = Math.min(m[posi][j].getDist(), m[posy][j].getDist());
			    			m2[i][j] = new Mdistancia(nomenovo, novores);
			    		}else if(j == 4-cont-1 && i != 4-cont-1){
			    			StringBuilder stringBuilder = new StringBuilder();
			    			stringBuilder.append(m[posi][posi].getNome());
			    			stringBuilder.append("-");
			    			stringBuilder.append(m[posy][posy].getNome());
			    			String nomenovo = stringBuilder.toString();
			    			Double novores = Math.min(m[i][posi].getDist(), m[i][posy].getDist());
			    			m2[i][j] = new Mdistancia(nomenovo, novores);
			    		}else if((i == 4-cont-1 && i == 4-cont-1 ) || (j == 4-cont-1 && j == 4-cont-1)){
			    			StringBuilder stringBuilder = new StringBuilder();
			    			stringBuilder.append(m[posi][posi].getNome());
			    			stringBuilder.append("-");
			    			stringBuilder.append(m[posy][posy].getNome());
			    			String nomenovo = stringBuilder.toString();
			    			m2[i][j] = new Mdistancia(nomenovo, 0.0);	
			    		}
			    	}
			    }
			    posi = 0;
			    posy = 0;
			    menor = -1.0;
			    for(int i = 0;i<4-cont;i++){
		    		for(int j = 0;j<4-cont;j++){
			    		if(menor == -1.0 && m2[i][j].getDist() != 0){
			    			menor = m2[i][j].getDist();
			    			posi = i;
			    			posy = j;
			    		}else if(m2[i][j].getDist() < menor && m2[i][j].getDist() != 0){
			    			menor = m2[i][j].getDist();
			    			posi = i;
			    			posy = j;
			    		}
		    		}
		    	}
		    }
	    	cont++;
	    	if(4-cont > 2){
		    	//diminui matriz
		    	m = new Mdistancia[4-cont][4-cont];
			    //passa a antiga matriz com o novo cluster para a nova
			    for(int i = 0;i<4-cont;i++){
			    	for(int j = 0;j<4-cont;j++){		    		
			    		if((i != posi && i != posy ) || (j != posi && j != posy)){
			    			m[i][j] = m2[i][j];
			    		}
			    		if(i == 4-cont-1 && j != 4-cont-1){
			    			StringBuilder stringBuilder = new StringBuilder();
			    			stringBuilder.append(m2[posi][posi].getNome());
			    			stringBuilder.append("-");
			    			stringBuilder.append(m2[posy][posy].getNome());
			    			String nomenovo = stringBuilder.toString();
			    			Double novores = Math.min(m2[posi][j].getDist(), m2[posy][j].getDist());
			    			m[i][j] = new Mdistancia(nomenovo, novores);
			    		}else if(j == 4-cont-1 && i != 4-cont-1){
			    			StringBuilder stringBuilder = new StringBuilder();
			    			stringBuilder.append(m2[posi][posi].getNome());
			    			stringBuilder.append("-");
			    			stringBuilder.append(m2[posy][posy].getNome());
			    			String nomenovo = stringBuilder.toString();
			    			Double novores = Math.min(m2[i][posi].getDist(), m2[i][posy].getDist());
			    			m[i][j] = new Mdistancia(nomenovo, novores);
			    		}else if((i == 4-cont-1 && i == 4-cont-1 ) || (j == 4-cont-1 && j == 4-cont-1)){
			    			StringBuilder stringBuilder = new StringBuilder();
			    			stringBuilder.append(m2[posi][posi].getNome());
			    			stringBuilder.append("-");
			    			stringBuilder.append(m2[posy][posy].getNome());
			    			String nomenovo = stringBuilder.toString();
			    			m[i][j] = new Mdistancia(nomenovo, 0.0);	
			    		}
			    	}
			    }
			    posi = 0;
			    posy = 0;
			    menor = -1.0;
			    for(int i = 0;i<4-cont;i++){
		    		for(int j = 0;j<4-cont;j++){
			    		if(menor == -1.0 && m[i][j].getDist() != 0){
			    			menor = m[i][j].getDist();
			    			posi = i;
			    			posy = j;
			    		}else if(m[i][j].getDist() < menor && m[i][j].getDist() != 0){
			    			menor = m[i][j].getDist();
			    			posi = i;
			    			posy = j;
			    		}
		    		}
		    	}
		    }
		}
	    //imprimi matriz
	    // for(int i = 0;i<4-cont;i++){
	    // 	for(int j = 0;j<4-cont;j++){
	    // 		System.out.print(m2[i][j].getDist()+" ");
	    // 	}
	    // 	System.out.println("");
	    // }
	}
}
