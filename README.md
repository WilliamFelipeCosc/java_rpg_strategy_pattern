# 🏰 RPG Medieval - Sistema de Combate com Padrão Strategy

## 📋 Descrição do Projeto

Este projeto implementa um sistema de combate para um RPG medieval utilizando o **Padrão de Projeto Strategy**. O sistema permite que diferentes classes de personagens equipem e utilizem várias armas, cada uma com efeitos especiais únicos, demonstrando flexibilidade e extensibilidade do código.

## 🎯 Padrão Strategy

O padrão Strategy é um padrão comportamental que define uma família de algoritmos, encapsula cada um deles e os torna intercambiáveis. Neste projeto:

- **Contexto**: A classe `Personagem` que pode equipar diferentes armas
- **Strategy (Interface)**: `Arma` - define o contrato para todas as armas
- **Concrete Strategies**: `EspadaLonga`, `ArcoElfico`, `CajadoArcano`, `MachadoDeGuerra`, `AdagaSombria`

### Vantagens do Padrão Strategy neste projeto:

✅ **Extensibilidade**: Novas armas podem ser adicionadas sem modificar código existente  
✅ **Flexibilidade**: Personagens podem trocar armas durante a batalha  
✅ **Manutenibilidade**: Cada arma encapsula sua própria lógica de ataque  
✅ **Princípio Open/Closed**: Aberto para extensão, fechado para modificação  

## 📁 Estrutura do Projeto

```
src/
├── Main.java                      # Ponto de entrada da aplicação
├── strategy/                      # Padrão Strategy - Armas
│   ├── Arma.java                 # Interface Strategy
│   ├── EspadaLonga.java          # Arma com sangramento
│   ├── ArcoElfico.java           # Arma com ataque em área
│   ├── CajadoArcano.java         # Arma mágica com queimadura
│   ├── MachadoDeGuerra.java      # Arma com atordoamento
│   └── AdagaSombria.java         # Arma furtiva
├── personagens/                   # Classes de personagens
│   ├── Personagem.java           # Classe abstrata base
│   ├── Guerreiro.java            # Tanque com redução de dano
│   ├── Arqueiro.java             # DPS com esquiva
│   └── Mago.java                 # Mago com regeneração de mana
├── effects/                       # Sistema de efeitos de status
│   ├── StatusEffect.java         # Interface para efeitos
│   ├── Sangramento.java          # Dano contínuo
│   ├── Queimadura.java           # Dano de fogo
│   └── Atordoado.java            # Controle de multidão
└── combat/                        # Sistema de batalha
    └── Batalha.java              # Gerenciador de combate
```

## ⚔️ Armas Implementadas

### 🗡️ Espada Longa
- **Dano Base**: 15
- **Efeito Especial**: "Corte Profundo" - 30% de chance de sangramento (5 de dano por 3 turnos)
- **Custo de Mana**: 0
- **Requisito**: Força ≥ 10

### 🏹 Arco Élfico
- **Dano Base**: 12
- **Efeito Especial**: "Chuva de Flechas" - Ataque em área (atinge todos os inimigos)
- **Custo de Mana**: 15
- **Requisito**: Destreza ≥ 8

### 🔮 Cajado Arcano
- **Dano Base**: 8
- **Efeito Especial**: "Bola de Fogo" - Causa queimadura (10 de dano por 2 turnos)
- **Custo de Mana**: 25
- **Requisito**: Inteligência ≥ 12

### 🪓 Machado de Guerra
- **Dano Base**: 18
- **Efeito Especial**: "Golpe Esmagador" - 25% de chance de atordoar (inimigo perde 1 turno)
- **Custo de Mana**: 5
- **Requisito**: Força ≥ 15

### 🗡️ Adaga Sombria
- **Dano Base**: 10
- **Efeito Especial**: "Ataque Furtivo" - Dano triplo se o inimigo estiver desprevenido (vida cheia)
- **Custo de Mana**: 10
- **Requisito**: Destreza ≥ 12

## 🎭 Classes de Personagens

### 🛡️ Guerreiro
- **Atributos**: Força 15 | Destreza 8 | Inteligência 5
- **Vida**: 120 | **Mana**: 50
- **Habilidade Passiva**: "Pele Dura" - Reduz dano recebido em 20%
- **Armas Compatíveis**: Espadas, Machados

### 🏹 Arqueiro
- **Atributos**: Força 8 | Destreza 15 | Inteligência 7
- **Vida**: 90 | **Mana**: 80
- **Habilidade Passiva**: "Esquiva" - 25% de chance de evitar ataques
- **Armas Compatíveis**: Arcos, Adagas

