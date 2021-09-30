package inventario;
import minecraft.bloco;
public class Enxada extends bloco{
	public boolean conquista;
	public void arar() {
		System.out.println("Terra arada ");
		
	}

	public void mineirar() {
		System.out.println("dano atribuido");
	}

	public static void main(String[] args) {
		Enxada  enxada = new Enxada();
		enxada.conquista = false;
		System.out.println("Item Enxada");
		enxada.arar();
		if (enxada.conquista == true) {
			System.out.println("conquista obtida");
			System.out.println("----------------------------------------");
		} else {
			enxada.mineirar();
			
		}
	
		
	}

}
