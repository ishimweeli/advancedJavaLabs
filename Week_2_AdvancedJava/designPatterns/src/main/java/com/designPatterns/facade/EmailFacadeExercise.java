package com.designPatterns.facade;

public class EmailFacadeExercise {
    static class EmailComposer {
        void compose(String content) {
            System.out.println("Composing email: " + content);
        }
    }

    static class EmailSender {
        void send() {
            System.out.println("Sending email");
        }
    }

    static class EmailManager {
        void archive() {
            System.out.println("Archiving email");
        }
    }

    static class EmailFacade {
        private EmailComposer composer;
        private EmailSender sender;
        private EmailManager manager;

        EmailFacade() {
            this.composer = new EmailComposer();
            this.sender = new EmailSender();
            this.manager = new EmailManager();
        }

        void sendEmail(String content) {
            composer.compose(content);
            sender.send();
            manager.archive();
        }
    }

    public static void main(String[] args) {
        EmailFacade facade = new EmailFacade();
        facade.sendEmail("Hello, this is a test email.");
    }
}