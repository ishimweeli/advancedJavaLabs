package com.designPatterns.decorator;
public class MessageDecoratorExercise {
    interface Message {
        String send(String text);
    }

    static class SimpleMessage implements Message {
        @Override
        public String send(String text) {
            return text;
        }
    }

    static class EncryptionDecorator implements Message {
        private Message message;

        EncryptionDecorator(Message message) {
            this.message = message;
        }

        @Override
        public String send(String text) {
            String encrypted = encrypt(text);
            return message.send(encrypted);
        }

        private String encrypt(String text) {
            return "ENCRYPTED(" + text + ")";
        }
    }

    public static void main(String[] args) {
        Message baseMessage = new SimpleMessage();
        Message encryptedMessage = new EncryptionDecorator(baseMessage);
        System.out.println(encryptedMessage.send("Hello, World!"));
    }
}