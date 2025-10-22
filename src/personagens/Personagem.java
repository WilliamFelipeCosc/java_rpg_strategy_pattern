package personagens;

import effects.Atordoado;
import effects.StatusEffect;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import strategy.Arma;

public abstract class Personagem {
    protected String nome;
    protected int forca;
    protected int destreza;
    protected int inteligencia;
    protected int vidaMaxima;
    protected int vidaAtual;
    protected int manaMaxima;
    protected int manaAtual;
    protected Arma armaAtual;
    protected List<StatusEffect> efeitosAtivos;
    
    public Personagem(String nome, int forca, int destreza, int inteligencia, int vida, int mana) {
        this.nome = nome;
        this.forca = forca;
        this.destreza = destreza;
        this.inteligencia = inteligencia;
        this.vidaMaxima = vida;
        this.vidaAtual = vida;
        this.manaMaxima = mana;
        this.manaAtual = mana;
        this.efeitosAtivos = new ArrayList<>();
    }
    
    public abstract String getClasse();
    public abstract double getReducaoDano();
    public abstract double getChanceEsquiva();
    public abstract void habilidadePassiva();
    
    public void equiparArma(Arma arma) {
        if (arma.podeUsar(this)) {
            this.armaAtual = arma;
            System.out.println(nome + " equipou " + arma.getNome());
        } else {
            System.out.println(nome + " não pode usar " + arma.getNome() + "!");
        }
    }
    
    public void atacar(Personagem alvo) {
        if (armaAtual == null) {
            System.out.println(nome + " não tem arma equipada!");
            return;
        }
        
        if (estaAtordoado()) {
            System.out.println(nome + " está atordoado e não pode atacar!");
            return;
        }
        
        armaAtual.atacar(this, alvo);
    }
    
    public void atacarArea(List<Personagem> alvos) {
        if (armaAtual == null) {
            System.out.println(nome + " não tem arma equipada!");
            return;
        }
        
        if (estaAtordoado()) {
            System.out.println(nome + " está atordoado e não pode atacar!");
            return;
        }
        
        armaAtual.atacar(this, alvos);
    }
    
    public void receberDano(int dano) {
        // Aplica redução de dano
        int danoReduzido = (int) (dano * (1 - getReducaoDano()));
        
        // Verifica esquiva
        if (Math.random() < getChanceEsquiva()) {
            System.out.println("⚡ " + nome + " esquivou do ataque!");
            return;
        }
        
        receberDanoDireto(danoReduzido);
    }
    
    public void receberDanoDireto(int dano) {
        vidaAtual -= dano;
        if (vidaAtual < 0) {
            vidaAtual = 0;
        }
    }
    
    public void adicionarEfeito(StatusEffect efeito) {
        efeitosAtivos.add(efeito);
    }
    
    public void processarEfeitos() {
        Iterator<StatusEffect> iterator = efeitosAtivos.iterator();
        while (iterator.hasNext()) {
            StatusEffect efeito = iterator.next();
            if (efeito.estaAtivo()) {
                efeito.aplicarEfeito(this);
                efeito.decrementarDuracao();
            } else {
                System.out.println("✓ " + nome + " não sofre mais de " + efeito.getNome());
                iterator.remove();
            }
        }
    }
    
    public boolean estaAtordoado() {
        for (StatusEffect efeito : efeitosAtivos) {
            if (efeito instanceof Atordoado && efeito.estaAtivo()) {
                return true;
            }
        }
        return false;
    }
    
    public void gastarMana(int custo) {
        manaAtual -= custo;
        if (manaAtual < 0) {
            manaAtual = 0;
        }
    }
    
    public void recuperarMana(int quantidade) {
        manaAtual += quantidade;
        if (manaAtual > manaMaxima) {
            manaAtual = manaMaxima;
        }
    }
    
    public boolean estaVivo() {
        return vidaAtual > 0;
    }
    
    public void mostrarStatus() {
        System.out.println("\n━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("👤 " + nome + " (" + getClasse() + ")");
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
        System.out.println("❤️  Vida: " + vidaAtual + "/" + vidaMaxima);
        System.out.println("✨ Mana: " + manaAtual + "/" + manaMaxima);
        System.out.println("⚔️  Arma: " + (armaAtual != null ? armaAtual.getNome() : "Nenhuma"));
        System.out.println("💪 Força: " + forca + " | Destreza: " + destreza + " | Inteligência: " + inteligencia);
        
        if (!efeitosAtivos.isEmpty()) {
            System.out.println("\n🌀 Efeitos Ativos:");
            for (StatusEffect efeito : efeitosAtivos) {
                if (efeito.estaAtivo()) {
                    System.out.println("   • " + efeito.getNome() + " (" + efeito.getDuracaoRestante() + " turnos)");
                }
            }
        }
        System.out.println("━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n");
    }
    
    // Getters
    public String getNome() { return nome; }
    public int getForca() { return forca; }
    public int getDestreza() { return destreza; }
    public int getInteligencia() { return inteligencia; }
    public int getVidaAtual() { return vidaAtual; }
    public int getVidaMaxima() { return vidaMaxima; }
    public int getManaAtual() { return manaAtual; }
    public int getManaMaxima() { return manaMaxima; }
    public Arma getArmaAtual() { return armaAtual; }
}
