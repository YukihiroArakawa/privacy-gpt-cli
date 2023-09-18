package com.example.privacygptcli.StdInput;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StdInputs {
    private List<StdInput> inputList;

    public StdInputs() {
       this.inputList = Collections.unmodifiableList(new ArrayList<>());
    }

    public StdInputs(List<StdInput> inputList) {
        this.inputList = Collections.unmodifiableList(inputList);
    }

    public boolean isEmpty() {
        return this.inputList == null || this.inputList.size() == 0;
    }

    public StdInputs reset() {
        return new StdInputs();
    }

    public StdInputs appendInput(StdInput input) {
        List<StdInput> list = new ArrayList<>();
        list.addAll(inputList);
        list.add(input);
        return new StdInputs(list);
    }

    @Override
    public String toString() {
        return this.inputList.stream()
                          .map(StdInput::getLine)
                          .collect(Collectors.joining("\n"));
    }
}
