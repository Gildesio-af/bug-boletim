package com.example

import spock.lang.Specification

class BoletimSpec extends Specification {
    def "calcularMedia retorna a media correta para uma lista de notas fornecida"() {
        given:
        def notas = [8.0, 7.5, 9.0]

        when:
        def media = Boletim.calcularMedia(notas)

        then:
        media == 8.17
    }

    def "situacao: media >= 7.0 -> Aprovado"() {
        expect:
        Boletim.situacao(7.0, 7.0, 5.0) == "Aprovado"
    }

    def "situacao: media >= 5.0 e < 7.0 -> Recuperacao"() {
        expect:
        Boletim.situacao(6.0, 7.0, 5.0) == "Recuperacao"
    }

    def "situacao: media < 5.0 -> Reprovado"() {
        expect:
        Boletim.situacao(4.0, 7.0, 5.0) == "Reprovado"
    }

    def "situacao na fronteira de aprovacao: exatamente 7.0 -> Aprovado"() {
        expect:
        Boletim.situacao(7.0, 7.0, 5.0) == "Aprovado"
    }

    def "situacao na fronteira de recuperacao: exatamente 5.0 -> Recuperacao"() {
        expect:
        Boletim.situacao(5.0, 7.0, 5.0) == "Recuperacao"
    }

    def "situacao de reprovacao na fronteira mais baixa: exatamente 0.0 -> Reprovado"() {
        expect:
        Boletim.situacao(0.0, 7.0, 5.0) == "Reprovado"
    }
}