package com.ia.ml.weka;

// Importação de Bibliotecas
import weka.classifiers.Classifier; // Inteface que define métodos obrigatórios para algoritmos de classifcação
import weka.classifiers.trees.J48; // Algoritmo deicisão que aprende a responder: "é fraude? Sim ou não"

import weka.core.Attribute; // Representa uma coluna dos dados (ex: "valor", "origem")
import weka.core.Instance; // Representa uma linha de dados (como uma linha do Excel)
import weka.core.DenseInstance; // Representa uma linha completa de dados (com valores reais)
import weka.core.Instances; // O conjunto completo de dados (como uma planilha) - dataSet



import java.util.ArrayList;

public class DeteccaoDeFraudeBancaria {
    private Classifier classificador; // Modelo de classificação utilizado
    private Instances dadosTreinamento; // Conjunto de dados usado para treinar o modelo

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

        // Criação do DataSet (estutura base da planilha)

        // Cria o dataset chamado "transacoes" com os atributos definidos. Começa vazio (0 linhas).
        dadosTreinamento = new Instances("transacoes", atributos, 0);

        // Define o utilo atributo ("fraude") como a classe alvo para a previsão.
        dadosTreinamento.setClassIndex(dadosTreinamento.numAttributes() - 1);
    }

    // Método auxiliar para criar e adicionar uma nova transação ao dataset de treino
    /** Significa que aqui será criada uma nova instância de dados (uma linha.
     * com a mesma quantidade de colunas (atributos) do dataset dadosTreinamento.
     * é como criar uma nova linha em branco em uma planilha, onde você ainda vai
     * preencher as colunas: valor, origem e fraude.
     */
    private void adicionarTransacao(double valor, String origem, String fraude){
        Instance instancia = new DenseInstance(dadosTreinamento.numAttributes());
        // setDataset(...) é uma configuração obrigadtoria que diz a instância:
        /* "Você vai seguir a mesma estrutura do dataset - os mesmos atributos,
          na mesma ordem e com os mesmos tipos de dados."
         */
        instancia.setDataset(dadosTreinamento);

        instancia.setValue(atributoValor, valor);
        instancia.setValue(atributoOrigem, origem);
        instancia.setValue(atributoFraude, fraude);
        dadosTreinamento.add(instancia);

    }

    // Adição de exemplos (dados de treino para o modelo aprender)
    public void adicionarExemplos() {
        // Exemplos de transações FRAUDULENTAS (valores altos + origem internacional)
        adicionarTransacao(5000, "internacional", "sim");
        adicionarTransacao(10000, "internacional", "sim");
        adicionarTransacao(7500, "internacional", "sim");
        adicionarTransacao(8000, "internacional", "sim");

        // Exemplos de transacoes VALIDAS (valores baixos + origem nacional)
        adicionarTransacao(200, "nacional", "nao");
        adicionarTransacao(500, "nacional", "nao");
        adicionarTransacao(300, "nacional", "nao");
        adicionarTransacao(150, "nacional", "nao");

        // Exemplos adicionais:
        // Transacoes com valores medios/altos em territorio nacional
        // Pode ser usado para demonstrar variação ou desafiar o modelo:

         adicionarTransacao(1000, "nacional", "sim");
         adicionarTransacao(1500, "nacional", "sim");
         adicionarTransacao(20000, "nacional", "sim");
    }

    // Treinamento do modelo
    public void treinarModelo() throws Exception {
        classificador = new J48(); // Cria o modelo de decisão chamado J48 (método matemático)
        classificador.buildClassifier(dadosTreinamento); // Treina o modelo com os dados fornecidos
    }

    // Classificação de novas transações
    public String classificarTransacao(double valor, String origem) throws Exception{
        // Cria uma nova transação para prever se é fraude ou não
        Instance novaInstancia = new DenseInstance(dadosTreinamento.numAttributes());
        novaInstancia.setDataset(dadosTreinamento);

        novaInstancia.setValue(atributoValor, valor);
        novaInstancia.setValue(atributoOrigem, origem);

        // Pedir para o classificador prever se a nova transação é fraude ou não
        // O resultado será 0.0 (não é fraude) ou 1.0 (é fraude), conforme o treinamento
        double previsao = classificador.classifyInstance(novaInstancia);
        // Obs: classifyInstance retorna um numero decimal/real porque usa o mesmo método para prever classes e números.

        // Transforma o número previsto em texto (ex: 0 "nao", 1 "sim") e monta a resposta final
        return "Fraude: " + dadosTreinamento.classAttribute().value((int) previsao);

    }

    // Método para imprimir a estrutura da árvore de decisão no console
    public void exibirArvore() {
        if (classificador == null) {
            System.out.println("O modelo precisa ser treinado antes de exibir a árvore.");
            return;
        }

        System.out.println("\n=== ESTRUTURA DA ÁRVORE DE DECISÃO (J48) ===");
        // O método toString() do J48 renderiza a árvore de forma textual e indentada
        System.out.println(classificador.toString());
        System.out.println("============================================\n");
    }

}
