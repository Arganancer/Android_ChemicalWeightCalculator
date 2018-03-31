package com.v41.tp1.modele.webbuilder;

import com.v41.tp1.modele.Token;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

/**
 * Created by Streetlamp on 01/03/2018.
 */

/**
 * Class used to construct a formatted color view of a valid formula.
 */
public class WebBuilderColor extends WebBuilder
{
    /**
     * First color used for the content of a table row. This color alternates with tableRowColor2.
     */
    String tableRowColor1 = "#8cc4d8";

    /**
     * Second color used for the content of a table row. This color alternates with tableRowColor1.
     */
    String tableRowColor2 = "#b3e0ef";

    /**
     * Method called when the content of a webView needs to be constructed.
     *
     * @param weight The total weight of the given chemical formula.
     * @param formulaInputTokens The tokens of the given chemical formula.
     * @param composition TreeMap containing one of every chemical element included in the chemical formula.
     * @return returns a string containing the html code to load into a webview.
     */
    @Override
    public String buildWebPage(double weight, ArrayList<Token> formulaInputTokens, TreeMap<String, Double> composition)
    {
        String htmlPage = "";
        htmlPage += buildHeader(weight, formulaInputTokens) + buildBody(composition);
        return htmlPage;
    }

    /**
     * Internal method used to build the header of the web page.
     *
     * @param weight The total weight of the given chemical formula.
     * @param formulaInputTokens The tokens of the given chemical formula.
     * @return Returns a string containing the html code of the constructed header.
     */
    @Override
    protected String buildHeader(double weight, ArrayList<Token> formulaInputTokens)
    {
        String htmlHeader =
            "<header>" +
                "<p>" + formatFormulaColorTheme(formulaInputTokens) + " = " + weight + "</p>" +
            "</header>";
        return htmlHeader;
    }

    /**
     * Internal method used to format a chemical formula. The color of each Token is changed according to its type,
     * and all NUMBER Tokens are styled to be subscript.
     *
     * @param formulaInputTokens The tokens of the given chemical formula.
     * @return Returns a string containing the html code for a formatted formula.
     */
    private String formatFormulaColorTheme( ArrayList<Token> formulaInputTokens)
    {
        String formattedFormula = "";
        int currentIndex = 0;

        while(currentIndex <= formulaInputTokens.size() - 1) {
            Token currentToken = formulaInputTokens.get(currentIndex);
            switch (currentToken.getTokenType())
            {
                case CHEMICAL_ELEMENT_SYMBOL:
                    formattedFormula += "<span style=\"color:rgb(71, 71, 71)\">" + currentToken.getTokenContent() + "</span>";
                    break;
                case NUMBER:
                    formattedFormula += "<small><sub style=\"color:rgb(0, 145, 196)\">" + currentToken.getTokenContent() + "</sub></small>";
                    break;
                case PARENTHESIS:
                    formattedFormula += "<span style=\"color:rgb(175, 196, 204)\">" + currentToken.getTokenContent() + "</span>";
                    break;
            }
            ++currentIndex;
        }
        return formattedFormula;
    }

    /**
     * Internal method used to build the body of the web page.
     *
     * @param composition TreeMap containing one of every chemical element included in the chemical formula.
     * @return Returns a string containing the html code to load into a webview.
     */
    @Override
    protected String buildBody(TreeMap<String, Double> composition)
    {
        String htmlBody = "";
        String tableRowCurrentColor;
        boolean colorSwitch = true;
        htmlBody +=
        "<body>" +
            "<table style=\"width:100%\">" +
                "<tr bgcolor=\"#114759\">" +
                    "<th style=\"width:20%\"><p style=\"color:rgb(167, 215, 232)\">Element</p></th>" +
                    "<th style=\"width:80%\"><p style=\"color:rgb(167, 215, 232)\">Weight</p></th>" +
                "</tr>";

        Set<String> keys = composition.keySet();
        for(String key: keys)
        {
            if(colorSwitch)
            {
                tableRowCurrentColor = tableRowColor1;
            }
            else
            {
                tableRowCurrentColor = tableRowColor2;
            }
            colorSwitch = !colorSwitch;
            htmlBody +=
                "<tr bgcolor=\"" + tableRowCurrentColor  + "\">" +
                    "<td align=\"left\" style=\"color:rgb(74, 101, 112)\">" + key + "</td>" +
                    "<td align=\"left\">" + composition.get(key) + "</td>" +
                "</tr>";
        }
        htmlBody +=
            "</table>" +
        "</body>";
        return htmlBody;
    }
}
