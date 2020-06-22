package sample;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import smile.Network;

public class Controller {
    private static final String resultNode = "cidade";
    private static final String xdslFile = "model.xdsl";

    Network network;

    @FXML
    ComboBox<String> gender;

    @FXML
    ComboBox<String> etinical;

    @FXML
    ComboBox<String> ageGroup;

    @FXML
    ComboBox<String> treatmentType;

    @FXML
    TextArea resultText;

    public void initialize() {
        this.initCombos();
        this.startNetwork();
    }

    @FXML
    public void calcProbability() {
        this.resultText.clear();

        this.setEvidences();

        network.updateBeliefs();
        double[] beliefs = network.getNodeValue(this.resultNode);
        String result = "Probabilidades\n\n";
        for (int i = 0; i < beliefs.length; i++) {
            result += String.format("Cidade: %s\nProbabilidade: %s \n\n",
                    network.getOutcomeId(this.resultNode, i).toUpperCase(),
                    beliefs[i] * 100 + "%");
        }

        System.out.println(result);
        this.resultText.setText(result);
    }

    public void startNetwork() {
        this.network = new Network();
        network.readFile(this.xdslFile);
    }

    public void setEvidences() {
        network.clearAllEvidence();
        this.setSingleEvidence(network, "faixa_etaria", this.ageGroup);
        this.setSingleEvidence(network, "tipo_atendimento", this.treatmentType);
        this.setSingleEvidence(network, "raca", this.etinical);
        this.setSingleEvidence(network, "sexo", this.gender);
    }

    public void setSingleEvidence(Network network, String evidenceName, ComboBox combo) {
        if (combo.getValue() != null) {
            String comboValue = combo.getValue().toString();
            if (combo != null && comboValue != null && comboValue != "Não informar") {
                network.setEvidence(evidenceName, combo.getValue().toString());
            }
        }
    }

    void initCombos() {
        this.gender.getItems().addAll(
                "Não informar",
                "masc",
                "fem"
        );

        this.etinical.getItems().addAll(
                "Não informar",
                "branca",
                "preta",
                "parda",
                "amarela",
                "indigena",
                "sem_informacao"
        );

        this.ageGroup.getItems().addAll(
                "Não informar",
                "menor_1",
                "ano_1_a_4",
                "ano_5_a_9",
                "ano_10_a_14",
                "ano_15_a_19",
                "ano_20_a_29",
                "ano_30_a_39",
                "ano_40_a_49",
                "ano_50_a_59",
                "ano_60_a_69",
                "ano_70_a_79",
                "ano_80_e_mais"
        );

        this.treatmentType.getItems().addAll(
                "Não informar",
                "eletivo",
                "urgencia",
                "acidente_local_trabalho",
                "outros_acidentes_trabalho",
                "outras_causas_externas"
        );
    }


}
