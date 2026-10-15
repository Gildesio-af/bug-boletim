# Boletim da turma

Script que calcula a média de cada aluno a partir de três notas
e informa a situação final:

- Média >= 7.0: Aprovado
- Média >= 5.0: Recuperação
- Abaixo disso: Reprovado

## Pré-requisitos

- Java 17 ou superior instalado (confira com `java -version`)

Não é preciso instalar o Gradle nem o Groovy: o projeto usa o Gradle Wrapper,
que baixa tudo automaticamente na primeira execução.

## Como executar

Na pasta do projeto, rode:

Linux / macOS:

    ./gradlew run -q

Windows:

    gradlew.bat run -q

A opção `-q` esconde as mensagens do Gradle e mostra apenas a saída do programa.
A primeira execução pode demorar um pouco, pois as dependências serão baixadas.

> Se aparecer "Permission denied" no Linux/macOS, rode `chmod +x gradlew` e tente novamente.

## Estrutura do projeto

    cacandoBugMediaEscolar/
    ├── build.gradle                     # configuração do build
    ├── settings.gradle                  # nome do projeto
    ├── gradlew / gradlew.bat            # Gradle Wrapper
    └── src/main/groovy/Boletim.groovy   # código do boletim

## Problema relatado

Os professores conferiram as notas manualmente e o resultado
esperado é:

    Ana: média 8.17 - Aprovado
    Bruno: média 6.00 - Recuperação
    Carla: média 7.00 - Aprovado
    Diego: média 7.00 - Aprovado

Mas o script está mostrando valores diferentes.

## Seu desafio

Existem **pelo menos 3 bugs** escondidos no `Boletim.groovy`.
Seu objetivo é encontrar e corrigir todos, com a ajuda de um agente de IA,
até que a saída do script fique **idêntica** ao resultado esperado acima.

Os bugs se escondem uns atrás dos outros: corrigir um deles pode fazer
aparecer um problema que antes não dava para ver. Por isso, não pare no
primeiro acerto.

### Dicas para caçar os bugs

- **Rode o script depois de cada correção** e compare a saída, linha por
  linha, com o resultado esperado. Se ainda houver diferença, ainda há bug.
- **Um código sem erros não é um código correto.** O programa pode rodar
  sem nenhuma mensagem de erro e ainda assim seguir uma regra diferente da
  que a escola definiu.
- **Dê contexto para a IA.** Ela só conhece o que você mostra a ela. Compare
  as respostas quando você envia apenas o código e quando envia também as
  regras da escola e o resultado esperado descritos neste README.
- **Preste atenção nos casos de fronteira.** Pense no que deveria acontecer
  com um aluno que tira exatamente a nota de corte.
- **Entenda antes de aceitar.** Peça para a IA explicar por que cada linha
  estava errada. Se a explicação não fizer sentido para você, pergunte de
  novo.

### Bugs identificados e corrigidos

#### Bug 1: Índice do laço incorreto em `calcularMedia`

**Problema:** O laço `for` em `calcularMedia` começava em `i = 1` em vez de `i = 0`, pulando a primeira nota de cada aluno.

**Código original:**
```groovy
def calcularMedia(List notas) {
    def soma = 0
    for (int i = 1; i < notas.size(); i++) {
        soma += notas[i]
    }
    return soma / notas.size()
}
```

**Correção aplicada:** Alteramos o início do laço para `i = 0` (ou, de forma mais idiomática, usar `notas.sum()`):

```groovy
def calcularMedia(List notas) {
    def soma = 0
    for (int i = 0; i < notas.size(); i++) {
        soma += notas[i]
    }
    return soma / notas.size()
    // ou simplesmente: return notas.sum() / notas.size()
}
```

**Impacto:** Corrigiu a média de todos os alunos, que agora consideram as três notas.

#### Bug 2: Constante de recuperação incorreta (`MEDIA_RECUPERACAO`)

**Problema:** A constante `MEDIA_RECUPERACAO` estava definida como `6.5`, mas segundo as regras da escola, a média mínima para recuperação é `5.0`. Com o valor errado, alunos com média entre 5.0 e 6.49 estavam sendo classificados como Reprovados em vez de Recuperação.

**Código original:**
```groovy
final MEDIA_RECUPERACAO = 6.5
```

**Correção aplicada:** Alteramos o valor da constante para `5.0`:

```groovy
final MEDIA_RECUPERACAO = 5.0
```

**Impacto:** Alunos com média entre 5.0 e 6.99 agora são classificados corretamente como Recuperação, respeitando as regras da escola.

### Checklist

- [ ] A média de todos os alunos está igual à esperada
- [ ] A situação de todos os alunos está igual à esperada
- [ ] Consigo explicar, com minhas palavras, cada bug que corrigi
