package com.example.privacygptcli;

import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // get OPENAI_API_KEY(environment variable) from your configuration file, such as .zshrc, .bashrc
        final Optional<String> optToken = Optional.ofNullable(System.getenv("OPENAI_API_KEY"));
        if(optToken.isEmpty()){
            System.out.println("[ERROR] can not get OPENAI_API_KEY. please, set `OPENAI_API_KEY` in your configuration file, such as .zshrc, .bashrc.");
            return;
        }
        String token = optToken.get();

        final var service = new OpenAiService(token, Duration.ofSeconds(60));

        // define the foundational prompt.
        final var prompt = "The following is a conversation with an AI assistant. The assistant is helpful, creative, clever.";
        final List<ChatMessage> messages = new ArrayList<>();

        final var promptMessage = new ChatMessage();
        promptMessage.setRole("system");
        promptMessage.setContent(prompt);
        messages.add(promptMessage);

        // receive stdin, and send it to OpenAI API.
        Scanner scanner = new Scanner(System.in);
        while(true) {
            System.out.print("You: ");
            final var message = scanner.nextLine();

            // if the received input is "exit", shutdown this application.
            if ("exit".equalsIgnoreCase(message)) break;

            final var userMessage = new ChatMessage();
            userMessage.setRole("user");
            userMessage.setContent(message);
            messages.add(userMessage);

            final var request = ChatCompletionRequest.builder()
                    .model("gpt-3.5-turbo")
                    .messages(messages)
                    .maxTokens(2048)
                    .build();

            final var completionResult = service.createChatCompletion(request);
            final var choiceList = completionResult.getChoices();

            if (choiceList.isEmpty()) {
                throw new Exception("Failed to parse.");
            }

            var reply = choiceList.get(0).getMessage().getContent();
            System.out.println("AI: " + reply);
            messages.add(choiceList.get(0).getMessage());
      }

      scanner.close();
    }
}
