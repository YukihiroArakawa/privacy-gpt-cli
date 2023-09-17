package com.example.privacygptcli.StdInput;

enum InputStatus {
    EXIT("exit"),
    GO("go");

    private final String code;

    InputStatus(String code) {
        this.code = code;
    }

    String getCode() {
        return code;
    }
}
