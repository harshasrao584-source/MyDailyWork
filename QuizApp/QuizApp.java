import java.util.Scanner;
import java.util.concurrent.*;

public class QuizApp {

    static class Question {
        String question;
        String[] options;
        int correctAnswer;

        Question(String q, String[] opt, int ans) {
            question = q;
            options = opt;
            correctAnswer = ans;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Question[] quiz = {
            new Question("Which language is used for web development?",
                    new String[]{"1. Python", "2. HTML", "3. C++", "4. Java"}, 2),

            new Question("Which is used for styling web pages?",
                    new String[]{"1. CSS", "2. Java", "3. Python", "4. C"}, 1),

            new Question("Which language is used for web logic?",
                    new String[]{"1. JavaScript", "2. HTML", "3. CSS", "4. SQL"}, 1),

            new Question("Which company developed Java?",
                    new String[]{"1. Microsoft", "2. Sun Microsystems", "3. Google", "4. Apple"}, 2)
        };

        int score = 0;

        System.out.println("===== QUIZ APPLICATION =====");

        for (int i = 0; i < quiz.length; i++) {

            System.out.println("\nQuestion " + (i + 1));
            System.out.println(quiz[i].question);

            for (String opt : quiz[i].options) {
                System.out.println(opt);
            }

            System.out.println("Enter your answer (1-4). You have 10 seconds:");

            ExecutorService executor = Executors.newSingleThreadExecutor();

            Future<Integer> future = executor.submit(() -> {
                return sc.nextInt();
            });

            try {
                int answer = future.get(10, TimeUnit.SECONDS);

                if (answer == quiz[i].correctAnswer) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Wrong!");
                }

            } catch (TimeoutException e) {
                System.out.println("Time's up! Moving to next question.");
                future.cancel(true);
            } catch (Exception e) {
                System.out.println("Invalid input.");
            }

            executor.shutdownNow();
        }

        // Result
        System.out.println("\n===== QUIZ RESULT =====");
        System.out.println("Total Score: " + score + " / " + quiz.length);

        sc.close();
    }
}
