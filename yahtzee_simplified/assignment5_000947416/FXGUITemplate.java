package assignment5_000947416;


import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This program is a simplified version of Yahtzee.
 * The user will click 'Roll' and five dice will be randomly rolled
 * The sum for that turn will be shown and be added to the grand total
 * The combination type will also be announced for each roll.
 * The game ends after the 13th round and the user has the option to play again.
 * @author Jacob Bakker 000947416
 */

public class FXGUITemplate extends Application {

    // TODO: Instance Variables for View Components and Model
    //DiceCollection created
    DiceCollection allDice;

    //In-window title created
    Label titleLabel;

    //Grand Total box created
    Label grandTotalLabel;

    //Label for grand-total box created
    Label grandTotalDescriptionLabel;
    int grandTotal = 0;

    //Round Count progress bar created
    ProgressBar roundCountProgressBar;
    double roundCountBarValue;

    //Label for Round count box created
    Label roundNumberLabel;
    int roundNumber = 0;

    //Roll Message box created
    Label rollMessageLabel;

    //Five dice boxes created
    Label die1;
    Label die2;
    Label die3;
    Label die4;
    Label die5;

    //Round sum box created
    Label roundSum;

    //Label for round sum box created
    Label roundSumDescription;

    //roll again button
    Button rollButton;

    //play again button
    Button playAgainButton;

    // TODO: Private Event Handlers and Helper Methods


    /**
     *
     * Roll routine will randomly roll five dice
     * Then it will assign the numbers to the die boxes, assign the appropriate roll message and sum
     * Afterward the round number will go up by one, stopping at 13, with the progress bar matching the number of rounds.
     * @param 'Action Event e' which is triggered by rollButton being clicked
     */
    private void rollRoutine(ActionEvent e) {
        int turnSum = 0;
        int[] d = allDice.roll();
        allDice.isOfKind();

        String s = String.format("%d",d[0]);
        die1.setText(s);

        s = String.format("%d",d[1]);
        die2.setText(s);

        s = String.format("%d",d[2]);
        die3.setText(s);

        s = String.format("%d",d[3]);
        die4.setText(s);

        s = String.format("%d",d[4]);
        die5.setText(s);

        if(allDice.isYahtzee()){
            rollMessageLabel.setText("Yahtzee!");
            rollMessageLabel.setStyle("-fx-text-fill: black; -fx-border-color: black; -fx-background-color: orange");
            roundSum.setText("50 pts");
            turnSum = 50;
        }

        //large straight
        else if (allDice.isLargeStraight()){
            rollMessageLabel.setText("Large Straight!");
            rollMessageLabel.setStyle("-fx-text-fill: green; -fx-border-color: black;");
            roundSum.setText("40 pts");
            turnSum = 40;
        }

        //small straight
        else if (allDice.isSmallStraight()){
            rollMessageLabel.setText("Small Straight!");
            rollMessageLabel.setStyle("-fx-text-fill: green; -fx-border-color: black;");
            roundSum.setText("30 pts");
            turnSum = 30;
        }

        //four of a kind (more complicated. only do if rest of program runs)
        else if(allDice.isFourKind()) {
            turnSum = d[0] + d[1] + d[2] + d[3] + d[4];
            String regularSum = String.format("%d pts", turnSum);
            rollMessageLabel.setText("Four of a Kind!");
            rollMessageLabel.setStyle("-fx-text-fill: darkorange; -fx-border-color: black;");
            roundSum.setText(regularSum);
        }

        //three of a kind (more complicated. only do if rest of program runs)
        else if(allDice.isThreeKind()) {
            turnSum = d[0] + d[1] + d[2] + d[3] + d[4];
            String regularSum = String.format("%d pts", turnSum);
            rollMessageLabel.setText("Three of a Kind!");
            rollMessageLabel.setStyle("-fx-text-fill: purple; -fx-border-color: black;");
            roundSum.setText(regularSum);
        }

        //chance
        else{
            turnSum = d[0] + d[1] + d[2]+ d[3]+ d[4];
            String regularSum = String.format("%d pts",turnSum);
            rollMessageLabel.setText("Chance!");
            rollMessageLabel.setStyle("-fx-text-fill: black; -fx-border-color: black;");
            roundSum.setText(regularSum);
        }

        //grand total increased
        grandTotal += turnSum;
        grandTotalLabel.setText(String.format("%d pts",grandTotal));

        //round count and progress bar increased
        roundCountBarValue += 0.077;
        roundCountProgressBar.setProgress(roundCountBarValue);
        roundNumber += 1;
        roundNumberLabel.setText(String.format("Round %d of 13",roundNumber));

        //last round will have unique message
        if (roundNumber == 13){
            rollMessageLabel.setText(String.format("Congrats! Your final score is %d pts!",grandTotal));
            rollMessageLabel.setStyle("-fx-text-fill: green; -fx-border-color: black; -fx-background-color: white");
            //roll button disabled
            rollButton.setDisable(true);

            //play again button enabled
            playAgainButton.setDisable(false);
        }
    }


