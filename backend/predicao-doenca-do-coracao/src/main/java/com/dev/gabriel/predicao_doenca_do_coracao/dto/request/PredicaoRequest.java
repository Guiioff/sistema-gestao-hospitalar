package com.dev.gabriel.predicao_doenca_do_coracao.dto.request;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;

public record PredicaoRequest(
    @Min(value = 0, message = "A idade deve ser um número positivo") Integer idade,
    @Pattern(
            regexp = "^(masculino|feminino)$",
            message = "O campo 'sexo' deve ser preenchido apenas com 'masculino' ou 'feminino'")
        String sexo,
    @Min(value = 0, message = "O tipo de dor torácica deve ser 0, 1, 2 ou 3")
        @Max(value = 3, message = "O tipo de dor torácica deve ser 0, 1, 2 ou 3")
        Integer tipoDorToracica,
    @Min(value = 0, message = "O valor da pressão sanguínea em repouso deve ser um número positivo")
        Integer pressaoSanguineaRepouso,
    @Min(value = 0, message = "O valor do colesterol sérico deve ser um número positivo")
        Integer colesterolSerico,
    Boolean glicemiaJejumMaiorQue120,
    @Min(
            value = 0,
            message = "O valor do resultado do eletrocardiograma em repouso deve ser 0, 1, ou 2")
        @Max(
            value = 2,
            message = "O valor do resultado do eletrocardiograma em repouso deve ser 0, 1, ou 2")
        Integer resultadosEletrocardiogramaRepouso,
    @Min(value = 0, message = "O valor da frequência cardíaca máxima deve ser um número positivo")
        Integer frequenciaCardiacaMaxima,
    Boolean anginaInduzida,
    @Min(value = 0, message = "O valor da depressão ST induzida deve ser um número positivo")
        @Digits(
            integer = 2,
            fraction = 2,
            message =
                "O valor da depressão ST induzida deve ter, no máximo, 2 dígitos inteiros e 2 casas decimais")
        Float depressaoSTInduzida,
    @Min(value = 0, message = "O valor do pico da inclinação ST deve ser 0, 1, ou 2")
        @Max(value = 2, message = "O valor do pico da inclinação ST deve ser 0, 1, ou 2")
        Integer inclinacaoPicoST,
    @Min(value = 0, message = "O numéro de vasos principais detectados deve ser 0, 1, 2, 3 ou 4")
        @Max(
            value = 4,
            message = "O numéro de vasos principais detectados deve ser 0, 1, 2, 3 ou 4")
        Integer numeroVasosPrincipais,
    @Pattern(
            regexp = "^(normal|reversivel|irreversivel)$",
            message =
                "O campo 'avaliação do teste de esforço' deve ser preenchido apenas com 'normal', 'reversivel' ou 'irreversivel'")
        String avaliacaoTesteEsforco) {}
