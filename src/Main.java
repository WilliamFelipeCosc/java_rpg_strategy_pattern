import combat.Batalha;
import java.util.ArrayList;
import java.util.List;
import personagens.*;
import strategy.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════╗");
        System.out.println("║   🏰 RPG MEDIEVAL - SISTEMA DE COMBATE 🏰    ║");
        System.out.println("║        Padrão Strategy Implementation         ║");
        System.out.println("╚═══════════════════════════════════════════════╝\n");
        
        // Criar personagens do Time 1
        Guerreiro guerreiro = new Guerreiro("Thorin");
        Arqueiro arqueiro = new Arqueiro("Legolas");
        Mago mago = new Mago("Gandalf");
        
        // Criar personagens do Time 2
        Guerreiro guerreiro2 = new Guerreiro("Conan");
        Arqueiro arqueiro2 = new Arqueiro("Robin");
        
        // Criar armas
        EspadaLonga espada = new EspadaLonga();
        ArcoElfico arco = new ArcoElfico();
        CajadoArcano cajado = new CajadoArcano();
        MachadoDeGuerra machado = new MachadoDeGuerra();
        AdagaSombria adaga = new AdagaSombria();
        
        // Equipar armas no Time 1
        System.out.println("⚔️ EQUIPANDO TIME 1...\n");
        guerreiro.equiparArma(machado);
        arqueiro.equiparArma(arco);
        mago.equiparArma(cajado);
        
        // Equipar armas no Time 2
        System.out.println("\n🛡️ EQUIPANDO TIME 2...\n");
        guerreiro2.equiparArma(espada);
        arqueiro2.equiparArma(adaga);
        
        // Mostrar status inicial
        System.out.println("\n" + "═".repeat(50));
        System.out.println("STATUS INICIAL DOS PERSONAGENS");
        System.out.println("═".repeat(50));
        
        guerreiro.mostrarStatus();
        arqueiro.mostrarStatus();
        mago.mostrarStatus();
        guerreiro2.mostrarStatus();
        arqueiro2.mostrarStatus();
        
        // Criar times
        List<Personagem> time1 = new ArrayList<>();
        time1.add(guerreiro);
        time1.add(arqueiro);
        time1.add(mago);
        
        List<Personagem> time2 = new ArrayList<>();
        time2.add(guerreiro2);
        time2.add(arqueiro2);
        
        // Iniciar batalha
        Batalha batalha = new Batalha(time1, time2);
        batalha.iniciar();
        
        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║         Obrigado por jogar! 🎮               ║");
        System.out.println("╚═══════════════════════════════════════════════╝");
    }
}