    /**
     * Reset game sets the game back to its default values and formatting.
     * The 'Play Again?' button is disabled, and the 'Roll' button is re-enabled.
     * @param 'Action event f' is triggered when the user clicks the 'Play Again?' button.
     */
    public void resetGame(ActionEvent f){
        //roll button enabled
        rollButton.setDisable(false);

        //play again button enabled
        playAgainButton.setDisable(true);

        //field values and formatting styles reset
        grandTotalLabel.setText("");
        roundCountProgressBar.setProgress(0.0);
        rollMessageLabel.setText("Click the 'Roll' button to start");
        rollMessageLabel.setStyle("-fx-border-color: black; -fx-text-fill: green; -fx-background-color: transparent");
        roundNumberLabel.setText("Round 0 of 13");

        die1.setText("0");
        die2.setText("0");
        die3.setText("0");
        die4.setText("0");
        die5.setText("0");

        roundSum.setText("0 pts");
        roundNumber = 0;
        grandTotal = 0;
        roundCountBarValue = 0.0;
    }







    /**
     * This is where you create your components and the model and add event
     * handlers.
     *
     * @param stage The main stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {

        Pane root = new Pane();
        Scene scene = new Scene(root, 600, 325); // set the size here
        stage.setTitle("Yahtzee (Simplified)"); // set the window title here
        stage.setScene(scene);
        // TODO: Add your GUI-building code here

        // 1. Create the model
        allDice = new DiceCollection();

        // 2. Create the GUI components
        titleLabel = new Label("Yahtzee (Simplified)");
        grandTotalLabel = new Label("");
        grandTotalDescriptionLabel = new Label("Grand Total:");
        roundCountProgressBar = new ProgressBar(0.0);
        rollMessageLabel = new Label("Click the 'Roll' button to start");
        roundNumberLabel = new Label("Round 0 of 13");

        die1 = new Label("0");
        die2 = new Label("0");
        die3 = new Label("0");
        die4 = new Label("0");
        die5 = new Label("0");

        roundSum = new Label("0 pts");
        roundSumDescription = new Label("Round Sum:");
        rollButton = new Button("Roll");
        playAgainButton = new Button("Play Again?");


        // 3. Add components to the root
        root.getChildren().addAll(titleLabel, grandTotalDescriptionLabel, grandTotalLabel, roundCountProgressBar, rollMessageLabel, roundNumberLabel,die1,die2,die3,die4,die5, roundSumDescription,roundSum,rollButton, playAgainButton);

        // 4. Configure the components (colors, fonts, size, location)
        Font titleFont = new Font("Impact", 35);
        titleLabel.setFont(titleFont);
        titleLabel.relocate(25,25);
        titleLabel.setPrefSize(425,50);
        titleLabel.setAlignment(Pos.CENTER);

        grandTotalDescriptionLabel.relocate(475,25);
        grandTotalDescriptionLabel.setPrefSize(100,25);
        grandTotalDescriptionLabel.setAlignment(Pos.CENTER);

        grandTotalLabel.relocate(475,50);
        grandTotalLabel.setPrefSize(100,25);
        grandTotalLabel.setStyle("-fx-border-color: black;");
        grandTotalLabel.setAlignment(Pos.CENTER);

        Font rollMessageFont = new Font("System",15);
        rollMessageLabel.setFont(rollMessageFont);
        rollMessageLabel.relocate(25,100);
        rollMessageLabel.setPrefSize(425,50);
        rollMessageLabel.setAlignment(Pos.CENTER);
        rollMessageLabel.setStyle("-fx-text-fill: green; -fx-border-color: black;");

        roundNumberLabel.relocate(475,100);
        roundNumberLabel.setPrefSize(100,25);
        roundNumberLabel.setAlignment(Pos.CENTER);

        roundCountProgressBar.relocate(475,125);
        roundCountProgressBar.setPrefSize(100,25);
        roundCountProgressBar.setStyle("-fx-border-color: black;");

        die1.relocate(62.5,175);
        die1.setPrefSize(50,50);
        die1.setStyle("-fx-border-color: black;");
        die1.setAlignment(Pos.CENTER);

        die2.relocate(137.5,175);
        die2.setPrefSize(50,50);
        die2.setStyle("-fx-border-color: black;");
        die2.setAlignment(Pos.CENTER);

        die3.relocate(212.5,175);
        die3.setPrefSize(50,50);
        die3.setStyle("-fx-border-color: black;");
        die3.setAlignment(Pos.CENTER);

        die4.relocate(287.5,175);
        die4.setPrefSize(50,50);
        die4.setStyle("-fx-border-color: black;");
        die4.setAlignment(Pos.CENTER);

        die5.relocate(362.6,175);
        die5.setPrefSize(50,50);
        die5.setStyle("-fx-border-color: black;");
        die5.setAlignment(Pos.CENTER);

        roundSumDescription.relocate(475,175);
        roundSumDescription.setPrefSize(100,25);
        roundSumDescription.setAlignment(Pos.CENTER);

        roundSum.relocate(475,200);
        roundSum.setPrefSize(100,25);
        roundSum.setStyle("-fx-border-color: black;");
        roundSum.setAlignment(Pos.CENTER);

        rollButton.relocate(125,250);
        rollButton.setPrefSize(100,50);
        rollButton.setStyle("-fx-border-color: black;");

        playAgainButton.relocate(250,250);
        playAgainButton.setPrefSize(100,50);
        playAgainButton.setStyle("-fx-border-color: black;");
        playAgainButton.setDisable(true);

        // 5. Add Event Handlers and do final setup
        rollButton.setOnAction(this::rollRoutine);
        playAgainButton.setOnAction(this::resetGame);

        // 6. Show the stage
        stage.show();
    }

    /**
     * Make no changes here.
     *
     * @param args unused
     */
    public static void main(String[] args) {
        launch(args);
    }
}
