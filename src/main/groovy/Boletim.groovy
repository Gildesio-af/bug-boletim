class Boletim {
    static final MEDIA_APROVACAO = 7.0
    static final MEDIA_RECUPERACAO = 5.0

    static void main(String[] args) {
        def alunos = [
            "Ana"  : [8.0, 7.5, 9.0],
            "Bruno": [5.0, 6.0, 7.0],
            "Carla": [10.0, 4.0, 7.0],
            "Diego": [3.0, 9.0, 9.0],
        ]

        println "=== Boletim da turma ==="
        alunos.each { nome, notas ->
            def media = calcularMedia(notas)
            def mediaFormatada = String.format(Locale.US, "%.2f", media)
            println "${nome}: média ${mediaFormatada} - ${situacao(media, MEDIA_APROVACAO, MEDIA_RECUPERACAO)}"
        }
    }

    static def calcularMedia(List notas) {
        def soma = 0
        for (int i = 0; i < notas.size(); i++) {
            soma += notas[i]
        }
        return soma / notas.size()
    }

    static def situacao(media, mediaAprovacao, mediaRecuperacao) {
        if (media >= mediaAprovacao) {
            return "Aprovado"
        } else if (media >= mediaRecuperacao) {
            return "Recuperação"
        } else {
            return "Reprovado"
        }
    }
}