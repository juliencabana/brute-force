/*****************************************************************
 *
 * This program is a cybersecurity learning project. This is the
 * first version where I tried to come up with something on my own
 * without using any external resources. A possibly optimized version
 * will maybe come later.
 *
 * @author Julien Cabana
 * @version 2024-08-06
 *
 *****************************************************************/


// TODO: Create MainWindow.class, DummyApp.class, Apprendre StringBuilder


public class PasswordCracker {

    // TO DO :
    // 1 : DOCUMENTATION FOR ALL PARAMETERS

    /* List of characters to look for in a password, one of them will become the characterSet variable.
     *  Time can be exponentially longer according to the chosen set.
     */
    private final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final String LOWER_UPPER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final String LOWER_SPECIAL = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()-=";
    private final String UPPER_SPECIAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-=";
    private final String ALL_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-=";

    private String targetPassword = "";
    private int numberOfCharacters;

    /*
     * This method is the entry to the loop
     *
     *
     *
     */
    private static String firstLoop(String characterSet, String targetPassword, int numberOfCharacters) {

        int loopCount = 1;
        String passwordTest = "";

        for (int index = 0; index < characterSet.length(); index++) {
            passwordTest = characterSet.charAt(index) + "";

            if (passwordTest.equals(targetPassword)) {
                break;
            } else if (loopCount <= numberOfCharacters - 2) {
                middleLoop(characterSet, targetPassword, numberOfCharacters, loopCount, passwordTest);
            } else if (loopCount == numberOfCharacters - 1) {
                lastLoop(characterSet, targetPassword, passwordTest);
            }
        }
        return passwordTest;
    }

    /*
     * This method is for the middle For Loops for characters that aren't first or last
     * @param The Set of characters included in the search
     * @param The password to crack
     * @param The passwordTest value from the precedent loop
     *
     */
    private static String middleLoop(String characterSet, String targetPassword, int numberOfCharacters, int loopCount, String passwordTest) {

        loopCount += 1;

        for (int index = 0; index < characterSet.length(); index++) {
            passwordTest = passwordTest + characterSet.charAt(index);
            if (passwordTest.equals(targetPassword)) {
                break;
            } else if (loopCount <= numberOfCharacters - 2) {
                middleLoop(characterSet, targetPassword, numberOfCharacters, loopCount, passwordTest);
            } else if (loopCount == numberOfCharacters - 1) {
                lastLoop(characterSet, targetPassword, passwordTest);
            }
        }
        return passwordTest;
    }

    /*
     * This method is for the last For Loop for the last character of the target.
     * @param The Set of characters included in the search
     * @param The password to crack
     * @param The passwordTest value from the precedent loop
     *
     */
    private static String lastLoop(String characterSet, String targetPassword, String passwordTest) {

        for (int index = 0; index < characterSet.length(); index++) {
            passwordTest = passwordTest + characterSet.charAt(index);
            if (passwordTest.equals(targetPassword)) {

                break;
            }
        }
        return passwordTest;
    }

    // TODO: CREATE THE BIG LOOP METHOD HERE, WATCH FOR RETURN STATEMENTS IN INTERMERDIARY LOOPS


    private static void main(String[] args) {

    }
}