### 🧙 Mago
- **Atributos**: Força 5 | Destreza 7 | Inteligência 18
- **Vida**: 70 | **Mana**: 150
- **Habilidade Passiva**: "Regeneração de Mana" - Recupera +10 mana por turno
- **Armas Compatíveis**: Cajados, Adagas

## 🌀 Sistema de Efeitos de Status

### 🩸 Sangramento
- **Dano**: 5 por turno
- **Duração**: 3 turnos
- **Fonte**: Espada Longa (Corte Profundo)

### 🔥 Queimadura
- **Dano**: 10 por turno
- **Duração**: 2 turnos
- **Fonte**: Cajado Arcano (Bola de Fogo)

### 💫 Atordoado
- **Efeito**: Personagem não pode agir
- **Duração**: 1 turno
- **Fonte**: Machado de Guerra (Golpe Esmagador)

## 🎮 Sistema de Combate

O sistema de batalha implementa:

- ✅ Combate baseado em turnos
- ✅ Gerenciamento automático de efeitos de status
- ✅ Aplicação de habilidades passivas
- ✅ Sistema de mana para habilidades especiais
- ✅ Verificação de requisitos de atributos para armas
- ✅ Ataques únicos e em área
- ✅ Interface interativa para escolha de ações
- ✅ Condições de vitória/derrota

## 🚀 Como Executar

### Compilação
```bash
javac -d bin src/**/*.java src/*.java
```

### Execução
```bash
java -cp bin Main
```

### Ou via VS Code
Pressione `F5` ou use o botão "Run" para executar o projeto diretamente.

## 💡 Conceitos Aplicados

### Padrões de Projeto
- **Strategy**: Sistema de armas intercambiáveis
- **Template Method**: Classe abstrata `Personagem` com métodos abstratos

### Princípios SOLID
- **Single Responsibility**: Cada classe tem uma responsabilidade única
- **Open/Closed**: Aberto para extensão (novas armas), fechado para modificação
- **Liskov Substitution**: Todas as armas implementam a interface `Arma`
- **Interface Segregation**: Interfaces específicas e coesas
- **Dependency Inversion**: Dependência de abstrações (interfaces)

### Programação Orientada a Objetos
- **Encapsulamento**: Atributos privados com getters
- **Herança**: Hierarquia de personagens
- **Polimorfismo**: Diferentes implementações de `Arma`
- **Abstração**: Classes e interfaces abstratas

## 🎯 Funcionalidades Implementadas

### Básicas ✅
- [x] Interface `Arma` e 5 tipos diferentes de armas
- [x] Classes `Guerreiro`, `Arqueiro` e `Mago`
- [x] Método `atacar()` com dano básico

### Efeitos Especiais ✅
- [x] Sistema de `StatusEffect`
- [x] 3 efeitos diferentes (sangramento, queimadura, atordoado)
- [x] Lógica de aplicação de efeitos nas armas

### Sistema de Batalha ✅
- [x] Classe `Batalha` que gerencia turnos
- [x] Troca de armas durante a batalha
- [x] Lógica de vitória/derrota

### Extensões ✅
- [x] 5 armas com efeitos únicos
- [x] Sistema de efeitos de status complexo
- [x] Habilidades passivas únicas por classe
- [x] Sistema de requisitos de atributos
- [x] Gerenciamento de mana
- [x] Ataques em área

## 📝 Exemplo de Uso

```java
// Criar personagens
Guerreiro guerreiro = new Guerreiro("Thorin");
Mago mago = new Mago("Gandalf");

// Criar e equipar armas
MachadoDeGuerra machado = new MachadoDeGuerra();
CajadoArcano cajado = new CajadoArcano();

guerreiro.equiparArma(machado);
mago.equiparArma(cajado);

// Criar times
List<Personagem> time1 = Arrays.asList(guerreiro);
List<Personagem> time2 = Arrays.asList(mago);

// Iniciar batalha
Batalha batalha = new Batalha(time1, time2);
batalha.iniciar();
```

## 🔄 Extensões Futuras

- [ ] Sistema de inventário
- [ ] Mais classes híbridas (Paladino, Druida, etc.)
- [ ] Sistema de níveis e experiência
- [ ] Armaduras com efeitos especiais
- [ ] Combos de ataques
- [ ] Sistema de missões
- [ ] Persistência de dados (save/load)

## 📚 Aprendizados

Este projeto demonstra:
- Aplicação prática do padrão Strategy
- Design orientado a objetos
- Separação de responsabilidades
- Código limpo e manutenível
- Extensibilidade sem modificar código existente

## 👤 Autor

William Felipe Cosc

## 📄 Licença

Este projeto foi desenvolvido para fins educacionais.
