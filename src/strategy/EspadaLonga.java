package strategy;

import effects.Sangramento;
import java.util.List;
import java.util.Random;
import personagens.Personagem;

public class EspadaLonga implements Arma {
    private static final int DANO_BASE = 15;
    private static final int CUSTO_MANA = 0;
    private static final int FORCA_MINIMA = 10;
    private static final double CHANCE_SANGRAMENTO = 0.30;
    
    private Random random = new Random();
    
    @Override
    public int getDanoBase() {
        return DANO_BASE;
    }
    
    @Override
    public String getNome() {
        return "Espada Longa";
    }
    
    @Override
    public int getCustoMana() {
        return CUSTO_MANA;
    }
    
    @Override
    public boolean podeUsar(Personagem personagem) {
        return personagem.getForca() >= FORCA_MINIMA;
    }
    
    @Override
    public void atacar(Personagem atacante, Personagem alvo) {
        if (!podeUsar(atacante)) {
            System.out.println(atacante.getNome() + " não tem força suficiente para usar " + getNome());
            return;
        }
        
        if (atacante.getManaAtual() < CUSTO_MANA) {
            System.out.println(atacante.getNome() + " não tem mana suficiente!");
            return;
        }
        
        atacante.gastarMana(CUSTO_MANA);
        
        int dano = DANO_BASE;
        System.out.println(atacante.getNome() + " ataca " + alvo.getNome() + " com " + getNome() + "!");
        
        // Chance de causar sangramento
        if (random.nextDouble() < CHANCE_SANGRAMENTO) {
            System.out.println("⚔️ CORTE PROFUNDO! " + alvo.getNome() + " está sangrando!");
            alvo.adicionarEfeito(new Sangramento());
        }
        
        alvo.receberDano(dano);
        System.out.println(alvo.getNome() + " recebeu " + dano + " de dano!");
    }
    
    @Override
    public void atacar(Personagem atacante, List<Personagem> alvos) {
        if (!alvos.isEmpty()) {
            atacar(atacante, alvos.get(0));
        }
    }
}
