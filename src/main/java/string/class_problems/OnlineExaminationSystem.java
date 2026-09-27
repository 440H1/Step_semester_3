import java.util.*;

public class OnlineExaminationSystem {

    interface Question {
        boolean checkAnswer(String answer);
    }

    class MultipleChoiceQuestion implements Question {
        String questionText;
        String correctAnswer;

        MultipleChoiceQuestion(String questionText, String correctAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }

        public boolean checkAnswer(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    class TrueFalseQuestion implements Question {
        String questionText;
        String correctAnswer;

        TrueFalseQuestion(String questionText, String correctAnswer) {
            this.questionText = questionText;
            this.correctAnswer = correctAnswer;
        }

        public boolean checkAnswer(String answer) {
            return correctAnswer.equalsIgnoreCase(answer);
        }
    }

    class Examination {
        String examinationName;
        ArrayList<Question> questions = new ArrayList<>();

        Examination(String examinationName) {
            this.examinationName = examinationName;
        }

        void addQuestion(Question question) {
            questions.add(question);
        }
    }

    class Attempt {
        Examination examination;
        HashMap<Integer, String> answers = new HashMap<>();
        boolean submitted = false;

        Attempt(Examination examination) {
            this.examination = examination;
        }

        void answerQuestion(int questionNumber, String answer) {
            if (!submitted) {
                answers.put(questionNumber, answer);
                System.out.println(
                        "Question " + questionNumber +
                                " answered with '" + answer + "'."
                );
            }
        }

        void submit() {
            if (!submitted) {
                submitted = true;

                System.out.println(
                        "Examination '" +
                                examination.examinationName +
                                "' submitted successfully."
                );
            }
        }

        void evaluate() {
            int correct = 0;

            for (Map.Entry<Integer, String> entry : answers.entrySet()) {
                int questionNumber = entry.getKey();
                String answer = entry.getValue();

                Question question =
                        examination.questions.get(questionNumber - 1);

                if (question.checkAnswer(answer)) {
                    correct++;
                }
            }

            System.out.println(
                    "Result for '" +
                            examination.examinationName +
                            "' attempt: " +
                            correct + "/" +
                            examination.questions.size() +
                            " correct"
            );
        }
    }

    class Student {
        String studentName;
        HashMap<String, Attempt> attempts = new HashMap<>();

        Student(String studentName) {
            this.studentName = studentName;
        }

        void startExamination(Examination examination) {

            if (!attempts.containsKey(examination.examinationName)) {

                Attempt attempt =
                        new Attempt(examination);

                attempts.put(
                        examination.examinationName,
                        attempt
                );

                System.out.println(
                        "Examination '" +
                                examination.examinationName +
                                "' started by " +
                                studentName + "."
                );
            }
        }

        Attempt getAttempt(String examinationName) {
            return attempts.get(examinationName);
        }
    }

    public static void main(String[] args) {

        OnlineExaminationSystem system =
                new OnlineExaminationSystem();

        Student student =
                system.new Student("Arjun");

        Examination mathQuiz =
                system.new Examination("Math Quiz");

        mathQuiz.addQuestion(
                system.new MultipleChoiceQuestion(
                        "What is 2 + 2?",
                        "A"
                )
        );

        mathQuiz.addQuestion(
                system.new MultipleChoiceQuestion(
                        "What is 3 + 3?",
                        "B"
                )
        );

        student.startExamination(mathQuiz);

        Attempt mathAttempt =
                student.getAttempt("Math Quiz");

        mathAttempt.answerQuestion(1, "A");
        mathAttempt.answerQuestion(2, "C");

        mathAttempt.submit();

        mathAttempt.evaluate();
    }
}