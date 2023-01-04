package uk.co.wowcher;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ClockConverter {

    static HashMap<String, String> dozens = new HashMap<>();
    static HashMap<String, String> oneToNine = new HashMap<>();
    static HashMap<String, String> tenToNineteen = new HashMap<>();

    public static void initialize() {
        oneToNine.put("0", "");
        oneToNine.put("00", "");
        oneToNine.put("1", "one");
        oneToNine.put("2", "two");
        oneToNine.put("3", "three");
        oneToNine.put("4", "four");
        oneToNine.put("5", "five");
        oneToNine.put("6", "six");
        oneToNine.put("7", "seven");
        oneToNine.put("8", "eight");
        oneToNine.put("9", "nine");
        tenToNineteen.put("10", "ten");
        tenToNineteen.put("11", "eleven");
        tenToNineteen.put("12", "twelve");
        tenToNineteen.put("13", "thirteen");
        tenToNineteen.put("14", "fourteen");
        tenToNineteen.put("15", "fifteen");
        tenToNineteen.put("16", "sixteen");
        tenToNineteen.put("17", "seventeen");
        tenToNineteen.put("18", "eighteen");
        tenToNineteen.put("19", "nineteen");
        dozens.put("10", "ten");
        dozens.put("20", "twenty");
        dozens.put("30", "thirty");
        dozens.put("40", "forty");
        dozens.put("50", "fifty");
    }

    private Pattern validationPattern = Pattern.compile("([01]?[0-9]|2[0-3]):[0-5][0-9]");
    private Pattern groupPattern = Pattern.compile("([0-2])([0-9]):([0-5])([0-9])");

    public ClockConverter() {
    }

    public String convertToWords(String time) {
        String result = "";
        if (time != null && validationPattern.matcher(time).matches()) {
            Matcher matcher = groupPattern.matcher(time);
            List<String> tokens = new LinkedList<>();
            if (matcher.matches() && matcher.groupCount() == 4) {
                for (int i = 1; i < 5; i++) {
                    tokens.add(matcher.group(i));
                }
            }
            String[] hoursAndMinutes = tokens.toArray(new String[tokens.size()]);
            if (hoursAndMinutes.length == 4) {
                result =  "It's " + checkHours(hoursAndMinutes[0], hoursAndMinutes[1])
                        + " " + checkOverTwenty(hoursAndMinutes[2], hoursAndMinutes[3]);
            }
        } else {
            result = "Time is not valid";
        }
        return result.trim();
    }

    private String checkHours(String left, String right) {
        String hours = left + right;
        if ("00".equals(hours)) {
            return "midnight";
        } else if ("12".equals(hours)) {
            return "midday";
        } else {
            return checkOverTwenty(left, right);
        }
    }

    private String checkOverTwenty(String left, String right) {
        String time = left + right;
        int intValue = Integer.valueOf(time).intValue();
        if (intValue < 10) {
            return oneToNine.get(time);
        }else if (intValue > 9 && intValue < 20) {
            return tenToNineteen.get(time);
        } else {
            return dozens.get(left + "0") + " " + oneToNine.get(right);
        }
    }
}
