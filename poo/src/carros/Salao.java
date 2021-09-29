package carros;

import java.util.Random;

public class Salao {
	public static void main(String[] args) {
		System.out.println("__________________________________");
		Carro fusca = new Carro();
		fusca.ano = 1967;
		fusca.cor = "Azul";
		System.out.println("Carro: Fusca");
		System.out.println("Ano: " + fusca.ano);
		System.out.println("Cor: " + fusca.cor);
		fusca.ligar();
		fusca.acelerar();
		fusca.acelerar();
		fusca.desligar();
		String chassi = new String("0123456789ABDCEFGHIJKLMNOPQRSTUVXWYZ");
		Random gerador = new Random();
		System.out.print("* ");
		for (int i = 0; i < 16; i++) {
		char numeracao = (char) gerador.nextInt(chassi.length());
		System.out.print(chassi.charAt(numeracao));
		}
		System.out.println(" *");
	}
}
