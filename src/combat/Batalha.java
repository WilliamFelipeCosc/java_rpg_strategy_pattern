package combat;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import personagens.Personagem;

public class Batalha {
    private List<Personagem> time1;
    private List<Personagem> time2;
    private int turnoAtual;
    private Scanner scanner;
    
    public Batalha(List<Personagem> time1, List<Personagem> time2) {
        this.time1 = time1;
        this.time2 = time2;
        this.turnoAtual = 1;
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciar() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     ⚔️  BATALHA INICIADA! ⚔️          ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        mostrarTimes();
        
        while (!batalhaFinalizada()) {
            System.out.println("\n" + "═".repeat(50));
            System.out.println("🎯 TURNO " + turnoAtual);
            System.out.println("═".repeat(50));
            
            // Turno do Time 1
            processarTurnoTime(time1, time2, "Time 1");
            
            if (batalhaFinalizada()) break;
            
            // Turno do Time 2
            processarTurnoTime(time2, time1, "Time 2");
            
            turnoAtual++;
        }
        
        anunciarVencedor();
    }
    
    private void processarTurnoTime(List<Personagem> timeAtacante, List<Personagem> timeAlvo, String nomeTime) {
        System.out.println("\n┌─ " + nomeTime + " ─────────────────────┐");
        
        for (Personagem atacante : timeAtacante) {
            if (!atacante.estaVivo()) continue;
            
            System.out.println("\n➤ Vez de " + atacante.getNome());
            
            // Aplica habilidade passiva
            atacante.habilidadePassiva();
            
            // Processa efeitos de status
            atacante.processarEfeitos();
            
            if (!atacante.estaVivo()) {
                System.out.println("💀 " + atacante.getNome() + " foi derrotado!");
                continue;
            }
            
            // Se não estiver atordoado, pode agir
            if (!atacante.estaAtordoado()) {
                // Escolher ação
                exibirMenu(atacante, timeAlvo);
            }
        }
        
        System.out.println("└" + "─".repeat(30) + "┘");
    }
    
    private void exibirMenu(Personagem atacante, List<Personagem> inimigos) {
        List<Personagem> inimigosVivos = getPersonagensVivos(inimigos);
        
        if (inimigosVivos.isEmpty()) return;
        
        System.out.println("\n┌─ Ações ─────────────────────┐");
        System.out.println("│ 1. Atacar                   │");
        System.out.println("│ 2. Trocar Arma              │");
        System.out.println("│ 3. Ver Status               │");
        System.out.println("│ 4. Passar Turno             │");
        System.out.println("└─────────────────────────────┘");
        System.out.print("Escolha uma ação: ");
        
        int escolha = lerOpcao(1, 4);
        
        switch (escolha) {
            case 1:
                executarAtaque(atacante, inimigosVivos);
                break;
            case 2:
                // Trocar arma (simplificado - não implementado nesta versão)
                System.out.println("Funcionalidade de trocar arma ainda não implementada.");
                break;
            case 3:
                atacante.mostrarStatus();
                exibirMenu(atacante, inimigos); // Volta ao menu
                break;
            case 4:
                System.out.println(atacante.getNome() + " passou o turno.");
                break;
        }
    }
    
    private void executarAtaque(Personagem atacante, List<Personagem> inimigos) {
        if (atacante.getArmaAtual() == null) {
            System.out.println(atacante.getNome() + " não tem arma equipada!");
            return;
        }
        
        System.out.println("\n┌─ Escolha o Alvo ────────────┐");
        for (int i = 0; i < inimigos.size(); i++) {
            Personagem inimigo = inimigos.get(i);
            System.out.printf("│ %d. %s (HP: %d/%d)%n", 
                (i + 1), inimigo.getNome(), inimigo.getVidaAtual(), inimigo.getVidaMaxima());
        }
        System.out.println("│ " + (inimigos.size() + 1) + ". Ataque em Área (se disponível)");
        System.out.println("└─────────────────────────────┘");
        System.out.print("Escolha o alvo: ");
        
        int escolha = lerOpcao(1, inimigos.size() + 1);
        
        if (escolha == inimigos.size() + 1) {
            // Ataque em área
            atacante.atacarArea(inimigos);
        } else {
            // Ataque único
            Personagem alvo = inimigos.get(escolha - 1);
            atacante.atacar(alvo);
        }
    }
    
    private int lerOpcao(int min, int max) {
        while (true) {
            try {
                int opcao = Integer.parseInt(scanner.nextLine().trim());
                if (opcao >= min && opcao <= max) {
                    return opcao;
                }
                System.out.print("Opção inválida. Escolha entre " + min + " e " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Entrada inválida. Digite um número: ");
            }
        }
    }
    
    private List<Personagem> getPersonagensVivos(List<Personagem> time) {
        List<Personagem> vivos = new ArrayList<>();
        for (Personagem p : time) {
            if (p.estaVivo()) {
                vivos.add(p);
            }
        }
        return vivos;
    }
    
    private boolean batalhaFinalizada() {
        boolean time1Vivo = false;
        boolean time2Vivo = false;
        
        for (Personagem p : time1) {
            if (p.estaVivo()) {
                time1Vivo = true;
                break;
            }
        }
        
        for (Personagem p : time2) {
            if (p.estaVivo()) {
                time2Vivo = true;
                break;
            }
        }
        
        return !time1Vivo || !time2Vivo;
    }
    
    private void anunciarVencedor() {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("🏆 FIM DA BATALHA! 🏆");
        System.out.println("═".repeat(50));
        
        boolean time1Venceu = false;
        for (Personagem p : time1) {
            if (p.estaVivo()) {
                time1Venceu = true;
                break;
            }
        }
        
        if (time1Venceu) {
            System.out.println("\n✨ TIME 1 VENCEU! ✨");
            System.out.println("\nSobreviventes:");
            for (Personagem p : time1) {
                if (p.estaVivo()) {
                    System.out.println("  • " + p.getNome() + " - HP: " + p.getVidaAtual() + "/" + p.getVidaMaxima());
                }
            }
        } else {
            System.out.println("\n✨ TIME 2 VENCEU! ✨");
            System.out.println("\nSobreviventes:");
            for (Personagem p : time2) {
                if (p.estaVivo()) {
                    System.out.println("  • " + p.getNome() + " - HP: " + p.getVidaAtual() + "/" + p.getVidaMaxima());
                }
            }
        }
        
        System.out.println("\n" + "═".repeat(50));
    }
    
    private void mostrarTimes() {
        System.out.println("\n⚔️  TIME 1:");
        for (Personagem p : time1) {
            System.out.println("  • " + p.getNome() + " (" + p.getClasse() + ")");
        }
        
        System.out.println("\n🛡️  TIME 2:");
        for (Personagem p : time2) {
            System.out.println("  • " + p.getNome() + " (" + p.getClasse() + ")");
        }
        System.out.println();
    }
}
