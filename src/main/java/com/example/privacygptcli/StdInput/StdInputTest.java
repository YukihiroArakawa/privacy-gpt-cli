package com.example.privacygptcli.StdInput;

import org.junit.Assert;
import org.junit.Test;

import java.util.Scanner;

public class StdInputTest {
    @Test
    public void testGetLine() {
        String input = "test input";
        Scanner scanner = new Scanner(input);
        StdInput stdInput = new StdInput(scanner);

        Assert.assertEquals(input, stdInput.getLine());
    }

    @Test
    public void testHasExitStatus() {
        String exitCode = "exit";
        Scanner scanner = new Scanner(exitCode);
        StdInput stdInput = new StdInput(scanner);

        Assert.assertTrue(stdInput.hasExitStatus());
    }

    @Test
    public void testHasGoStatus() {
        String goCode = "go";
        Scanner scanner = new Scanner(goCode);
        StdInput stdInput = new StdInput(scanner);

        Assert.assertTrue(stdInput.hasGoStatus());
    }

    @Test
    public void testHasProhibitedWord() {
        String prohibitedWord = "xxx corp";
        Scanner scanner = new Scanner(prohibitedWord);
        StdInput stdInput = new StdInput(scanner);

        Assert.assertTrue(stdInput.hasProhibitedWord());
    }
}