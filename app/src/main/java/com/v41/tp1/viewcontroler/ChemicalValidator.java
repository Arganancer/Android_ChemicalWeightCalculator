package com.v41.tp1.viewcontroler;

import com.v41.tp1.modele.PeriodicTable;
import com.v41.tp1.modele.Token;
import com.v41.tp1.modele.TokenType;
import java.util.ArrayList;

/**
 * Class that handles the validation of a chemical formula inputted as a string.
 * Simultaneously handles the extraction of each element of the formula and
 * converts them into Tokens.
 *      @see Token
 */
public enum ChemicalValidator
{
    /**
     * The instance of the ChemicalValidator class.
     */
    INSTANCE;

    /**
     * The container containing the different elements extracted from the user's input
     * during the validation of the formula. A token can be a Number, a Parentheses
     * or a Chemical Element.
     *      @see Token
     */
    ArrayList<Token> formulaTokens = new ArrayList<>();

    /**
     * Array containing the different error messages that can be sent back to
     * the view during the validation of a formula.
     */
    private String[] errorMessages;
    {
        errorMessages = new String[11];
        errorMessages[0] = "Erreur: formule de longueur nulle.";
        errorMessages[1] = "Erreur: la formule commence par un chiffre.";
        errorMessages[2] = "Erreur: ceci n'est pas un atome existant.";
        errorMessages[3] = "Erreur: un multiplicateur doit suivre un élément ou une parenthèse fermante.";
        errorMessages[4] = "Erreur: Un multiplicateur doit être au moins de 2.";
        errorMessages[5] = "Erreur: les zéros en trop ne sont pas permis devant un nombre.";;
        errorMessages[6] = "Erreur: les parenthèses vides sont interdites.";
        errorMessages[7] = "Erreur: Parenthèse fermante sans parenthèse ouvrante.";
        errorMessages[8] = "Erreur: Parenthèse ouvrante sans parenthèse fermante.";
        errorMessages[9] = "Erreur: caractère invalide (y compris une minuscule qui ne suit pas une majuscule).";
        errorMessages[10] = "Erreur: Le multiplicateur est trop gros.";
    }

    /**
     * Constructor for the class ChemicalValidator. This constructor is private
     * to ensure the integrity of the class, since the class is a Singleton.
     */
    private ChemicalValidator()
    {
    }

    /**
     * Public method that returns the Tokens that make up the chemical formula.
     *
     * @return Returns the formulaTokens container which contains the elements
     * found during the validation of the formula.
     *      @see #formulaTokens
     */
    public ArrayList<Token> getFormulaTokens()
    {
        return formulaTokens;
    }

