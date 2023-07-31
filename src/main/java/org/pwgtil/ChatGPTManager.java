package org.pwgtil;

import com.theokanning.openai.completion.chat.ChatCompletionChoice;
import com.theokanning.openai.completion.chat.ChatCompletionRequest;
import com.theokanning.openai.completion.chat.ChatMessage;
import com.theokanning.openai.service.OpenAiService;

import java.time.Duration;
import java.util.List;

public class ChatGPTManager {
    OpenAiService openAiService;

    public ChatGPTManager() {
        openAiService = new OpenAiService("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", Duration.ofSeconds(60L));
    }

    private String askChatGPT(String question) {

        ChatCompletionRequest chatCompletionRequest = ChatCompletionRequest
                .builder()
                .messages(List.of(new ChatMessage("user", question)))
                .model("gpt-3.5-turbo")
                .build();

        List<ChatCompletionChoice> response = openAiService
                .createChatCompletion(chatCompletionRequest)
                .getChoices();

        StringBuilder stringBuilder = new StringBuilder();
        response.stream()
                .map(ChatCompletionChoice::getMessage)
                .map(ChatMessage::getContent)
                .forEach(stringBuilder::append);

        return stringBuilder.toString();//new StringBuilder().append("ChatGPT propose the following three options:").append(() -> "test")//String.join()
    }

    public String getBreakfastProposal(List<String> productsList) {
        String allProducts = String.join(", ", productsList);
        String question = "I have in my fridge: " + allProducts + ". What can I do with it for breakfast? Propose three ideas";

        return askChatGPT(question);
    }

    public String getDinnerProposal(List<String> productsList) {
        String allProducts = String.join(", ", productsList);
        String question = "I have in my fridge: " + allProducts + ". What can I do with it for dinner? Propose three ideas";
        return askChatGPT(question);
    }

    public String getHealthyProductsRecommendation(List<String> productsList) {
        String allProducts = String.join(", ", productsList);
        String question = "I have in my fridge: " + allProducts + ". What other healthy products can I buy?";
        return askChatGPT(question);
    }
}
