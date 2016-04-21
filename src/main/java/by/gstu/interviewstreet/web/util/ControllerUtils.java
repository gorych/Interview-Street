package by.gstu.interviewstreet.web.util;

import by.gstu.interviewstreet.domain.Answer;

import java.util.List;

public final class ControllerUtils {

    public static final String TEXT_ANSWER_NAME = "text";

    private ControllerUtils() {
    }

    public static boolean notExistTextAnswer(List<Answer> answers) {
        return answers.stream()
                .filter(a -> a.getType().getName().equals(TEXT_ANSWER_NAME)).count() < 1;
    }

    public static int getPageCount(int allInterviewSize, double cardsOnPage) {
        return (int) Math.ceil(allInterviewSize / cardsOnPage);
    }

    /*Used for building pagination*/
    public static int[] getPaginationBounds(int pageNumber, int pageCount, int cardCountPerPage, int startPageNumber) {
        int leftBorder = startPageNumber;
        int rightBorder = cardCountPerPage;

        while (true) {
            if (pageNumber == leftBorder) {
                rightBorder = leftBorder + cardCountPerPage - 1;
                if (rightBorder > pageCount) {
                    rightBorder = pageCount;
                }
                break;
            }

            if (pageNumber > leftBorder && pageNumber < rightBorder) {
                if (rightBorder > pageCount) {
                    rightBorder = pageCount;
                }
                break;
            }

            if (pageNumber == rightBorder) {
                leftBorder = pageNumber;
                rightBorder = leftBorder + cardCountPerPage - 1;
                if (rightBorder >= pageCount) {
                    leftBorder = pageNumber - 3;
                    rightBorder = pageCount;
                }
                break;
            }

            leftBorder = rightBorder;
            rightBorder += cardCountPerPage - 1;
        }

        return new int[]{leftBorder, rightBorder};
    }

}