    /**
     * This method contains an algorithm that takes the user's input and verifies
     * whether or not it is a valid formula. This verification is done incrementally,
     * one element at a time. When an element has been determined to be valid, an
     * associated Token is created and stored in the internal formulaTokens TreeMap.
     *      @see Token
     *      @see #formulaTokens
     *
     * @param formula represents the user's input that will be evaluated.
     * @param messageForUser represents the message that will be returned to the view.
     *      @see #errorMessages
     * @return returns true if the formula is valid, else it returns false.
     */
    public boolean validateChemicalFormula(String formula, StringWrapper messageForUser)
    {
        formulaTokens.clear();
        if(formula.equals(null) || formula.equals(""))
        {
            messageForUser.content = errorMessages[0];
            return false;
        }
        if(Character.isDigit(formula.charAt(0)))
        {
            messageForUser.content = errorMessages[1];
            return false;
        }

        int currentStringIdx = 0;
        ArrayList<Integer> nbOfOpeningParentheses = new ArrayList<>();
        ArrayList<Integer> nbOfClosingParentheses = new ArrayList<>();

        while(currentStringIdx < formula.length())
        {
            // Validate if the element exists
            if(Character.isLetter(formula.charAt(currentStringIdx)))
            {
                if(Character.isUpperCase(formula.charAt(currentStringIdx)))
                {
                    // Check whether or not the symbol of the element to validate is 2 characters long.
                    if(currentStringIdx < formula.length() - 1 &&
                            Character.isLetter(formula.charAt(currentStringIdx + 1)) &&
                            Character.isLowerCase(formula.charAt(currentStringIdx + 1)))
                    {
                        if (PeriodicTable.INSTANCE.isSymbol(formula.substring(currentStringIdx, currentStringIdx + 2)))
                        {
                            formulaTokens.add(new Token(
                                    formula.substring(currentStringIdx, currentStringIdx + 2),
                                    TokenType.CHEMICAL_ELEMENT_SYMBOL));
                            currentStringIdx += 2;
                        }
                        else
                        {
                            messageForUser.content = errorMessages[2];
                            return false;
                        }
                    }
                    else if(PeriodicTable.INSTANCE.isSymbol(formula.substring(currentStringIdx, currentStringIdx + 1)))
                    {
                        formulaTokens.add(new Token(
                                formula.substring(currentStringIdx, currentStringIdx + 1),
                                TokenType.CHEMICAL_ELEMENT_SYMBOL));
                        ++currentStringIdx;
                    }
                    else
                    {
                        messageForUser.content = errorMessages[2];
                        return false;
                    }
                }
                else
                {
                    messageForUser.content = errorMessages[9];
                    return false;
                }
            }
            // Validate if the multiplicity is valid
            else if(Character.isDigit(formula.charAt(currentStringIdx)))
            {
                if(formula.charAt(currentStringIdx) == '0')
                {
                    messageForUser.content = errorMessages[5];
                    return false;
                }
                if(formula.charAt(currentStringIdx - 1) == '(')
                {
                    messageForUser.content = errorMessages[3];
                    return false;
                }
                int lastNumericalIdx = currentStringIdx;
                while(lastNumericalIdx < formula.length() - 1
                        && Character.isDigit(formula.charAt(lastNumericalIdx + 1)))
                {
                    ++lastNumericalIdx;
                }
                int value;
                try
                {
                    value = Integer.parseInt(formula.substring(currentStringIdx, lastNumericalIdx + 1));
                }
                catch(NumberFormatException e)
                {
                    messageForUser.content = errorMessages[10];
                    return false;
                }
                if(value >= 2)
                {
                    formulaTokens.add(new Token(
                            formula.substring(currentStringIdx, lastNumericalIdx + 1),
                            TokenType.NUMBER));
                    currentStringIdx += formula.substring(currentStringIdx, lastNumericalIdx + 1).length();
                }
                else
                {
                    messageForUser.content = errorMessages[4];
                    return false;
                }
            }
            // Validate whether or not the non-letter non-digit character is a parentheses.
            else if(formula.charAt(currentStringIdx) == '(')
            {
                if(currentStringIdx < formula.length() - 1
                    && formula.charAt(currentStringIdx + 1) == ')')
                {
                    messageForUser.content = errorMessages[6];
                    return false;
                }
                formulaTokens.add(new Token(
                        "(",
                        TokenType.PARENTHESIS));
                nbOfOpeningParentheses.add(currentStringIdx);
                ++currentStringIdx;
            }
            else if(formula.charAt(currentStringIdx) == ')')
            {
                formulaTokens.add(new Token(
                        ")",
                        TokenType.PARENTHESIS));
                nbOfClosingParentheses.add(currentStringIdx);
                ++currentStringIdx;
            }
            else
            {
                messageForUser.content = errorMessages[9];
                return false;
            }
        }
        if(nbOfClosingParentheses.size() > nbOfOpeningParentheses.size())
        {
            messageForUser.content = errorMessages[7];
            return false;
        }
        if(nbOfClosingParentheses.size() < nbOfOpeningParentheses.size())
        {
            messageForUser.content = errorMessages[8];
            return false;
        }
        for(int i = 0; i < nbOfClosingParentheses.size(); ++i)
        {
            if(nbOfOpeningParentheses.get(i) > nbOfClosingParentheses.get(i))
            {
                messageForUser.content = errorMessages[7];
                return false;
            }
        }
        return true;
    }
}