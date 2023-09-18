package com.example.privacygptcli;

import com.example.privacygptcli.StdInput.StdInput;
import com.example.privacygptcli.StdInput.StdInputs;
import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatCompletionResult;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Chat {
    List<ChatMessage> messages;
    OpenAiService openAiService;
    Scanner scanner;
    
    Chat(List<ChatMessage> messages, OpenAiService openAiService) {
        this.messages = messages;
        this.openAiService = openAiService;
        this.scanner = new Scanner(System.in);
    }

    public static Chat openConnection() {
        List<ChatMessage> messages = new ArrayList<>();
        final String prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever.";
        final ChatMessage promptMessage = new ChatMessage();
        promptMessage.setRole("system");
        promptMessage.setContent(prompt);
        messages.add(promptMessage);

        String apiKey = null;
        Optional<String> optApiKey = Optional.ofNullable(System.getenv("OPENAI_API_KEY"));
        if (optApiKey.isPresent()) {
          apiKey = optApiKey.get();
        } else {
            System.out.println("[ERROR] can not get OPENAI_API_KEY. please, set `OPENAI_API_KEY` in your configuration file, such as .zshrc, .bashrc.");
            return null;
        }

        return new Chat(messages, new OpenAiService(apiKey, Duration.ofSeconds(60)));
    }

    public StdInput getUserInput() {
        return new StdInput(scanner);
    }

    public void displayWelcomeMessage() {
        System.out.println(" 🔒Welcome to the Privacy GPT CLI🔒");
        System.out.println("Here you can have a conversation with OpenAI's GPT model, and prohibit some words from submitting to Open AI API.\n");
        System.out.println("[USAGE]");
        System.out.println("Type your messages and press Enter");
        System.out.println("And type `go` and press Enter to send your messages.\n");
        System.out.println("If you want to exit the chat, just type 'exit' and press Enter.\n");
        System.out.println("Feel free to start the conversation now. \n");
    }
    
    public void displayUserIcon() {
        System.out.print("You👨: ");
    }

    public void displayGptReply(String reply) {
        System.out.println("GPT🤖: " + reply);
    }

    public void sendUserInputsToApi(StdInputs stdInputs) throws Exception {
        String fullMessage = stdInputs.toString();

        final ChatMessage userMessage = new ChatMessage();
        userMessage.setRole("user");
        userMessage.setContent(fullMessage);
        messages.add(userMessage);

        final ChatCompletionRequest request = ChatCompletionRequest.builder()
                .model("gpt-3.5-turbo")
                .messages(messages)
                .maxTokens(2048)
                .build();

        final ChatCompletionResult completionResult = openAiService.createChatCompletion(request);
        final List<ChatCompletionChoice> choiceList = completionResult.getChoices();

        if (choiceList.isEmpty()) throw new Exception("Failed to parse.");

        String reply = choiceList.get(0).getMessage().getContent();
        displayGptReply(reply);

        messages.add(choiceList.get(0).getMessage());
    }

    public void closeConnection() {
        scanner.close();
    }
}
