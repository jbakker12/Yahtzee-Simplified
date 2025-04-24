package assignment5_000947416;

/**
 * This class is an array of all five dice put together.
 * The DiceCollection can be rolled together
 * Then they can be checked for various combinations based on the dice values
 * @author Jacob Bakker 000947416
 */

public class DiceCollection {
    /** sets up the array for the DieCollection **/
    private Die[] diceArray;

    /** sets up the temporary array used for the roll method **/
    public int[] turn = new int[5];

    /**sets up the variable for counting number one dice **/
    public int ones;

    /**sets up the variable for counting number two dice **/
    public int twos;

    /**sets up the variable for counting number three dice **/
    public int threes;

    /**sets up the variable for counting number four dice **/
    public int fours;

    /**sets up the variable for counting number five dice **/
    public int fives;

    /**sets up the variable for counting number six dice **/
    public int sixes;



    /**
     * Default constructor for DiceCollection.
     */
    public DiceCollection() {
        //array is created to have 5 indices
        diceArray = new Die[5];

        //5 dice added to dice array
        for (int i = 0; i < 5; i++) {
            diceArray[i] = new Die();
        }
    }


    /**
     * Roll method rolls the dice array of five dice together at the same time
     * @return int array 'turn' to be used in subsequent methods
     */
    public int[] roll() {
        for (int i = 0; i < 5; i++) {
            turn[i] = diceArray[i].rollDie(); //will add one to index of roll
        }
        return turn;
    }


    /**
     * This method counts the number of times a number from one to six shows up among the five dice rolled
     * These numbers are used in subsequent methods of Yahtzee, isFourKind, and isThreeKind.
     */
    public void isOfKind() {
        ones = 0;
        twos = 0;
        threes = 0;
        fours = 0;
        fives = 0;
        sixes = 0;
        for (int i = 0; i < 5; i++) {
            if (turn[i] == 1) {
                ones += 1;
            }
            else if (turn[i] == 2) {
                twos += 1;
            }
            else if (turn[i] == 3) {
                threes += 1;
            }
            else if (turn[i] == 4) {
                fours += 1;
            }
            else if (turn[i] == 5) {
                fives += 1;
            }
            else {
                sixes += 1;
            }
        }
    }

    /**
     * isYahtzee() determines if the dice rolled make a yahtzee (five of a kind)
     * @return Boolean isYahtzee;
     */
    public Boolean isYahtzee() {
        Boolean isYahtzee = false;
        if (ones == 5 || twos == 5 || threes == 5 || fours == 5 || fives == 5 || sixes == 5) {
            isYahtzee = true;
        }
        return isYahtzee;
    }


    /**
     * isFourKind() determines if four of the five dice rolled have the same number showing
     * @return Boolean isFourKind.
     */
    public Boolean isFourKind(){
        Boolean isFourKind = false;
        if (ones == 4 || twos == 4 || threes == 4 || fours == 4 || fives == 4 || sixes == 4) {
            isFourKind = true;
        }
        return isFourKind;
    }


    /**
     * isThreeKind() determines if three of the five dice rolled have the same number showing.
     * @return Boolean isThreeKind().
     */
    public Boolean isThreeKind(){
        Boolean isThreeKind = false;
        if (ones == 3 || twos == 3 || threes == 3 || fours == 3 || fives == 3 || sixes == 3 ) {
            isThreeKind = true;
        }
        return isThreeKind;
    }


    /**
     * This method determines if the dice rolled make a large straight, a sequence of FOUR (ex: 1-2-3-4-1, OR 6-3-4-5-6)
     * @return Boolean isLargeStraight;
     */
    public Boolean isLargeStraight(){
        Boolean isLargeStraight = false;
        if(((turn[0]+1 == turn[1] && turn[1]+1 ==turn[2] && turn[2]+1 == turn[3])
                || turn[1]+1 == turn[2] && turn[2]+1 ==turn[3] && turn[3]+1 == turn[4])){
            isLargeStraight = true;
        }
        return isLargeStraight;
    }


    /**
     * This method determines if the dice rolled make a small straight, a sequence of THREE (ex: 1-2-3-2-6, OR 6-3-4-5-1 etc.)
     * @return Boolean isSmallStraight;
     */
    public Boolean isSmallStraight(){
        Boolean isSmallStraight = false;
        if((turn[0]+1 == turn[1] && turn[1]+1 ==turn[2]) || (turn[1]+1 == turn[2] && turn[2]+1 ==turn[3]) || (turn[2]+1==turn[3] && turn[3]+1==turn[4])){
            isSmallStraight = true;
        }
        return isSmallStraight;
    }
}
