package com.v41.tp1.viewcontroler;

/**
 * This class allows the program to bypass a problem with immutable Strings
 * in Java. In particular, it allows strings to be passed to a method in a way
 * that simulates referential parameterization.
 */
public class StringWrapper
{
    /**
     * The string that we want to pass as a reference.
     */
    public String content;
}
