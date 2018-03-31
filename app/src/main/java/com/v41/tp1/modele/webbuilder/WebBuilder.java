package com.v41.tp1.modele.webbuilder;

import com.v41.tp1.modele.Token;

import java.util.ArrayList;
import java.util.TreeMap;

/**
 * Created by Streetlamp on 01/03/2018.
 */

/**
 * Abstract class from which WebBuilderColor and WebBuilderMonochrome are derived.
 * All methods are abstract and are redefined in the subclasses.
 */
public abstract class WebBuilder
{
    /**
     * Method called when the content of a webView needs to be constructed.
     *
     * @param weight The total weight of the given chemical formula.
     * @param formulaInputTokens The tokens of the given chemical formula.
     * @param composition TreeMap containing one of every chemical element included in the chemical formula.
     * @return returns a string containing the html code to load into a webview.
     */
    public abstract String buildWebPage(double weight, ArrayList<Token> formulaInputTokens, TreeMap<String, Double> composition);

    /**
     * Internal method used to build the header of the web page.
     *
     * @param weight The total weight of the given chemical formula.
     * @param formulaInputTokens The tokens of the given chemical formula.
     * @return Returns a string containing the html code of the constructed header.
     */
    protected abstract String buildHeader(double weight, ArrayList<Token> formulaInputTokens);

    /**
     * Internal method used to build the body of the web page.
     *
     * @param composition TreeMap containing one of every chemical element included in the chemical formula.
     * @return Returns a string containing the html code to load into a webview.
     */
    protected abstract String buildBody(TreeMap<String, Double> composition);
}
