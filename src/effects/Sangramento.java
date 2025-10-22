package effects;

import personagens.Personagem;

public class Sangramento implements StatusEffect {
    private static final int DANO_POR_TURNO = 5;
    private static final int DURACAO_INICIAL = 3;
    
    private int duracaoRestante;
    
    public Sangramento() {
        this.duracaoRestante = DURACAO_INICIAL;
    }
    
    @Override
    public String getNome() {
        return "Sangramento";
    }
    
    @Override
    public int getDuracaoRestante() {
        return duracaoRestante;
    }
    
    @Override
    public void aplicarEfeito(Personagem alvo) {
        if (estaAtivo()) {
            System.out.println("🩸 " + alvo.getNome() + " sofre " + DANO_POR_TURNO + " de dano por sangramento!");
            alvo.receberDanoDireto(DANO_POR_TURNO);
        }
    }
    
    @Override
    public void decrementarDuracao() {
        if (duracaoRestante > 0) {
            duracaoRestante--;
        }
    }
    
    @Override
    public boolean estaAtivo() {
        return duracaoRestante > 0;
    }
}
