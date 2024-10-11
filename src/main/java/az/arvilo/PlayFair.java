package az.arvilo;

import az.arvilo.exception.EdgeCaseException;

import java.util.Arrays;
import java.util.stream.Collectors;

public class PlayFair {

    private String[][] matrix;
    private String key;
    private static final String[] alphabet = {"A", "B", "C", "D", "E",
            "F", "G", "H", "I", "K", "L", "M", "N", "O",
            "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private String[] additionalAlphabet;

    public PlayFair() {
        matrix = new String[5][5];
    }

    public void setKey(String key) {
        this.key = key.toUpperCase().replace('J', 'I')
                .chars()
                .filter(Character::isLetter)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());;
    }

    private boolean doesLetterExist(String letter) {
        return Arrays.stream(matrix).anyMatch(row ->
                Arrays.stream(row).anyMatch(column -> letter.equals(column))
        );
    }

    public void prepareAdditionalAlphabet() {
        String[] additionalAlphabet = new String[key.length() + alphabet.length];

        for (int i = 0; i < additionalAlphabet.length; i++) {
            if (i < key.length())
                additionalAlphabet[i] = key.substring(i, i + 1);
            else
                additionalAlphabet[i] = alphabet[i - key.length()];
        }

        this.additionalAlphabet = additionalAlphabet;
    }

    public void prepareMatrix() {
        int i = 0;
        int j = 0;

        for (String letter : additionalAlphabet) {
            if (!doesLetterExist(letter)) {
                matrix[i][j] = letter;
                if (j == 4) {
                    i++;
                    j = 0;
                } else {
                    j++;
                }
            }
        }
    }

    public int[] getCoordinates(String letter) {
        int i = 0;
        int j = 0;
        for (String[] array : matrix) {
            for (String currentLetter : array) {
                if (currentLetter.equals(letter)) {
                    int[] rtr = new int[2];
                    rtr[0] = i;
                    rtr[1] = j;
                    return rtr;
                }
                j++;
            }
            j = 0;
            i++;
        }
        return null;
    }

    public String encodeSameRow(int[][] coordinates) {
        return matrix[coordinates[0][0]][coordinates[0][1] == 4 ? 0 : coordinates[0][1] + 1] +
                matrix[coordinates[1][0]][coordinates[1][1] == 4 ? 0 : coordinates[1][1] + 1];
    }

    public String encodeSameColumn(int[][] coordinates) {
        return matrix[coordinates[0][0] == 4 ? 0 : coordinates[0][0] + 1][(coordinates[0][1])] +
                matrix[coordinates[1][0] == 4 ? 0 : coordinates[1][0] + 1][(coordinates[1][1])];
    }

    public String encodeCross(int[][] coordinates) {
        return matrix[coordinates[0][0]][coordinates[1][1]] +
                matrix[coordinates[1][0]][coordinates[0][1]];
    }

    public String encodePart(String part) {
        if (part.length() == 1)
            part += "X";
        String first = part.substring(0, 1);
        String last = part.substring(1, 2);
        if (getCoordinates(first)[0] == getCoordinates(last)[0]) {
            int[][] coordinates = new int[2][2];
            coordinates[0] = getCoordinates(first);
            coordinates[1] = getCoordinates(last);
            return encodeSameRow(coordinates);
        } else if (getCoordinates(first)[1] == getCoordinates(last)[1]) {
            int[][] coordinates = new int[2][2];
            coordinates[0] = getCoordinates(first);
            coordinates[1] = getCoordinates(last);
            return encodeSameColumn(coordinates);
        } else {
            int[][] coordinates = new int[2][2];
            coordinates[0] = getCoordinates(first);
            coordinates[1] = getCoordinates(last);
            return encodeCross(coordinates);
        }
    }

    public String[] getParts(String word) throws EdgeCaseException {
//    public String[] getParts(String word)  {
        for (int i = 0; i < word.length() - 1; i += 2) {
            String subs = word.substring(i, i + 2);
            if (subs.equals("XX"))
                throw new EdgeCaseException();

            if (subs.substring(0, 1).equals(subs.substring(1, 2))) {
                return getParts(word.substring(0, i + 1) + "X" + word.substring(i + 1, word.length()));
            }
        }
        String[] rtr = new String[(int) Math.ceil(word.length() / 2.0)];
        for (int i = 0; i < rtr.length; i++) {
            rtr[i] = word.substring(i * 2, i * 2 + 2 > word.length() ? i * 2 + 1 : i * 2 + 2);
        }

        return rtr;
    }

    public String encode(String text) throws EdgeCaseException {
        prepareAdditionalAlphabet();
        prepareMatrix();

        text = text.toUpperCase();
        text = text.replace('J', 'I');
        text = text.chars()
                .filter(Character::isLetter)
                .mapToObj(c -> String.valueOf((char) c))
                .collect(Collectors.joining());
        String code = "";
        String[] parts = getParts(text);
        for (String part : parts) {
            code += encodePart(part);
        }

        return code;
    }

    public void showMatrix() {
        Arrays.stream(matrix).forEach(array -> {
            System.out.println();
            Arrays.stream(array).forEach(letter -> {
                System.out.print(letter);
                System.out.print(" ");
            });
        });
    }


}
