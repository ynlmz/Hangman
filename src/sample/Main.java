package sample;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws InterruptedException, FileNotFoundException {
        List<String> wordList = GetWordFromFileToList();
        String word = GetRandomWordFromList(wordList);
        Play(word);
    }

    public static void Play(String word) throws InterruptedException {
        int numberFault = 0;
        char[] wordChars = word.toCharArray();
        char[] hiddenWord = SetHiddenWord(wordChars);
        String input;
        System.out.println("*** HANGMAN GAME STARTED ***");
        TimeUnit.SECONDS.sleep(1);

        while (numberFault < 7 && (String.valueOf(hiddenWord).contains("_"))) {
            System.out.println("\n\t" + String.valueOf(hiddenWord) + "  (" + word.length() + " LETTERS)");
            input = GetInputFromUser();
            CheckWordContainsInput(word.toCharArray(), hiddenWord, input.charAt(0));
            if (word.equals(input) || !String.valueOf(hiddenWord).contains("_")) {
                System.out.println("\t" + word);
                System.out.println("CONGRATULATIONS, YOU WON!");
                break;
            } else if (!String.valueOf(hiddenWord).contains(input)) {
                numberFault++;
                if (numberFault < 7) System.out.println("\nWRONG GUESS, PLEASE TRY AGAIN!");
                else System.out.println("\nGAME OVER! WORD WAS : " + word + "\n");
                drawHangman(numberFault);
            }
        }
    }

    public static List<String> GetWordFromFileToList() throws FileNotFoundException {
        Scanner fileScanner = new Scanner(new File("/home/vaio/IdeaProjects/HangMan Game/words.txt"));
        List<String> wordList = new ArrayList<>();
        while (fileScanner.hasNextLine()) {
            wordList.add(fileScanner.nextLine());
        }
        fileScanner.close();
        return wordList;
    }

    public static String GetRandomWordFromList(List<String> list) {
        Random random = new Random();
        int index = random.nextInt(list.size());
        return list.get(index).toUpperCase();
    }

    public static char[] SetHiddenWord(char[] word) {
        char[] hiddenWord = word.clone();
        Arrays.fill(hiddenWord, '_');
        return hiddenWord;
    }

    public static String GetInputFromUser() {
        Scanner inputReader = new Scanner(System.in);
        System.out.print("\nYour Guess : ");
        return inputReader.next().toUpperCase();
    }

    public static void CheckWordContainsInput(char[] word, char[] hiddenWord, char input) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] == input) {
                hiddenWord[i] = input;
            }
        }
    }

    public static void drawHangman(int numb) {
        switch (numb) {
            case 1: {
                System.out.println("|----------");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            }
            case 2: {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            }
            case 3: {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|    |");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            }
            case 4: {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            }
            case 5: {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            }
            case 6: {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|   /");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            }
            case 7: {
                System.out.println("|----------");
                System.out.println("|    O");
                System.out.println("|   -|-");
                System.out.println("|   / \\");
                System.out.println("|");
                System.out.println("|");
                System.out.println("|");
                break;
            }
            default:
                break;
        }
    }
}
