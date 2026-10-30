package com.ia.ml.weka;

// Importação de Bibliotecas
import weka.classifiers.Classifier; // Inteface que define métodos obrigatórios para algoritmos de classifcação
import weka.classifiers.trees.J48; // Algoritmo deicisão que aprende a responder: "é fraude? Sim ou não"

import weka.core.Attribute; // Representa uma coluna dos dados (ex: "valor", "origem")
import weka.core.Instance; // Representa uma linha de dados (como uma linha do Excel)
import weka.core.DenseInstance; // Representa uma linha completa de dados (com valores reais)
import weka.core.Instances; // O conjunto completo de dados (como uma planilha) - dataSet

// Ferramentas de log usadas para ocultar avisos do Weka (não afetam o funcionamento do código)
import java.util.logging.Level; // Controla o nível de importância dos avisos exibidos
import java.util.logging.Logger; // Usado para configurar o sistema de logs do java

import java.util.ArrayList;



public class DeteccaoDeFraudeBancaria {
    private Classifier classificador; // Modelo de classificação utilizado
    private Instances dadosTreinaento; // Conjunto de dados usado para treinar o modelo

    // Atributos de conjuntos de dados
    private Attribute atributoValor;
    private Attribute atributoOrigem;
    private Attribute atributoFraude;

    // Definição dos atributos (colunas de "planilha")
    public void definirAtributos(){
        atributoValor = new Attribute("valor"); // Valor da transação

        ArrayList<String> valoresOrigem = new ArrayList<>();
        valoresOrigem.add("internacional");
        valoresOrigem.add("nacional");
        atributoOrigem = new Attribute("origem", valoresOrigem); // Origem da transação

        ArrayList<String> valoresFraude = new ArrayList<>();
        valoresFraude.add("nao");
        valoresFraude.add("sim");
        atributoFraude = new Attribute("fraude", valoresFraude); // Rótulo: É fraude? Sim ou não

        ArrayList<Attribute> atributos = new ArrayList<>();
        atributos.add(atributoValor);
        atributos.add(atributoOrigem);
        atributos.add(atributoFraude);

    }


}
