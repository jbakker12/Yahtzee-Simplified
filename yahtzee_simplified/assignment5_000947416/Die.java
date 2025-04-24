package assignment5_000947416;

/**
 * This class stores the number of sides for one die.
 * It also rolls the one die randomly and returns the current number showing.
 * NOTE: This class has been adapted from assignment 4
 * @author Jacob Bakker 000947416
 */
public class Die {
    /** number of sides on the die **/
    private int sides;
    /** current side showing on the die **/
    private int currentSide;


    /**
     * This constructs the die, assigning the number of sides it has.
     */
    public Die() {
        this.sides = 6;
    }


    /**
     * Rolls the die with a random number between one and six.
     * @return int currentSide is the number showing on the die
     */
    public int rollDie() {
        double min = 1;
        double max = 6;
        int currentSide = (int) ((Math.random() * (max - min)) + min);
        return currentSide;
    }


    /**
     * Replaces the default toString()
     * @return new toString() with current side showing on die
     */

    @Override
    public String toString() {
        String str = String.format("%d",currentSide);
        return str;
    }
}
