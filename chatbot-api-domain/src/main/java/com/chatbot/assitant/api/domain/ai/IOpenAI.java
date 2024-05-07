package com.chatbot.assitant.api.domain.ai;

import java.io.IOException;

public interface IOpenAI {
    String askForChatGPT(String question) throws IOException;

}
