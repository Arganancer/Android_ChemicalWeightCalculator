package com.v41.tp1.modele;

import com.v41.tp1.modele.portal.ModelPortal;
import com.v41.tp1.modele.webbuilder.WebBuilder;
import com.v41.tp1.modele.webbuilder.WebBuilderColor;
import com.v41.tp1.modele.webbuilder.WebBuilderMonochrome;
import com.v41.tp1.viewcontroler.portal.ViewPortal;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * This class represents a chemical formula made up of Tokens. This class does not manage
 * validation, and therefore assumes that given chemical formulas have already been
 * validated. The class calculates the atomic weight of the given chemical formula and
 * creates a composition containing a single copy of the symbol and atomic weight
 * of each type of chemical element present in the molecule.
 *
 * In the MVC pattern, this class embodies the role of the Model.
 */
public class ChemicalComposition implements ModelPortal
{
    /**
     * The total weight of the given chemical formula.
     */
    private double weight;

    /**
     * The formula Tokens that will be calculated.
     */
    private ArrayList<Token> formulaInputTokens = new ArrayList<>();

    /**
     * Composition containing a single copy of the symbol and atomic weight
     * of each type of chemical element present in the molecule.
     */
    private TreeMap<String, Double> composition = new TreeMap<>();

    /**
     * View interface that permits the model to notify the view.
     */
    private ViewPortal viewPortal;

    /**
     * The current webBuilder. Changes when the theme of the app is changed.
     */
    private WebBuilder webBuilder = new WebBuilderColor();

    /**
     * Default constructor whose sole purpose is to allow purely logic based unit tests.
     */
    public ChemicalComposition()
    {
    }

    /**
     * Overloaded constructor that links the view's interface to the model.
     *
     * @param viewPortal The interface allowing the model to interact with the view.
     */
    public ChemicalComposition(ViewPortal viewPortal)
    {
        this.viewPortal = viewPortal;
    }

    /**
     * Initialising method that must be called every time a new chemical formula
     * must be calculated. This method receives a chemical formula composition
     * in the form of Tokens, calls a private method for further operations, then
     * notifies the view that the requested information is ready to be read.
     *
     * @param formulaInputTokens Inputted chemical formula composition in the form
     *                           of Tokens.
     */
    public void initialise(ArrayList<Token> formulaInputTokens)
    {
        weight = 0;
        composition.clear();
        this.formulaInputTokens = formulaInputTokens;
        calculateAtomicWeight();
        if(viewPortal != null)
        {
            viewPortal.notify(this);
        }
    }

    /**
     * Returns the total weight of a chemical formula.
     *
     * @return The total weight of a molecule.
     */
    public double getWeight()
    {
        return weight;
    }

    /**
     * Interface method that allows the view to read the results of the calculations
     * performed by the model.
     *
     * @return Returns a string containing the details of the calculation
     * formatted to be readable by the user.
     */
    @Override
    public String getInformationAboutChemicalComposition()
    {
        return webBuilder.buildWebPage(weight, formulaInputTokens, composition);
    }

    /**
     * Method called when the theme of the app is changed.
     *
     * @param themeId The id of the desired theme.
     */
    public void changeTheme(int themeId)
    {
        switch(themeId)
        {
            case 0:
                webBuilder = new WebBuilderColor();
                break;
            case 1:
                webBuilder = new WebBuilderMonochrome();
                break;
        }
        if(viewPortal != null)
        {
            viewPortal.notify(this);
        }
    }

    /**
     * This method contains the algorithm that calculates the total weight of the
     * given chemical formula. Whenever a chemical element token is encountered, the method
     * checks to see if the element is present in the element composition TreeMap. If it is
     * not, then the encountered element is added.
     *
     * The algorithm functions by working backwards through the formula. This way, the an
     * accurate array multipliers is easily maintained and modified, and is applied to each
     * element before the element is added to the total weight of the molecule.
     */
    private void calculateAtomicWeight()
    {
        ArrayList<Integer> multipliers = new ArrayList<>();
        multipliers.add(1);
        int nbOfMultipliers = 1;
        int currentIndex = formulaInputTokens.size() - 1;

        while(currentIndex >= 0)
        {
            Token currentToken = formulaInputTokens.get(currentIndex);
            // If token is NUMBER
            if(currentToken.getTokenType() == TokenType.NUMBER)
            {
                multipliers.set(nbOfMultipliers - 1, Integer.parseInt(currentToken.getTokenContent()));
            }
            // If token is PARENTHESIS
            else if(currentToken.getTokenType() == TokenType.PARENTHESIS)
            {
                if(currentToken.getTokenContent() == "(")
                {
                    --nbOfMultipliers;
                    multipliers.remove(nbOfMultipliers);
                    multipliers.set(nbOfMultipliers - 1, 1);
                }
                else
                {
                    ++nbOfMultipliers;
                    multipliers.add(1);
                }
            }
            // If token is CHEMICAL_ELEMENT_SYMBOL
            else if(currentToken.getTokenType() == TokenType.CHEMICAL_ELEMENT_SYMBOL)
            {
                double currentElementAtomicWeightTotal = PeriodicTable.INSTANCE.getChemicalElement(currentToken.getTokenContent()).getAtomicWeight();
                // Before multiplying the atomic weight of the element, we check to see if the element is already in the composition.
                if(composition.isEmpty() || !composition.containsKey(currentToken.getTokenContent()))
                {
                    composition.put(currentToken.getTokenContent(), currentElementAtomicWeightTotal);
                }
                for (int i = 0; i < nbOfMultipliers; ++i)
                {
                    currentElementAtomicWeightTotal *= multipliers.get(i);
                }
                weight += currentElementAtomicWeightTotal;
                multipliers.set(nbOfMultipliers - 1, 1);
            }
            --currentIndex;
        }
    }
}