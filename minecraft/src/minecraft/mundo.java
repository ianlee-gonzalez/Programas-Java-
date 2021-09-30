package minecraft;

public class mundo {

	public static void main(String[] args) {
		bloco pokemon =new bloco();
		pokemon.poder=100;
		pokemon.valor=60;
		System.out.println("Pokemon");
		System.out.println("Poder:"+pokemon.poder);
		System.out.println("valor"+pokemon.valor);;
		pokemon.craftar();
		pokemon.construir();
		pokemon.mineirar();
		
		
		bloco lixo =new bloco();
		lixo.poder=0;
		lixo.valor=0;
		System.out.println("LIXO");
		System.out.println("Poder"+lixo.poder);
		System.out.println("Valor"+lixo.valor);
		lixo.craftar();
		lixo.construir();
		lixo.mineirar();
		

	}

}
