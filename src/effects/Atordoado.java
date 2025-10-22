package effects;

import personagens.Personagem;

public class Atordoado implements StatusEffect {
    private static final int DURACAO_INICIAL = 1;
    
    private int duracaoRestante;
    
    public Atordoado() {
        this.duracaoRestante = DURACAO_INICIAL;
    }
    
    @Override
    public String getNome() {
        return "Atordoado";
    }
    
    @Override
    public int getDuracaoRestante() {
        return duracaoRestante;
    }
    
    @Override
    public void aplicarEfeito(Personagem alvo) {
        if (estaAtivo()) {
            System.out.println("💫 " + alvo.getNome() + " está atordoado e não pode agir!");
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
