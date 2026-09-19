package string.assigment_problems;

import java.util.Scanner;

public class LibraryISBNNormalizer {

    static String normalizeISBN(String raw) {

        String isbn = raw.trim();

        if (isbn.length() < 3) {
            return isbn;
        }

        String publisherCode = isbn.substring(0, 3).toUpperCase();
        String remaining = isbn.substring(3);

        return publisherCode + remaining;
    }

    static String validateAndFormat(String isbn) {

        if (isbn.length() != 13) {
            return "Invalid: wrong length";
        }

        for (int i = 0; i < 3; i++) {
            if (!Character.isLetter(isbn.charAt(i))) {
                return "Invalid: publisher code must be 3 letters";
            }
        }

        for (int i = 3; i < isbn.length(); i++) {
            if (!Character.isDigit(isbn.charAt(i))) {
                return "Invalid: body must contain only digits";
            }
        }

        String publisherCode = isbn.substring(0, 3);
        String year = isbn.substring(3, 7);
        String catalog = isbn.substring(7);

        return "["
                + publisherCode
                + "] YEAR: "
                + year
                + " | CATALOG: "
                + catalog;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter ISBN: ");
        String raw = sc.nextLine();

        String normalized = normalizeISBN(raw);

        System.out.println(validateAndFormat(normalized));

        sc.close();
    }
}
