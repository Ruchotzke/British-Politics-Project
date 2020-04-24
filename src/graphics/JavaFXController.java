package graphics;

import Geography.Constituency;
import Simulation.Party;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.scene.control.Label;

import java.awt.*;
import java.util.Map;

public class JavaFXController {

    @FXML
    public VBox vbox_Constituencies;

    @FXML
    public Label text_constituencyName;

    @FXML
    public Label text_voteResult;

    @FXML
    public PieChart chart_constResult;

    public void insertConstituencyButton(Constituency c){
        Button finder = new Button();
        finder.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        finder.setText(c.name);
        finder.setTextAlignment(TextAlignment.LEFT);
        finder.setBackground(new Background(new BackgroundFill(c.winningParty.partyColor, CornerRadii.EMPTY, Insets.EMPTY)));
        finder.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                onConstituencyClicked(c);
            }
        });
        vbox_Constituencies.getChildren().add(finder);
    }

    private void onConstituencyClicked(Constituency c){
        System.out.println("Selecting " + c.name);
        text_constituencyName.setText(c.name + "    Region: " + c.country);

        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        for(Map.Entry<Party, Integer> entry : c.votesPerParty.entrySet()){
            chartData.add(new PieChart.Data(entry.getKey().name, entry.getValue()));
        }

        chart_constResult.setData(chartData);
    }
}
