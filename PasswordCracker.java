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


// TODO: Apprendre StringBuilder, Learn how to Refactor correctly

public class PasswordCracker {

    /* List of characters to look for in a password, one of them will become the characterSet variable.
     * Time can be exponentially longer according to the chosen set.
     */
    private final static String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private final static String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String LOWER_UPPER = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private final static String LOWER_SPECIAL = "abcdefghijklmnopqrstuvwxyz!@#$%^&*()-=";
    private final static String UPPER_SPECIAL = "ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-=";
    private final static String ALL_CHARACTERS = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$%^&*()-=";


    // Field
    private String characterSet = "";
    private String targetPassword = "";
    private int numberOfCharacters;

    // Constructor ; The program is launched through the constructor when an object PasswordCracker is created
    public PasswordCracker(String characterSet, String targetPassword, int numberOfCharacters) {

        this.characterSet = characterSet;
        this.targetPassword = targetPassword;
        this.numberOfCharacters = numberOfCharacters;
        String result = passwordCrack(characterSet, targetPassword, numberOfCharacters);
        System.out.println(result);

    }

    /*
     * This method is the entry to the loop
     */
    private static String firstLoop(String characterSet, String targetPassword, int numberOfCharacters) {

        int loopCount = 1;
        String passwordTest = "";

        for (int index = 0; index < characterSet.length(); index++) {

            passwordTest = characterSet.charAt(index) + "";

            if (passwordTest.equals(targetPassword)) {
                break;
            } else if (loopCount <= numberOfCharacters - 2) {
                passwordTest = middleLoop(characterSet, targetPassword, numberOfCharacters, loopCount, passwordTest);
                if (passwordTest.equals(targetPassword)) {
                    return passwordTest;
                }
            } else if (loopCount == numberOfCharacters - 1) {
                passwordTest = lastLoop(characterSet, targetPassword, passwordTest);
                if (passwordTest.equals(targetPassword)) {
                    return passwordTest;
                }
            }
        }
        return passwordTest;
    }

    /*
     * This method is for the middle For Loops for characters that aren't first or last
     * @param The Set of characters included in the search
     * @param The password to crack
     * @param The previousPasswordTest value from the precedent loop
     *
     */
    private static String middleLoop(String characterSet, String targetPassword, int numberOfCharacters, int loopCount, String previousPasswordTest) {

        String passwordTest = "";
        loopCount += 1;

        for (int index = 0; index < characterSet.length(); index++) {

            passwordTest = previousPasswordTest + characterSet.charAt(index);

            if (passwordTest.equals(targetPassword)) {
                break;
            } else if (loopCount <= numberOfCharacters - 2) {
                passwordTest = middleLoop(characterSet, targetPassword, numberOfCharacters, loopCount, passwordTest);
                if (passwordTest.equals(targetPassword)) {
                    return passwordTest;
                }
            } else if (loopCount == numberOfCharacters - 1) {
                passwordTest = lastLoop(characterSet, targetPassword, passwordTest);
                if (passwordTest.equals(targetPassword)) {
                    return passwordTest;
                }
            }
        }
        return passwordTest;
    }

    /*
     * This method is for the last For Loop for the last character of the target.
     * @param The Set of characters included in the search
     * @param The password to crack
     * @param The previousPasswordTest value from the precedent loop
     *
     */
    private static String lastLoop(String characterSet, String targetPassword, String previousPasswordTest) {

        boolean found = false;
        String passwordTest = "";

        for (int index = 0; index < characterSet.length(); index++) {
            passwordTest = previousPasswordTest + characterSet.charAt(index);
            if (passwordTest.equals(targetPassword)) {
                found = true;
                break;
            }
        }
        return passwordTest;
    }


    /*
     * This method allows the search to be for a password of length of '1 to numberOfCharacters' instead of
     * 'numberOfCharacters' exactly
     * @param
     */
    private static String passwordCrack(String characterSet, String targetPassword, int numberOfCharacters) {

        String crackedPassword = "";

        for (int i = 1; i <= numberOfCharacters; i++) {
            crackedPassword = firstLoop(characterSet, targetPassword, i);
            if (crackedPassword.equals(targetPassword)) {
                break;
            }
        }
        if (!crackedPassword.equals(targetPassword)) {
            crackedPassword = "PasswordCracker: Password hasn't been cracked!";
        }
        return crackedPassword;
    }

    public static void main(String[] args) {

        PasswordCracker passwordCracker = new PasswordCracker(LOWER_UPPER, "bofb", 4);

    }
}
