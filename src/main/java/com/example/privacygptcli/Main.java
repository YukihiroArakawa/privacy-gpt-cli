package com.example.privacygptcli;

import com.example.privacygptcli.StdInput.StdInput;
import com.example.privacygptcli.StdInput.StdInputs;

public class Main {
    public static void main(String[] args) throws Exception {
        Chat chat = Chat.openConnection();

        chat.displayWelcomeMessage();

        StdInputs stdInputs = new StdInputs();

        while(true) {
            if (stdInputs.isEmpty()) chat.displayUserIcon();

            StdInput stdInput = chat.getUserInput();

            if (stdInput.hasExitStatus()) break;

            if (stdInput.hasProhibitedWord()) {
                System.out.println("[ERROR] As your message has prohibited words, it stopped submitting it to OpenAi API. ");
                continue;
            }

            if (stdInput.hasGoStatus()) {
                chat.sendUserInputsToApi(stdInputs);
                stdInputs = stdInputs.reset();
            } else {
                stdInputs = stdInputs.appendInput(stdInput);
            }
      }

        chat.closeConnection();
    }
}
