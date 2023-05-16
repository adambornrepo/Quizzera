package com.abtech.utils;

import com.abtech.domain.*;
import com.abtech.dto.QuizRecord;
import com.abtech.enums.Gender;
import com.abtech.enums.QuestionType;
import com.abtech.enums.Role;
import com.abtech.repository.*;
import com.abtech.service.QuizService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class QuizzeraCommandLineRunner implements CommandLineRunner {
    private final FillBlankRepository fillBlankRepository;
    private final MultipleChoiceRepository multipleChoiceRepository;
    private final OpenEndRepository openEndRepository;
    private final QuizService quizService;
    private final QuizUserRepository quizUserRepository;
    private final TopicRepository topicRepository;
    private final TrueFalseRepository trueFalseRepository;
    private final UserInfoRepository userInfoRepository;

    @Override
    public void run(String... args) throws Exception {

        //Users start

        UserInfo userAdmin = new UserInfo();

        userAdmin.setUsername("adamborn");
        userAdmin.setPassword("admin123");
        userAdmin.setEmail("adam@abtech.com");
        userAdmin.setUserRole(Role.ROLE_ADMIN);

        userInfoRepository.save(userAdmin);

        QuizUser quizUserAdmin = new QuizUser();

        quizUserAdmin.setUserInfo(userAdmin);
        quizUserAdmin.setFirstName("Adam");
        quizUserAdmin.setLastName("BORN");
        quizUserAdmin.setBirthdate(LocalDate.of(2000, 1, 1));
        quizUserAdmin.setGender(Gender.MALE);

        quizUserRepository.save(quizUserAdmin);

        UserInfo userModerator = new UserInfo();

        userModerator.setUsername("johndoe");
        userModerator.setPassword("john1234");
        userModerator.setEmail("john@mod.com");
        userModerator.setUserRole(Role.ROLE_MODERATOR);

        userInfoRepository.save(userModerator);

        QuizUser quizUserModerator = new QuizUser();

        quizUserModerator.setUserInfo(userModerator);
        quizUserModerator.setFirstName("John");
        quizUserModerator.setLastName("DOE");
        quizUserModerator.setBirthdate(LocalDate.of(1985, 1, 1));
        quizUserModerator.setGender(Gender.UNSPECIFIED);

        quizUserRepository.save(quizUserModerator);

        UserInfo userQuizzer = new UserInfo();

        userQuizzer.setUsername("janedoe");
        userQuizzer.setPassword("jane1234");
        userQuizzer.setEmail("jane@janes.com");
        userQuizzer.setUserRole(Role.ROLE_QUIZZER);

        userInfoRepository.save(userQuizzer);

        QuizUser quizUserQuizzer = new QuizUser();

        quizUserQuizzer.setUserInfo(userQuizzer);
        quizUserQuizzer.setFirstName("Jane");
        quizUserQuizzer.setLastName("DOE");
        quizUserQuizzer.setBirthdate(LocalDate.of(2009, 1, 1));
        quizUserQuizzer.setGender(Gender.FEMALE);

        quizUserRepository.save(quizUserQuizzer);

        //Users end

        //Topic start
        Topic topic1 = new Topic();
        topic1.setName("Science");
        topicRepository.save(topic1);
        Topic topic2 = new Topic();
        topic2.setName("History");
        topicRepository.save(topic2);
        Topic topic3 = new Topic();
        topic3.setName("Geography");
        topicRepository.save(topic3);
        Topic topic4 = new Topic();
        topic4.setName("Mathematics");
        topicRepository.save(topic4);
        Topic topic5 = new Topic();
        topic5.setName("Literature");
        topicRepository.save(topic5);
        Topic topic6 = new Topic();
        topic6.setName("Art");
        topicRepository.save(topic6);
        Topic topic7 = new Topic();
        topic7.setName("Music");
        topicRepository.save(topic7);
        Topic topic8 = new Topic();
        topic8.setName("Sports");
        topicRepository.save(topic8);
        Topic topic9 = new Topic();
        topic9.setName("Technology");
        topicRepository.save(topic9);
        Topic topic10 = new Topic();
        topic10.setName("Languages");
        topicRepository.save(topic10);
        Topic topic11 = new Topic();
        topic11.setName("Chemistry");
        topicRepository.save(topic11);
        //Topic end

        //True-False Q Start
        TrueFalse trueFalse1 = new TrueFalse();
        trueFalse1.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse1.setQuestionText("Is 101 a prime number?");
        trueFalse1.setScore(5);
        trueFalse1.setAnswer(true);
        trueFalseRepository.save(trueFalse1);

        TrueFalse trueFalse2 = new TrueFalse();
        trueFalse2.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse2.setQuestionText("Is the sun a planet?");
        trueFalse2.setScore(5);
        trueFalse2.setAnswer(false);
        trueFalseRepository.save(trueFalse2);

        TrueFalse trueFalse3 = new TrueFalse();
        trueFalse3.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse3.setQuestionText("Is water a solid at room temperature?");
        trueFalse3.setScore(5);
        trueFalse3.setAnswer(false);
        trueFalseRepository.save(trueFalse3);

        TrueFalse trueFalse4 = new TrueFalse();
        trueFalse4.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse4.setQuestionText("Is the Earth flat?");
        trueFalse4.setScore(5);
        trueFalse4.setAnswer(false);
        trueFalseRepository.save(trueFalse4);

        TrueFalse trueFalse5 = new TrueFalse();
        trueFalse5.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse5.setQuestionText("Is the sky blue?");
        trueFalse5.setScore(5);
        trueFalse5.setAnswer(true);
        trueFalseRepository.save(trueFalse5);

        TrueFalse trueFalse6 = new TrueFalse();
        trueFalse6.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse6.setQuestionText("Is gravity a force?");
        trueFalse6.setScore(5);
        trueFalse6.setAnswer(true);
        trueFalseRepository.save(trueFalse6);

        TrueFalse trueFalse7 = new TrueFalse();
        trueFalse7.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse7.setQuestionText("Is the moon made of cheese?");
        trueFalse7.setScore(1);
        trueFalse7.setAnswer(false);
        trueFalseRepository.save(trueFalse7);

        TrueFalse trueFalse8 = new TrueFalse();
        trueFalse8.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse8.setQuestionText("Is 1 a prime number?");
        trueFalse8.setScore(5);
        trueFalse8.setAnswer(false);
        trueFalseRepository.save(trueFalse8);

        TrueFalse trueFalse9 = new TrueFalse();
        trueFalse9.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse9.setQuestionText("Is the Atlantic Ocean the largest ocean?");
        trueFalse9.setScore(5);
        trueFalse9.setAnswer(false);
        trueFalseRepository.save(trueFalse9);

        TrueFalse trueFalse10 = new TrueFalse();
        trueFalse10.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse10.setQuestionText("Is lightning hotter than the sun?");
        trueFalse10.setScore(5);
        trueFalse10.setAnswer(true);
        trueFalseRepository.save(trueFalse10);

        TrueFalse trueFalse11 = new TrueFalse();
        trueFalse11.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse11.setQuestionText("Is the Statue of Liberty located in Paris?");
        trueFalse11.setScore(5);
        trueFalse11.setAnswer(false);
        trueFalseRepository.save(trueFalse11);

        TrueFalse trueFalse12 = new TrueFalse();
        trueFalse12.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse12.setQuestionText("Is 2+2=4?");
        trueFalse12.setScore(2);
        trueFalse12.setAnswer(true);
        trueFalseRepository.save(trueFalse12);

        TrueFalse trueFalse13 = new TrueFalse();
        trueFalse13.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse13.setQuestionText("Is snow white?");
        trueFalse13.setScore(5);
        trueFalse13.setAnswer(true);
        trueFalseRepository.save(trueFalse13);

        TrueFalse trueFalse14 = new TrueFalse();
        trueFalse14.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse14.setQuestionText("Is the speed of light constant?");
        trueFalse14.setScore(5);
        trueFalse14.setAnswer(true);
        trueFalseRepository.save(trueFalse14);

        TrueFalse trueFalse15 = new TrueFalse();
        trueFalse15.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse15.setQuestionText("Is the human brain a muscle?");
        trueFalse15.setScore(5);
        trueFalse15.setAnswer(false);
        trueFalseRepository.save(trueFalse15);

        TrueFalse trueFalse16 = new TrueFalse();
        trueFalse16.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse16.setQuestionText("Is the Amazon River the longest river?");
        trueFalse16.setScore(5);
        trueFalse16.setAnswer(true);
        trueFalseRepository.save(trueFalse16);

        TrueFalse trueFalse17 = new TrueFalse();
        trueFalse17.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse17.setQuestionText("Is the Earth the third planet from the sun?");
        trueFalse17.setScore(5);
        trueFalse17.setAnswer(true);
        trueFalseRepository.save(trueFalse17);

        TrueFalse trueFalse18 = new TrueFalse();
        trueFalse18.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse18.setQuestionText("Is the Mona Lisa a sculpture?");
        trueFalse18.setScore(5);
        trueFalse18.setAnswer(false);
        trueFalseRepository.save(trueFalse18);

        TrueFalse trueFalse19 = new TrueFalse();
        trueFalse19.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse19.setQuestionText("Is the Pacific Ocean the largest ocean?");
        trueFalse19.setScore(5);
        trueFalse19.setAnswer(true);
        trueFalseRepository.save(trueFalse19);

        TrueFalse trueFalse20 = new TrueFalse();
        trueFalse20.setQuestionType(QuestionType.TRUE_FALSE);
        trueFalse20.setQuestionText("Is the Great Wall of China in China?");
        trueFalse20.setScore(5);
        trueFalse20.setAnswer(true);
        trueFalseRepository.save(trueFalse20);
        //True-False Q End

        //MultipleChoice Q Start
        MultipleChoice multipleChoice1 = new MultipleChoice();
        multipleChoice1.setQuestionText("What is the capital of France?");
        multipleChoice1.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice1.setScore(10);
        multipleChoice1.setChcA("London");
        multipleChoice1.setChcB("Rome");
        multipleChoice1.setChcC("Paris");
        multipleChoice1.setChcD("Berlin");
        multipleChoice1.setChcE("Istanbul");
        multipleChoice1.setAnswer('C');
        multipleChoiceRepository.save(multipleChoice1);

        MultipleChoice multipleChoice2 = new MultipleChoice();
        multipleChoice2.setQuestionText("What is the largest planet in our solar system?");
        multipleChoice2.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice2.setScore(8);
        multipleChoice2.setChcA("Mars");
        multipleChoice2.setChcB("Saturn");
        multipleChoice2.setChcC("Jupiter");
        multipleChoice2.setChcD("Earth");
        multipleChoice2.setChcE("Venus");
        multipleChoice2.setAnswer('C');
        multipleChoiceRepository.save(multipleChoice2);

        MultipleChoice multipleChoice3 = new MultipleChoice();
        multipleChoice3.setQuestionText("Who painted the Mona Lisa?");
        multipleChoice3.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice3.setScore(12);
        multipleChoice3.setChcA("Pablo Picasso");
        multipleChoice3.setChcB("Leonardo da Vinci");
        multipleChoice3.setChcC("Vincent van Gogh");
        multipleChoice3.setChcD("Michelangelo");
        multipleChoice3.setChcE("Rembrandt");
        multipleChoice3.setAnswer('B');
        multipleChoiceRepository.save(multipleChoice3);

        MultipleChoice multipleChoice4 = new MultipleChoice();
        multipleChoice4.setQuestionText("What is the chemical symbol for gold?");
        multipleChoice4.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice4.setScore(9);
        multipleChoice4.setChcA("Ag");
        multipleChoice4.setChcB("Au");
        multipleChoice4.setChcC("Pt");
        multipleChoice4.setChcD("Fe");
        multipleChoice4.setChcE("Cu");
        multipleChoice4.setAnswer('B');
        multipleChoiceRepository.save(multipleChoice4);

        MultipleChoice multipleChoice5 = new MultipleChoice();
        multipleChoice5.setQuestionText("Who wrote the novel 'Pride and Prejudice'?");
        multipleChoice5.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice5.setScore(11);
        multipleChoice5.setChcA("Jane Austen");
        multipleChoice5.setChcB("Charlotte Brontë");
        multipleChoice5.setChcC("Emily Dickinson");
        multipleChoice5.setChcD("Virginia Woolf");
        multipleChoice5.setChcE("Harper Lee");
        multipleChoice5.setAnswer('A');
        multipleChoiceRepository.save(multipleChoice5);

        MultipleChoice multipleChoice6 = new MultipleChoice();
        multipleChoice6.setQuestionText("What is the capital of Japan?");
        multipleChoice6.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice6.setScore(10);
        multipleChoice6.setChcA("Tokyo");
        multipleChoice6.setChcB("Beijing");
        multipleChoice6.setChcC("Seoul");
        multipleChoice6.setChcD("Bangkok");
        multipleChoice6.setChcE("New Delhi");
        multipleChoice6.setAnswer('A');
        multipleChoiceRepository.save(multipleChoice6);

        MultipleChoice multipleChoice7 = new MultipleChoice();
        multipleChoice7.setQuestionText("Who is the author of the Harry Potter book series?");
        multipleChoice7.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice7.setScore(12);
        multipleChoice7.setChcA("J.R.R. Tolkien");
        multipleChoice7.setChcB("J.K. Rowling");
        multipleChoice7.setChcC("George R.R. Martin");
        multipleChoice7.setChcD("Stephen King");
        multipleChoice7.setChcE("C.S. Lewis");
        multipleChoice7.setAnswer('B');
        multipleChoiceRepository.save(multipleChoice7);

        MultipleChoice multipleChoice8 = new MultipleChoice();
        multipleChoice8.setQuestionText("What is the largest ocean in the world?");
        multipleChoice8.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice8.setScore(9);
        multipleChoice8.setChcA("Indian Ocean");
        multipleChoice8.setChcB("Arctic Ocean");
        multipleChoice8.setChcC("Atlantic Ocean");
        multipleChoice8.setChcD("Southern Ocean");
        multipleChoice8.setChcE("Pacific Ocean");
        multipleChoice8.setAnswer('E');
        multipleChoiceRepository.save(multipleChoice8);

        MultipleChoice multipleChoice9 = new MultipleChoice();
        multipleChoice9.setQuestionText("Who invented the telephone?");
        multipleChoice9.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice9.setScore(11);
        multipleChoice9.setChcA("Thomas Edison");
        multipleChoice9.setChcB("Alexander Graham Bell");
        multipleChoice9.setChcC("Nikola Tesla");
        multipleChoice9.setChcD("Albert Einstein");
        multipleChoice9.setChcE("Isaac Newton");
        multipleChoice9.setAnswer('B');
        multipleChoiceRepository.save(multipleChoice9);

        MultipleChoice multipleChoice10 = new MultipleChoice();
        multipleChoice10.setQuestionText("What is the largest organ in the human body?");
        multipleChoice10.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice10.setScore(8);
        multipleChoice10.setChcA("Brain");
        multipleChoice10.setChcB("Heart");
        multipleChoice10.setChcC("Liver");
        multipleChoice10.setChcD("Lungs");
        multipleChoice10.setChcE("Skin");
        multipleChoice10.setAnswer('E');
        multipleChoiceRepository.save(multipleChoice10);

        MultipleChoice multipleChoice11 = new MultipleChoice();
        multipleChoice11.setQuestionText("Who painted the famous artwork 'The Starry Night'?");
        multipleChoice11.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice11.setScore(10);
        multipleChoice11.setChcA("Pablo Picasso");
        multipleChoice11.setChcB("Vincent van Gogh");
        multipleChoice11.setChcC("Claude Monet");
        multipleChoice11.setChcD("Salvador Dalí");
        multipleChoice11.setChcE("Michelangelo");
        multipleChoice11.setAnswer('B');
        multipleChoiceRepository.save(multipleChoice11);

        MultipleChoice multipleChoice12 = new MultipleChoice();
        multipleChoice12.setQuestionText("What is the symbol for the chemical element oxygen?");
        multipleChoice12.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice12.setScore(9);
        multipleChoice12.setChcA("Ox");
        multipleChoice12.setChcB("Oxg");
        multipleChoice12.setChcC("O");
        multipleChoice12.setChcD("Oh");
        multipleChoice12.setChcE("On");
        multipleChoice12.setAnswer('C');
        multipleChoiceRepository.save(multipleChoice12);

        MultipleChoice multipleChoice13 = new MultipleChoice();
        multipleChoice13.setQuestionText("Who is the founder of Microsoft?");
        multipleChoice13.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice13.setScore(12);
        multipleChoice13.setChcA("Steve Jobs");
        multipleChoice13.setChcB("Bill Gates");
        multipleChoice13.setChcC("Mark Zuckerberg");
        multipleChoice13.setChcD("Larry Page");
        multipleChoice13.setChcE("Elon Musk");
        multipleChoice13.setAnswer('B');
        multipleChoiceRepository.save(multipleChoice13);

        MultipleChoice multipleChoice14 = new MultipleChoice();
        multipleChoice14.setQuestionText("Which planet is known as the 'Red Planet'?");
        multipleChoice14.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice14.setScore(11);
        multipleChoice14.setChcA("Mars");
        multipleChoice14.setChcB("Jupiter");
        multipleChoice14.setChcC("Saturn");
        multipleChoice14.setChcD("Venus");
        multipleChoice14.setChcE("Mercury");
        multipleChoice14.setAnswer('A');
        multipleChoiceRepository.save(multipleChoice14);

        MultipleChoice multipleChoice15 = new MultipleChoice();
        multipleChoice15.setQuestionText("What is the currency of Japan?");
        multipleChoice15.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice15.setScore(8);
        multipleChoice15.setChcA("Pound");
        multipleChoice15.setChcB("Yen");
        multipleChoice15.setChcC("Euro");
        multipleChoice15.setChcD("Dollar");
        multipleChoice15.setChcE("Rupee");
        multipleChoice15.setAnswer('B');
        multipleChoiceRepository.save(multipleChoice15);

        MultipleChoice multipleChoice16 = new MultipleChoice();
        multipleChoice16.setQuestionText("What is the largest desert in the world?");
        multipleChoice16.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice16.setScore(10);
        multipleChoice16.setChcA("Sahara Desert");
        multipleChoice16.setChcB("Gobi Desert");
        multipleChoice16.setChcC("Atacama Desert");
        multipleChoice16.setChcD("Arabian Desert");
        multipleChoice16.setChcE("Antarctic Desert");
        multipleChoice16.setAnswer('A');
        multipleChoiceRepository.save(multipleChoice16);

        MultipleChoice multipleChoice17 = new MultipleChoice();
        multipleChoice17.setQuestionText("Who wrote the play 'Romeo and Juliet'?");
        multipleChoice17.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice17.setScore(12);
        multipleChoice17.setChcA("William Shakespeare");
        multipleChoice17.setChcB("Arthur Miller");
        multipleChoice17.setChcC("Anton Chekhov");
        multipleChoice17.setChcD("George Bernard Shaw");
        multipleChoice17.setChcE("Oscar Wilde");
        multipleChoice17.setAnswer('A');
        multipleChoiceRepository.save(multipleChoice17);

        MultipleChoice multipleChoice18 = new MultipleChoice();
        multipleChoice18.setQuestionText("What is the chemical formula for water?");
        multipleChoice18.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice18.setScore(9);
        multipleChoice18.setChcA("H");
        multipleChoice18.setChcB("HO");
        multipleChoice18.setChcC("OH");
        multipleChoice18.setChcD("H2O");
        multipleChoice18.setChcE("O2");
        multipleChoice18.setAnswer('D');
        multipleChoiceRepository.save(multipleChoice18);

        MultipleChoice multipleChoice19 = new MultipleChoice();
        multipleChoice19.setQuestionText("Who is the current President of the United States?");
        multipleChoice19.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice19.setScore(11);
        multipleChoice19.setChcA("Donald Trump");
        multipleChoice19.setChcB("Barack Obama");
        multipleChoice19.setChcC("Joe Biden");
        multipleChoice19.setChcD("George W. Bush");
        multipleChoice19.setChcE("Bill Clinton");
        multipleChoice19.setAnswer('C');
        multipleChoiceRepository.save(multipleChoice19);

        MultipleChoice multipleChoice20 = new MultipleChoice();
        multipleChoice20.setQuestionText("What is the largest continent in the world?");
        multipleChoice20.setQuestionType(QuestionType.MULTIPLE_CHOICE);
        multipleChoice20.setScore(8);
        multipleChoice20.setChcA("Africa");
        multipleChoice20.setChcB("Europe");
        multipleChoice20.setChcC("North America");
        multipleChoice20.setChcD("Asia");
        multipleChoice20.setChcE("South America");
        multipleChoice20.setAnswer('D');
        multipleChoiceRepository.save(multipleChoice20);
        //MultipleChoice Q End

        //FillBlank Q Start
        FillBlank fillBlank1 = new FillBlank();
        fillBlank1.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank1.setQuestionText("The capital of France is ___.");
        fillBlank1.setScore(10);
        fillBlank1.setAnswerText("Paris");
        fillBlankRepository.save(fillBlank1);

        FillBlank fillBlank2 = new FillBlank();
        fillBlank2.setQuestionText("The largest ocean in the world is the ___.");
        fillBlank2.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank2.setScore(12);
        fillBlank2.setAnswerText("Pacific Ocean");
        fillBlankRepository.save(fillBlank2);

        FillBlank fillBlank3 = new FillBlank();
        fillBlank3.setQuestionText("The chemical symbol for oxygen is ___.");
        fillBlank3.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank3.setScore(9);
        fillBlank3.setAnswerText("O");
        fillBlankRepository.save(fillBlank3);

        FillBlank fillBlank4 = new FillBlank();
        fillBlank4.setQuestionText("The first President of the United States was ___.");
        fillBlank4.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank4.setScore(11);
        fillBlank4.setAnswerText("George Washington");
        fillBlankRepository.save(fillBlank4);

        FillBlank fillBlank5 = new FillBlank();
        fillBlank5.setQuestionText("The largest desert in the world is the ___.");
        fillBlank5.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank5.setScore(8);
        fillBlank5.setAnswerText("Sahara Desert");
        fillBlankRepository.save(fillBlank5);

        FillBlank fillBlank6 = new FillBlank();
        fillBlank6.setQuestionText("The famous scientist who developed the theory of relativity is ___.");
        fillBlank6.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank6.setScore(10);
        fillBlank6.setAnswerText("Albert Einstein");
        fillBlankRepository.save(fillBlank6);

        FillBlank fillBlank7 = new FillBlank();
        fillBlank7.setQuestionText("The process of converting a high-level programming language code into machine code is called ___.");
        fillBlank7.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank7.setScore(10);
        fillBlank7.setAnswerText("compilation");
        fillBlankRepository.save(fillBlank7);

        FillBlank fillBlank8 = new FillBlank();
        fillBlank8.setQuestionText("The largest planet in our solar system is ___.");
        fillBlank8.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank8.setScore(12);
        fillBlank8.setAnswerText("Jupiter");
        fillBlankRepository.save(fillBlank8);

        FillBlank fillBlank9 = new FillBlank();
        fillBlank9.setQuestionText("The famous speech 'I Have a Dream' was delivered by ___.");
        fillBlank9.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank9.setScore(9);
        fillBlank9.setAnswerText("Martin Luther King Jr.");
        fillBlankRepository.save(fillBlank9);

        FillBlank fillBlank10 = new FillBlank();
        fillBlank10.setQuestionText("The currency of Germany is ___.");
        fillBlank10.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank10.setScore(11);
        fillBlank10.setAnswerText("Euro");
        fillBlankRepository.save(fillBlank10);

        FillBlank fillBlank11 = new FillBlank();
        fillBlank11.setQuestionText("The chemical symbol for gold is ___.");
        fillBlank11.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank11.setScore(8);
        fillBlank11.setAnswerText("Au");
        fillBlankRepository.save(fillBlank11);

        FillBlank fillBlank12 = new FillBlank();
        fillBlank12.setQuestionText("The process of photosynthesis occurs in the ___.");
        fillBlank12.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank12.setScore(10);
        fillBlank12.setAnswerText("chloroplasts");
        fillBlankRepository.save(fillBlank12);

        FillBlank fillBlank13 = new FillBlank();
        fillBlank13.setQuestionText("The famous novel 'To Kill a Mockingbird' was written by ___.");
        fillBlank13.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank13.setScore(12);
        fillBlank13.setAnswerText("Harper Lee");
        fillBlankRepository.save(fillBlank13);

        FillBlank fillBlank14 = new FillBlank();
        fillBlank14.setQuestionText("The unit of electric current is ___.");
        fillBlank14.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank14.setScore(9);
        fillBlank14.setAnswerText("ampere");
        fillBlankRepository.save(fillBlank14);

        FillBlank fillBlank15 = new FillBlank();
        fillBlank15.setQuestionText("The famous painting 'The Last Supper' was created by ___.");
        fillBlank15.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank15.setScore(11);
        fillBlank15.setAnswerText("Leonardo da Vinci");
        fillBlankRepository.save(fillBlank15);

        FillBlank fillBlank16 = new FillBlank();
        fillBlank16.setQuestionText("The chemical formula for table salt is ___.");
        fillBlank16.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank16.setScore(8);
        fillBlank16.setAnswerText("NaCl");
        fillBlankRepository.save(fillBlank16);

        FillBlank fillBlank17 = new FillBlank();
        fillBlank17.setQuestionText("The capital city of Australia is ___.");
        fillBlank17.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank17.setScore(10);
        fillBlank17.setAnswerText("Canberra");
        fillBlankRepository.save(fillBlank17);

        FillBlank fillBlank18 = new FillBlank();
        fillBlank18.setQuestionText("The largest species of penguin is the ___.");
        fillBlank18.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank18.setScore(12);
        fillBlank18.setAnswerText("Emperor Penguin");
        fillBlankRepository.save(fillBlank18);

        FillBlank fillBlank19 = new FillBlank();
        fillBlank19.setQuestionText("The Declaration of Independence was adopted on ___. (year)");
        fillBlank19.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank19.setScore(10);
        fillBlank19.setAnswerText("1776");
        fillBlankRepository.save(fillBlank19);

        FillBlank fillBlank20 = new FillBlank();
        fillBlank20.setQuestionText("The chemical symbol for silver is ___.");
        fillBlank20.setQuestionType(QuestionType.FILL_BLANK);
        fillBlank20.setScore(11);
        fillBlank20.setAnswerText("Ag");
        fillBlankRepository.save(fillBlank20);
        //FillBlank Q End

        //OpenEnd Q Start
        OpenEnd openEnd1 = new OpenEnd();
        openEnd1.setQuestionType(QuestionType.OPEN_END);
        openEnd1.setQuestionText("Why is environmental conservation important to you? Explain.");
        openEnd1.setScore(30);
        openEndRepository.save(openEnd1);

        OpenEnd openEnd2 = new OpenEnd();
        openEnd2.setQuestionType(QuestionType.OPEN_END);
        openEnd2.setQuestionText("What are the qualities of an ideal leader? Explain.");
        openEnd2.setScore(30);
        openEndRepository.save(openEnd2);

        OpenEnd openEnd3 = new OpenEnd();
        openEnd3.setQuestionType(QuestionType.OPEN_END);
        openEnd3.setQuestionText("How do you express yourself the most? Why?");
        openEnd3.setScore(30);
        openEndRepository.save(openEnd3);

        OpenEnd openEnd4 = new OpenEnd();
        openEnd4.setQuestionType(QuestionType.OPEN_END);
        openEnd4.setQuestionText("What are your thoughts on the impact of technology on our daily lives? Explain.");
        openEnd4.setScore(30);
        openEndRepository.save(openEnd4);

        OpenEnd openEnd5 = new OpenEnd();
        openEnd5.setQuestionType(QuestionType.OPEN_END);
        openEnd5.setQuestionText("Which book deeply influenced you and why? Explain.");
        openEnd5.setScore(30);
        openEndRepository.save(openEnd5);

        OpenEnd openEnd6 = new OpenEnd();
        openEnd6.setQuestionType(QuestionType.OPEN_END);
        openEnd6.setQuestionText("What is your vision of a perfect world? Describe it in detail.");
        openEnd6.setScore(30);
        openEndRepository.save(openEnd6);

        OpenEnd openEnd7 = new OpenEnd();
        openEnd7.setQuestionType(QuestionType.OPEN_END);
        openEnd7.setQuestionText("How do you define success in your life? Explain.");
        openEnd7.setScore(30);
        openEndRepository.save(openEnd7);

        OpenEnd openEnd8 = new OpenEnd();
        openEnd8.setQuestionType(QuestionType.OPEN_END);
        openEnd8.setQuestionText("What is the most challenging obstacle you have faced, and how did you overcome it?");
        openEnd8.setScore(30);
        openEndRepository.save(openEnd8);

        OpenEnd openEnd9 = new OpenEnd();
        openEnd9.setQuestionType(QuestionType.OPEN_END);
        openEnd9.setQuestionText("If you could have a conversation with any historical figure, who would it be and why?");
        openEnd9.setScore(30);
        openEndRepository.save(openEnd9);

        OpenEnd openEnd10 = new OpenEnd();
        openEnd10.setQuestionType(QuestionType.OPEN_END);
        openEnd10.setQuestionText("Describe a time when you had to make a difficult decision.");
        openEnd10.setScore(30);
        openEndRepository.save(openEnd10);
        //OpenEnd Q End

        //Quiz Start
        QuizRecord quizRecord1 = new QuizRecord("For beginners","Good choice to start your first quiz", LocalTime.of(0,20,0), Arrays.asList(6L,10L), Arrays.asList(1L,2L,3L,6L,13L), Arrays.asList(1L,2L,4L,5L,17L), Arrays.asList(1L,2L,5L,12L,13L),1,2L);
        quizService.createQuiz(quizRecord1);

        QuizRecord quizRecord2 = new QuizRecord("Chemistry start","Entry level chemistry quiz", LocalTime.of(0,35,0), Arrays.asList(1L,3L), Arrays.asList(4L,8L,9L,12L,18L), Arrays.asList(3L,6L,11L,14L,20L), Arrays.asList(3L,4L,6L,10L,14L),11,2L);
        quizService.createQuiz(quizRecord2);
        //Quiz End



    }
}
