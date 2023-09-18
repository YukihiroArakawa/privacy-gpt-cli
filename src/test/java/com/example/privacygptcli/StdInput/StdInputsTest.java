package com.example.privacygptcli.StdInput;

import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StdInputsTest {
    @Test
    public void testIsEmpty() {
        StdInputs inputs = new StdInputs();
        Assert.assertTrue(inputs.isEmpty());

        List<StdInput> inputList = new ArrayList<>();
        inputList.add(new StdInput(new Scanner("input")));
        inputs = new StdInputs(inputList);
        Assert.assertFalse(inputs.isEmpty());
    }

    @Test
    public void testReset() {
        List<StdInput> inputList = new ArrayList<>();
        inputList.add(new StdInput(new Scanner("input")));
        StdInputs inputs = new StdInputs(inputList);

        inputs = inputs.reset();
        Assert.assertTrue(inputs.isEmpty());
    }

    @Test
    public void testAppendInput() {
        List<StdInput> inputList = new ArrayList<>();
        inputList.add(new StdInput(new Scanner("first input")));
        StdInputs inputs = new StdInputs(inputList);

        inputs = inputs.appendInput(new StdInput(new Scanner("second input")));
        Assert.assertEquals("first input\nsecond input", inputs.toString());
    }

    @Test
    public void testToString() {
        List<StdInput> inputList = new ArrayList<>();
        inputList.add(new StdInput(new Scanner("first input")));
        inputList.add(new StdInput(new Scanner("second input")));
        StdInputs inputs = new StdInputs(inputList);

        Assert.assertEquals("first input\nsecond input", inputs.toString());
    }
}